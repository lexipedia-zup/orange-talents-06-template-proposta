package br.com.zup.proposta.cartao.aviso;

import br.com.zup.proposta.cartao.Cartao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Aviso {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JsonIgnore
    private Cartao cartao;
    private String destino;
    private LocalDate dataTerminoViagem;
    @CreationTimestamp
    private LocalDateTime instanteDoAviso = LocalDateTime.now();
    private String ipClienteRequisicao;
    private String userAgent;

    public Aviso() {
    }

    public Aviso(Cartao cartao,
                 String destino,
                 LocalDate dataTerminoViagem,
                 String ipClienteRequisicao,
                 String userAgent) {
        this.cartao = cartao;
        this.destino = destino;
        this.dataTerminoViagem = dataTerminoViagem;
        this.ipClienteRequisicao = ipClienteRequisicao;
        this.userAgent = userAgent;
    }

    public Integer getId() {
        return id;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getDataTerminoViagem() {
        return dataTerminoViagem;
    }

    public LocalDateTime getInstanteDoAviso() {
        return instanteDoAviso;
    }

    public String getIpClienteRequisicao() {
        return ipClienteRequisicao;
    }

    public String getUserAgent() {
        return userAgent;
    }
}
