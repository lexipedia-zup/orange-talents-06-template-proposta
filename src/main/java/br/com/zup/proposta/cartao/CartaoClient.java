package br.com.zup.proposta.cartao;

import br.com.zup.proposta.cartao.bloqueio.BloqueioResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "novo-cartao",url = "http://localhost:8888/api/cartoes")
@Service
public interface CartaoClient {

    @PostMapping
    Cartao criarCartao(@RequestBody CriacaoCartaoRequest criacaoCartaoRequest);

    @PostMapping("/{id}/bloqueios")
    BloqueioResponse bloquearCartao(@PathVariable String id, Map<String, String> sistemaResponsavel);
}
