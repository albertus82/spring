package it.albertus.spring.ioc.model;

import it.albertus.util.ThreadUtils;

import org.springframework.stereotype.Component;

@Component
public class Multisumma24 extends Calcolatrice {

	private static final int ATTESA = 4050;
	private static final String RUMORE = '/' + Multisumma24.class.getPackage().getName().replace('.', '/') + "/ms24_22k_8b.wav";

	@Override
	public double addizione(double a, double b) {
		soundPlayer.play(this.getClass().getResourceAsStream(RUMORE));
		ThreadUtils.sleep(ATTESA);
		return super.addizione(a, b);
	}

	@Override
	public double sottrazione(double a, double b) {
		soundPlayer.play(this.getClass().getResourceAsStream(RUMORE));
		ThreadUtils.sleep(ATTESA);
		return super.sottrazione(a, b);
	}

	@Override
	public double moltiplicazione(double a, double b) {
		soundPlayer.play(this.getClass().getResourceAsStream(RUMORE));
		ThreadUtils.sleep(ATTESA);
		return super.moltiplicazione(a, b);
	}

	@Override
	public double divisione(double a, double b) {
		throw new UnsupportedOperationException("Operazione non disponibile sulla Olivetti Multisumma 24.");
	}

	@Override
	public String toString() {
		return "Multisumma 24";
	}

}
