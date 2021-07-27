package br.com.zup.proposta.validacao;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy =  IsValidBase64Validator.class)
public @interface IsValidBase64 {

    String message() default "O valor já foi cadastrado";

    Class<?>[] groups() default{};

    Class<? extends Payload>[] payload() default{};
}
