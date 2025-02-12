package br.com.zup.proposta.cartao.bloqueio;

import br.com.zup.proposta.cartao.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BloqueioRepository extends JpaRepository<Bloqueio, Integer> {
    List<Bloqueio> findByCartaoAndAtivoIsTrue(Cartao cartao);
}
