package br.com.zup.proposta.proposta;

import br.com.zup.proposta.cartao.Cartao;
import br.com.zup.proposta.validacao.CpfOrCnpj;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Proposta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String documento;
    private String email;
    private String nome;
    private String endereco;
    private Integer salario;
    @Enumerated(EnumType.STRING)
    private SituacaoProposta situacaoProposta;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Cartao cartao;

    public Proposta() {
    }

    public Proposta(@CpfOrCnpj @NotBlank String documento,
                    @NotBlank @Email String email,
                    @NotBlank String nome,
                    @NotBlank String endereco,
                    @NotNull @Positive Integer salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Integer getId() {
        return id;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public void setSituacaoProposta(SituacaoProposta situacaoProposta) {
        this.situacaoProposta = situacaoProposta;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }
}
