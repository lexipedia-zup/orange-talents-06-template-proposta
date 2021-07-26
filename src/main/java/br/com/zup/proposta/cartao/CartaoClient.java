package br.com.zup.proposta.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "novo-cartao",url = "http://localhost:8888/api/cartoes")
@Service
public interface CartaoClient {

    @PostMapping
    Cartao criarCartao(@RequestBody CriacaoCartaoRequest criacaoCartaoRequest);
}
