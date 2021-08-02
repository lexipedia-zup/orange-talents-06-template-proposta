package br.com.zup.proposta.cartao.aviso;

import br.com.zup.proposta.cartao.Cartao;
import br.com.zup.proposta.cartao.CartaoClient;
import br.com.zup.proposta.cartao.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/aviso")
public class AvisoController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private CartaoClient cartaoClient;

    @Autowired
    private AvisoRepository avisoRepository;

    @PostMapping("/{idCartao}")
    @Transactional
    public ResponseEntity<?> insert(@PathVariable("idCartao") Integer idCartao, @RequestBody @Valid AvisoRequest request, HttpServletRequest httpServletRequest) {
        Optional<Cartao> cartao = cartaoRepository.findById(idCartao);
        if (cartao.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        AvisoResponse avisoResponse = cartaoClient.criarAviso(cartao.get().getId(), request);
        if (avisoResponse.getResultado() == AvisoResultado.FALHA) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        Aviso aviso = request.toModel(cartao.get(), httpServletRequest.getRemoteAddr(), httpServletRequest.getHeader("user-agent"));
        cartao.get().adicionarAviso(aviso);
        avisoRepository.save(aviso);
        return ResponseEntity.ok().build();
    }

}
