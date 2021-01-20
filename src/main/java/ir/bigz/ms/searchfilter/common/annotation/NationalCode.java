package ir.bigz.ms.searchfilter.common.annotation;

import org.springframework.stereotype.Component;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Component
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy= NationalCodeValidator.class)
public @interface NationalCode {

    String message() default "{validation.message.incorrect.nationalCode}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
