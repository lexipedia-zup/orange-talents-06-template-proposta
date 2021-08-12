package br.com.zup.proposta.validacao;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.HttpClientErrorException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UniqueValidator implements ConstraintValidator<Unique, Object> {
    @PersistenceContext
    private EntityManager entityManager;

    private Class<?> classe;

    private String atributo;

    @Override
    public void initialize(Unique constraintAnnotation) {
        atributo = constraintAnnotation.fieldName();
        classe = constraintAnnotation.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        if(atributo.equals("documento")) {
            List<Boolean> valida = new ArrayList<>();
            System.out.println("Entrando na validação");
            Query query = entityManager.createQuery("select " + atributo + " from " + classe.getName());
            List<?> resultadoConsulta = query.getResultList();

            for(Object documento:resultadoConsulta){
                if(new BCryptPasswordEncoder().matches(value.toString(), documento.toString())){
                    throw HttpClientErrorException.UnprocessableEntity.create(HttpStatus.UNPROCESSABLE_ENTITY,
                            "Proposta já realizada para esse documento.", HttpHeaders.EMPTY,
                            null, null);
                }
            }

            return true;
        }else{
            Query query = entityManager.createQuery("select 1 from " + classe.getName()+" where "+atributo+"=:value");
            query.setParameter("value", value);
            List<?> lista = query.getResultList();
            return lista.isEmpty();
        }


    }
}
