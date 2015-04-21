package it.albertus.spring.concerto;

import org.springframework.stereotype.Component;

@Component
public class Sinfonia implements Esecuzione {

	@Override
	public int esegui(String opera) {
		System.out.println(getClass().getSimpleName() + " - " + opera + ": TATATATAAAAAA - TATATATAAAAAAAAAAAA...");
		if (Math.random() < 0.5D) {
			System.out.println(getClass().getSimpleName() + " - Stonatura...");
			throw new StonaturaException();
		}
		return 60;
	}

}