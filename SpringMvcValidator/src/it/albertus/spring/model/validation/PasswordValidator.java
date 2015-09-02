package it.albertus.spring.model.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordConstraint, String> {

	@Override
	public void initialize(PasswordConstraint constraintAnnotation) {}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value != null && (value.toLowerCase().equals(value) || value.toUpperCase().equals(value))) {
			return false;
		}
		return true;
	}

}
