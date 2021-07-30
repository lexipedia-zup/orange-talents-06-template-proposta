package br.com.zup.proposta.cartao.aviso;

import br.com.zup.proposta.cartao.Cartao;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvisoRequest {

    @NotBlank
    private String destino;
    @NotNull
    @Future
    private LocalDate validoAte;

    public AvisoRequest() {
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public AvisoRequest(String destino, LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }

    public Aviso toModel(Cartao cartao, String ipClienteRequisicao, String userAgent){
        return new Aviso(cartao, this.destino, this.validoAte, ipClienteRequisicao, userAgent);
    }
}
