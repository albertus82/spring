package it.albertus.spring.model.validation;

import javax.validation.GroupSequence;

@GroupSequence({ StepOne.class, StepTwo.class, StepThree.class })
public interface UtenteChecks {}
