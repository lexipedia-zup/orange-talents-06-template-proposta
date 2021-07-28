package br.com.zup.proposta.cartao.bloqueio;

import br.com.zup.proposta.cartao.Cartao;
import br.com.zup.proposta.cartao.CartaoClient;
import br.com.zup.proposta.cartao.CartaoRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/bloqueio")
public class BloqueioController {

    @Autowired
    private BloqueioRepository bloqueioRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private CartaoClient cartaoClient;

    @PostMapping("/{idCartao}")
    public ResponseEntity<?> insert(@PathVariable("idCartao") @NotNull Integer idCartao, HttpServletRequest httpServletRequest){

        Optional<Cartao> cartao = cartaoRepository.findById(idCartao);
        if(cartao.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        if(cartao.get().bloqueado()){
            return ResponseEntity.unprocessableEntity().build();
        }

        try{
            BloqueioResponse bloqueioResponse = cartaoClient.bloquearCartao(cartao.get().getId(),
                    Map.of("sistemaResponsavel", "proposta-api-alex"));
            if(bloqueioResponse.getResultado() == ResultadoBloqueio.BLOQUEADO){
                Bloqueio bloqueio = new Bloqueio(cartao.get(), httpServletRequest.getRemoteAddr(), httpServletRequest.getHeader("user-agent"));
                cartao.get().adicionaBloqueio(bloqueio);
                bloqueioRepository.save(bloqueio);
                return ResponseEntity.ok().build();
            }
        }catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }
}
