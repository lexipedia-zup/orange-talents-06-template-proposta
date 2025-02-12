package br.com.zup.proposta.cartao;

import br.com.zup.proposta.cartao.aviso.AvisoRequest;
import br.com.zup.proposta.cartao.aviso.AvisoResponse;
import br.com.zup.proposta.cartao.bloqueio.BloqueioResponse;
import br.com.zup.proposta.cartao.carteira.CarteiraRequest;
import br.com.zup.proposta.cartao.carteira.CarteiraResponse;
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

    @PostMapping("/{id}/avisos")
    AvisoResponse criarAviso(@PathVariable String id, @RequestBody AvisoRequest request);

    @PostMapping("/{id}/carteiras")
    CarteiraResponse criarCarteira(@PathVariable String id, @RequestBody CarteiraRequest request);

}
