package it.albertus.spring.model;

import it.albertus.spring.util.Logger;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class Cavaliere {

	@Autowired
	private Logger log;

//	@Resource(name = "salvataggio")
	private Missione missione;

	public String toString() {
		log.info("Sono nel toString()!", getClass());
		return "Salve, sono Re Artu'";
	}

	public Missione getMissione() {
		return missione;
	}

	public void setMissione(Missione missione) {
		this.missione = missione;
	}

	public void imbarcati() {
		System.out.println(getClass().getSimpleName() + ": " + missione.toString());
	}

}