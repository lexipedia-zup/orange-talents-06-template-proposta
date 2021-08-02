package br.com.zup.proposta.cartao.carteira;

import br.com.zup.proposta.cartao.Cartao;
import br.com.zup.proposta.cartao.carteira.Carteira;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;

public class CarteiraRequest {

    @Email
    private String email;
    @Enumerated(EnumType.STRING)
    private SistemaCarteira carteira;

    public CarteiraRequest() {
    }

    public CarteiraRequest(String email, SistemaCarteira carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public SistemaCarteira getCarteira() {
        return carteira;
    }

    public Carteira toModel(String id, Cartao cartao){
        return new Carteira(cartao, id, this.email, this.carteira.toString());
    }
}
