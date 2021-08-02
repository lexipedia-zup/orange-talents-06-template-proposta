package br.com.zup.proposta.cartao.carteira;

import br.com.zup.proposta.cartao.Cartao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idCarteira;
    @ManyToOne
    private Cartao cartao;
    private String id;
    private String email;
    @CreationTimestamp
    private LocalDateTime associadaEm= LocalDateTime.now();
    private String emissor;

    public Carteira() {
    }

    public Carteira(Cartao cartao, String id, String email, String emissor) {
        this.cartao = cartao;
        this.id = id;
        this.email = email;
        this.emissor = emissor;
    }

    public Integer getIdCarteira() {
        return idCarteira;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getAssociadaEm() {
        return associadaEm;
    }

    public String getEmissor() {
        return emissor;
    }
}
