package br.com.zup.proposta.analisefinanceira;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AnaliseFinanceiraRequest {

    private String idProposta;
    private String documento;
    private String nome;

    public AnaliseFinanceiraRequest(String idProposta, String documento, String nome) {
        this.idProposta = idProposta;
        this.documento = documento;
        this.nome = nome;
    }

    public String getIdProposta() {
        return idProposta;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

}

