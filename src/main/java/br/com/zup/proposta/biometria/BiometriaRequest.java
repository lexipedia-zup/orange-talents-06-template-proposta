package br.com.zup.proposta.biometria;

import br.com.zup.proposta.cartao.Cartao;
import br.com.zup.proposta.validacao.IsValidBase64;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class BiometriaRequest {

    @IsValidBase64
    private String fingerPrint;

    public BiometriaRequest() {
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public BiometriaRequest(String fingerPrint) {
        this.fingerPrint = fingerPrint;
    }

    public String getFingerPrint() {
        return fingerPrint;
    }

    public Biometria toModel(Cartao cartao) {
        return new Biometria(Base64.getEncoder().encode(fingerPrint.getBytes()), cartao);
    }

}
