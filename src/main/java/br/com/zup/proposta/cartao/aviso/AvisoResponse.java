package br.com.zup.proposta.cartao.aviso;

public class AvisoResponse {

    private AvisoResultado resultado;

    public AvisoResponse() {
    }


    public AvisoResponse(AvisoResultado resultado) {
        this.resultado = resultado;
    }

    public AvisoResultado getResultado() {
        return resultado;
    }

    public void setResultado(AvisoResultado resultado) {
        this.resultado = resultado;
    }
}
