package br.com.zup.proposta.proposta;

import br.com.zup.proposta.analisefinanceira.AnaliseFinanceiraClient;
import br.com.zup.proposta.analisefinanceira.AnaliseFinanceiraRequest;
import br.com.zup.proposta.analisefinanceira.AnaliseFinanceiraResponse;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/proposta")
public class PropostaController {

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private AnaliseFinanceiraClient analiseFinanceiraClient;

    @PostMapping
    public ResponseEntity<?> insert(@Valid @RequestBody PropostaRequest request, UriComponentsBuilder uriComponentsBuilder) {



        Proposta novaProposta = request.toModel();

        propostaRepository.save(novaProposta);

        try {
            AnaliseFinanceiraRequest analiseFinanceiraRequest = new AnaliseFinanceiraRequest(novaProposta.getId().toString(), novaProposta.getDocumento(), novaProposta.getNome());
            AnaliseFinanceiraResponse analiseFinanceiraResponse = analiseFinanceiraClient.avaliarProposta(analiseFinanceiraRequest);
            System.out.println(analiseFinanceiraResponse.toString());
            if (analiseFinanceiraResponse.clienteElegivel()) {
                System.out.println("ENTRANDO NO IF");
                novaProposta.setSituacaoProposta(SituacaoProposta.ELEGIVEL);
                System.out.println("SAINDO DO IF");
            }

        } catch (FeignException e) {
            e.printStackTrace();
            novaProposta.setSituacaoProposta(SituacaoProposta.NAO_ELEGIVEL);
        }

        propostaRepository.save(novaProposta);

        URI enderecoRecurso = uriComponentsBuilder.path("/proposta/{id}").build(novaProposta.getId());
        return ResponseEntity.created(enderecoRecurso).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        Optional<Proposta> proposta = propostaRepository.findById(id);
        if (proposta.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok().body(proposta.get());
    }
}
