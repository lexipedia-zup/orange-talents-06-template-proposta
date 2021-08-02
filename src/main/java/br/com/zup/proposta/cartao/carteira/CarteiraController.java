package br.com.zup.proposta.cartao.carteira;

import br.com.zup.proposta.cartao.Cartao;
import br.com.zup.proposta.cartao.CartaoClient;
import br.com.zup.proposta.cartao.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/carteira")
public class CarteiraController {

    @Autowired
    private CarteiraRepository carteiraRepository;

    @Autowired
    private CartaoClient cartaoClient;

    @Autowired
    private CartaoRepository cartaoRepository;

    @PostMapping("/{idCartao}")
    public ResponseEntity<?> insert(@PathVariable("idCartao") Integer idCartao, @RequestBody @Valid CarteiraRequest request, UriComponentsBuilder uriComponentsBuilder){
        Optional<Cartao> cartao = cartaoRepository.findById(idCartao);
        if(cartao.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        boolean carteiraJaAssociadaNoCartao = carteiraRepository.existsByCartaoAndEmissor(cartao.get(), request.getCarteira().toString());
        if(carteiraJaAssociadaNoCartao){
            return ResponseEntity.unprocessableEntity().build();
        }
        CarteiraResponse response = cartaoClient.criarCarteira(cartao.get().getId(), request);
        if(response.getResultado() == CarteiraResultado.FALHA){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        Carteira carteira = request.toModel(response.getId(), cartao.get());
        cartao.get().adicionaCarteira(carteira);
        carteiraRepository.save(carteira);
        URI enderecoRecurso = uriComponentsBuilder.path("/carteira/{id}").build(carteira.getIdCarteira());
        return ResponseEntity.created(enderecoRecurso).build();
    }
}
