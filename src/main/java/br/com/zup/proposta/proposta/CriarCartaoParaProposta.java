package br.com.zup.proposta.proposta;

import br.com.zup.proposta.cartao.Cartao;
import br.com.zup.proposta.cartao.CartaoClient;
import br.com.zup.proposta.cartao.CriacaoCartaoRequest;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@EnableScheduling
@Transactional
public class CriarCartaoParaProposta {

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private CartaoClient cartaoClient;

    @Scheduled(fixedRate = 10000)
    public void criarCartaoParaProposta(){
        List<Proposta> propostasElegiveis = propostaRepository.findBySituacaoPropostaAndCartaoIsNull(SituacaoProposta.ELEGIVEL);

        if(!propostasElegiveis.isEmpty()){
            for(Proposta proposta: propostasElegiveis){
                try {
                    CriacaoCartaoRequest criacaoCartaoRequest = new CriacaoCartaoRequest(proposta.getId().toString(), proposta.getDocumento(), proposta.getNome());
                    Cartao cartao = cartaoClient.criarCartao(criacaoCartaoRequest);
                    proposta.setCartao(cartao);
                    propostaRepository.save(proposta);
                }catch (FeignException ignored){
                }
            }
        }

    }

}
