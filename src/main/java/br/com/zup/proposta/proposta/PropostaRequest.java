package br.com.zup.proposta.proposta;

import br.com.zup.proposta.validacao.CpfOrCnpj;
import br.com.zup.proposta.validacao.Unique;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class PropostaRequest {

    @CpfOrCnpj
    @NotBlank
    @Unique(fieldName = "documento", domainClass = Proposta.class)
    private String documento;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String nome;
    @NotBlank
    private String endereco;
    @NotNull
    @Positive
    private BigDecimal salario;

    public PropostaRequest() {
    }

    public PropostaRequest(String documento, String email, String nome, String endereco, BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Proposta toModel(){
        BCryptPasswordEncoder encriptador = new BCryptPasswordEncoder();
        String documentoEncriptado = encriptador.encode(this.documento);
        return new Proposta(documentoEncriptado,
                this.email,
                this.nome,
                this.endereco,
                this.salario.multiply(BigDecimal.valueOf(100.0)).intValue());
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }
}
