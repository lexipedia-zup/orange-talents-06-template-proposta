package br.com.zup.proposta.proposta;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PropostaRepository extends JpaRepository<Proposta, Integer> {
    boolean existsByDocumento(String documento);
}
