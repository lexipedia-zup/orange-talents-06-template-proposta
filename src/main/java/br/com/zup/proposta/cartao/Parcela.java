package br.com.zup.proposta.cartao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Parcela {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


}
