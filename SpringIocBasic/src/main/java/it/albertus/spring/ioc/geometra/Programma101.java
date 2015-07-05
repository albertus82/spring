package it.albertus.spring.ioc.geometra;

import it.albertus.util.ThreadUtils;

import org.springframework.stereotype.Component;

@Component
public class Programma101 extends Calcolatrice {

	private static final int ATTESA = 850;
	private static final String RUMORE = '/' + Programma101.class.getPackage().getName().replace('.', '/') + "/p101_22k_8b.wav";

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
		soundPlayer.play(this.getClass().getResourceAsStream(RUMORE));
		ThreadUtils.sleep(ATTESA);
		return super.divisione(a, b);
	}

	public double radiceQuadrata(double operando) {
		soundPlayer.play(this.getClass().getResourceAsStream(RUMORE));
		ThreadUtils.sleep(ATTESA);
		return Math.sqrt(operando);
	}

	@Override
	public String toString() {
		return "Programma 101";
	}

}
