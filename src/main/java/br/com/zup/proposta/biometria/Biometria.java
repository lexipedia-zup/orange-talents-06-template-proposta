package br.com.zup.proposta.biometria;

import br.com.zup.proposta.cartao.Cartao;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Biometria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private byte[] fingerPrint;
    private LocalDateTime dataCriacao;
    @ManyToOne
    private Cartao cartao;

    public Biometria() {
    }

    public Biometria(byte[] fingerPrint, Cartao cartao) {
        this.fingerPrint = fingerPrint;
        this.cartao = cartao;
        this.dataCriacao = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }
}
