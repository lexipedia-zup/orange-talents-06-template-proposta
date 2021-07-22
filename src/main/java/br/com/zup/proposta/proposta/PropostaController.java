package br.com.zup.proposta.proposta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/proposta")
public class PropostaController {

    @Autowired
    private PropostaRepository propostaRepository;

    @PostMapping
    public ResponseEntity<?> insert(@Valid @RequestBody PropostaRequest request, UriComponentsBuilder uriComponentsBuilder){
        if(propostaRepository.existsByDocumento(request.getDocumento())){
            return ResponseEntity.unprocessableEntity().build();
        }
        Proposta novaProposta = request.toModel();
        propostaRepository.save(novaProposta);
        URI enderecoRecurso = uriComponentsBuilder.path("/proposta/{id}").build(novaProposta.getId());
        return ResponseEntity.created(enderecoRecurso).build();
    }
}
