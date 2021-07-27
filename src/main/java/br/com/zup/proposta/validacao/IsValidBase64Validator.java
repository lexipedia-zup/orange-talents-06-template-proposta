package br.com.zup.proposta.validacao;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Base64;

public class IsValidBase64Validator implements ConstraintValidator<IsValidBase64, String> {
    @Override
    public void initialize(IsValidBase64 constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            Base64.getDecoder().decode(value.getBytes());
            return true;
        } catch (IllegalArgumentException e){
            return false;
        }
    }
}
