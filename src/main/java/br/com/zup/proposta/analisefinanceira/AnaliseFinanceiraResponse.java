package br.com.zup.proposta.analisefinanceira;

public class AnaliseFinanceiraResponse {

    private String idProposta;
    private String documento;
    private String nome;
    private ResultadoAnaliseFinanceira resultadoSolicitacao;

    public AnaliseFinanceiraResponse(String idProposta, String documento, String nome, ResultadoAnaliseFinanceira resultadoSolicitacao) {
        this.idProposta = idProposta;
        this.documento = documento;
        this.nome = nome;
        this.resultadoSolicitacao = resultadoSolicitacao;
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

    public ResultadoAnaliseFinanceira getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }

    public boolean clienteElegivel(){
        return this.resultadoSolicitacao == ResultadoAnaliseFinanceira.SEM_RESTRICAO;
    }

}