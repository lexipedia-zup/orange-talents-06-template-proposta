package br.com.zup.proposta.cartao.bloqueio;

import br.com.zup.proposta.cartao.Cartao;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;

@Entity
public class Bloqueio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToOne
    private Cartao cartao;
    @CreationTimestamp
    private LocalDateTime instanteDoBloqueio = LocalDateTime.now();
    private String ipClienteRequisicao;
    private String userAgent;
    private boolean ativo;

    public Bloqueio() {
    }

    public Bloqueio(Cartao cartao, String ipClienteRequisicao,String userAgent) {
        this.cartao = cartao;
        this.ipClienteRequisicao = ipClienteRequisicao;
        this.userAgent = userAgent;
        this.ativo = true;
    }

    public Integer getId() {
        return id;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public LocalDateTime getInstanteDoBloqueio() {
        return instanteDoBloqueio;
    }

    public String getIpClienteRequisicao() {
        return ipClienteRequisicao;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public boolean isAtivo() {
        return ativo;
    }
}
