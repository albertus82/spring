package it.albertus.spring.ioc.geometra;

import it.albertus.util.SoundPlayer;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class Calcolatrice {

	@Autowired
	protected SoundPlayer soundPlayer;

	public double addizione(double a, double b) {
		return a + b;
	}

	public double sottrazione(double a, double b) {
		return a - b;
	}

	public double moltiplicazione(double a, double b) {
		return a * b;
	}

	public double divisione(double a, double b) {
		return a / b;
	}

}
