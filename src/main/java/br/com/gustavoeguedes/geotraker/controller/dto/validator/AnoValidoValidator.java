package br.com.gustavoeguedes.geotraker.controller.dto.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class AnoValidoValidator implements ConstraintValidator<AnoValido, Integer> {
    
    @Override
    public boolean isValid(Integer ano, ConstraintValidatorContext context) {
        if (ano == null) {
            return true;
        }

        int anoMinimo = 1900;

        int anoAtual = LocalDate.now().getYear();
        
        if (ano < anoMinimo || ano > anoAtual) {
            return false;
        }
        
        return true;
    }
}