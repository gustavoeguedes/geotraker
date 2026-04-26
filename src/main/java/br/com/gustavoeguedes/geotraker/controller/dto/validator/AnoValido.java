package br.com.gustavoeguedes.geotraker.controller.dto.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AnoValidoValidator.class)
public @interface AnoValido {
    String message() default "Ano do veículo inválido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}