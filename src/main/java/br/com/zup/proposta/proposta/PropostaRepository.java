package br.com.zup.proposta.proposta;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropostaRepository extends JpaRepository<Proposta, Integer> {
    boolean existsByDocumento(String documento);
    List<Proposta> findBySituacaoPropostaAndCartaoIsNull(SituacaoProposta situacaoProposta);
}
