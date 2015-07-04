package it.albertus.spring.ioc.soffitta;

import it.albertus.spring.ioc.geometra.Calcolatrice;
import it.albertus.util.ThreadUtils;

import org.springframework.stereotype.Component;

@Component
public class Multisumma24 extends Calcolatrice {

	private static final int ATTESA = 4050;
	private static final String RUMORE = '/' + Multisumma24.class.getPackage().getName().replace('.', '/') + "/ms24_22k_8b.wav";

	@Override
	public double somma(double a, double b) {
		soundPlayer.play(this.getClass().getResourceAsStream(RUMORE));
		ThreadUtils.sleep(ATTESA);
		double risultato = super.somma(a, b);
		System.out.println(risultato);
		return risultato;
	}

	@Override
	public double sottrazione(double a, double b) {
		soundPlayer.play(this.getClass().getResourceAsStream(RUMORE));
		ThreadUtils.sleep(ATTESA);
		double risultato = super.sottrazione(a, b);
		System.out.println(risultato);
		return risultato;
	}

	@Override
	public double moltiplicazione(double a, double b) {
		soundPlayer.play(this.getClass().getResourceAsStream(RUMORE));
		ThreadUtils.sleep(ATTESA);
		double risultato = super.moltiplicazione(a, b);
		System.out.println(risultato);
		return risultato;
	}

	@Override
	public double divisione(double a, double b) {
		throw new UnsupportedOperationException();
	}

}
