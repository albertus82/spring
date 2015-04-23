package it.test.esempio.soffitta;

import it.test.esempio.geometra.Calcolatrice;

import org.springframework.stereotype.Component;

@Component
public class Multisumma24 implements Calcolatrice {

	@Override
	public double somma(double a, double b) {
		System.out.println("Attendere...");
		try {
			Thread.sleep(1000);
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		return a + b;
	}

	@Override
	public double sottrazione(double a, double b) {
		System.out.println("Attendere...");
		try {
			Thread.sleep(1000);
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		return a - b;
	}

	@Override
	public double moltiplicazione(double a, double b) {
		System.out.println("Attendere...");
		try {
			Thread.sleep(3000);
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		return a * b;
	}

	@Override
	public double divisione(double a, double b) {
		throw new UnsupportedOperationException();
	}
	
}
