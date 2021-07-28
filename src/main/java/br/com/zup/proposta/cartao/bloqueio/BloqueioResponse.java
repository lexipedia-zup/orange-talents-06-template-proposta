package br.com.zup.proposta.cartao.bloqueio;

import com.fasterxml.jackson.annotation.JsonCreator;

public class BloqueioResponse {

    private ResultadoBloqueio resultado;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public BloqueioResponse(ResultadoBloqueio resultado) {
        this.resultado = resultado;
    }

    public ResultadoBloqueio getResultado() {
        return resultado;
    }
}
