package br.com.zup.proposta.biometria;

import br.com.zup.proposta.cartao.Cartao;
import br.com.zup.proposta.cartao.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/biometria")
public class BiometriaController {

    @Autowired
    private BiometriaRepository biometriaRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    @PostMapping("/{cartaoId}")
    public ResponseEntity<?> insert(@PathVariable(name = "cartaoId") Integer cartaoId, @RequestBody @Valid BiometriaRequest request, UriComponentsBuilder uriComponentsBuilder){
        Optional<Cartao> cartao = cartaoRepository.findById(cartaoId);
        if (cartao.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Biometria biometria = request.toModel(cartao.get());
        biometriaRepository.save(biometria);
        URI enderecoRecurso = uriComponentsBuilder.path("/proposta/{id}").build(biometria.getId());
        return ResponseEntity.created(enderecoRecurso).build();
    }

}
