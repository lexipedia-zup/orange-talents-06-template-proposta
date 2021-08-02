package br.com.zup.proposta.cartao.carteira;

public class CarteiraResponse {

    private CarteiraResultado resultado;
    private String id;

    public CarteiraResponse() {
    }

    public CarteiraResponse(CarteiraResultado resultado, String id) {
        this.resultado = resultado;
        this.id = id;
    }

    public CarteiraResultado getResultado() {
        return resultado;
    }

    public String getId() {
        return id;
    }
}
