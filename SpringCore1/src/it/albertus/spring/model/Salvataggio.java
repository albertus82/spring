package it.albertus.spring.model;

import org.springframework.stereotype.Component;

@Component
public class Salvataggio implements Missione {

	@Override
	public String toString() {
		return "Si parte per il salvataggio!";
	}

}