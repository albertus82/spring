package it.test.esempio.geometra;

import org.springframework.stereotype.Component;

@Component
public class Programma101 implements Calcolatrice {

	@Override
	public double somma(double a, double b) {
		return a+b;
	}

	@Override
	public double sottrazione(double a, double b) {
		return a-b;
	}

	@Override
	public double moltiplicazione(double a, double b) {
		return a*b;
	}

	@Override
	public double divisione(double a, double b) {
		return a/b;
	}
	
}