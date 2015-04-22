package it.albertus.spring.concerto;

import org.springframework.stereotype.Service;

@Service
public class Pianista implements Interprete {

	@Override
	public int esegui(String opera) {
		System.out.println(getClass().getSimpleName() + " - " + opera + ": PLINK PLINK PLINK...");
		return 40;
	}

}