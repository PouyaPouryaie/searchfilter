package ir.bigz.ms.searchfilter.common.annotation;

import ir.bigz.ms.searchfilter.common.utility.Utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NationalCodeValidator implements ConstraintValidator<NationalCode, String> {

    @Override
    public boolean isValid(String nationalCode, ConstraintValidatorContext constraintValidatorContext) {
        return Utils.isValidNationalCode(nationalCode);
    }

    @Override
    public void initialize(NationalCode constraintAnnotation) {

    }
}
