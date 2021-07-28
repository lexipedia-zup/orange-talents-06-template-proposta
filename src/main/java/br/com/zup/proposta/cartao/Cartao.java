package br.com.zup.proposta.cartao;

import br.com.zup.proposta.cartao.bloqueio.Bloqueio;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idCartao;
    private String id;
    private LocalDateTime emitidoEm;
    private String titular;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Bloqueio> bloqueios;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Aviso> avisos;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Carteira> carteiras;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Parcela> parcelas;
    private Long limite;
    private String renegociacao;
    @OneToOne(cascade = CascadeType.ALL)
    private Vencimento vencimento;
    private Integer idProposta;

    public Cartao() {
    }

    public Cartao(String id,
                  LocalDateTime emitidoEm,
                  String titular,
                  List<Bloqueio> bloqueios,
                  List<Aviso> avisos,
                  List<Carteira> carteiras,
                  List<Parcela> parcelas,
                  Long limite,
                  String renegociacao,
                  Vencimento vencimento,
                  Integer idProposta) {

        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.bloqueios = bloqueios;
        this.avisos = avisos;
        this.carteiras = carteiras;
        this.parcelas = parcelas;
        this.limite = limite;
        this.renegociacao = renegociacao;
        this.vencimento = vencimento;
        this.idProposta = idProposta;
    }

    public Integer getIdCartao() {
        return idCartao;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public List<Bloqueio> getBloqueios() {
        return bloqueios;
    }

    public List<Aviso> getAvisos() {
        return avisos;
    }

    public List<Carteira> getCarteiras() {
        return carteiras;
    }

    public List<Parcela> getParcelas() {
        return parcelas;
    }

    public Long getLimite() {
        return limite;
    }

    public String getRenegociacao() {
        return renegociacao;
    }

    public Vencimento getVencimento() {
        return vencimento;
    }

    public Integer getIdProposta() {
        return idProposta;
    }

    public void adicionaBloqueio(Bloqueio bloqueio) {
        this.bloqueios.add(bloqueio);
    }

    public boolean bloqueado(){
        return !this.bloqueios.isEmpty();
    }
}
