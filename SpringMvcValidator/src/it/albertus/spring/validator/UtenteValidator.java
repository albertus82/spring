package it.albertus.spring.validator;

import it.albertus.spring.model.Utente;
import it.albertus.spring.model.validation.UtenteChecks;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

@Component
public class UtenteValidator implements org.springframework.validation.Validator {

	@Autowired
	private javax.validation.Validator javaValidator;

	@Override
	public boolean supports(Class<?> clazz) {
		return Utente.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Utente utente = (Utente) target;
		Set<ConstraintViolation<Utente>> cvs = javaValidator.validate(utente, UtenteChecks.class);
		for (ConstraintViolation<Utente> cv : cvs) {
			String fieldName = cv.getPropertyPath().toString();
			FieldError fieldError = errors.getFieldError(fieldName);
			if (fieldError == null || !fieldError.isBindingFailure()) {
				errors.rejectValue(fieldName, "", cv.getMessage());
			}
		}
	}

}
