package it.albertus.spring.concerto;

import org.springframework.stereotype.Component;

@Component
public class Sinfonia implements Esecuzione {

	@Override
	public void esegui() {
		System.out.println(getClass().getSimpleName() + " - " + "TATATATAAAAAA - TATATATAAAAAAAAAAAA...");
	}

}