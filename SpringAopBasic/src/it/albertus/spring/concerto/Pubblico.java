package it.albertus.spring.concerto;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component // Deve essere un bean gestito da Spring!
@Aspect
public class Pubblico {
	
	@Pointcut("execution(** it.albertus.spring.concerto.Esecuzione.esegui(..))") // Si puo' specificare sia Esecuzione che Sinfonia.
	private void esecuzione() {}; // Marcatore

	@Before("esecuzione()")
	public void silenceCellPhones() {
		System.out.println(getClass().getSimpleName() + " - " + "Spegne telefoni cellulari...");
	}

	@Before("esecuzione()")
	public void takeSeats() {
		System.out.println(getClass().getSimpleName() + " - " + "Prende posto...");
	}

	@After("esecuzione()")
	public void exitAll() {
		System.out.println(getClass().getSimpleName() + " - " + "Valuta la qualit\u00E0 dell'esecuzione...");
	}
	
	@AfterReturning("esecuzione()")
	public void applause() {
		System.out.println(getClass().getSimpleName() + " - " + "Applaude!!!");
	}

	@AfterThrowing("esecuzione()")
	public void demandRefund() {
		System.out.println(getClass().getSimpleName() + " - " + "Richiede rimborso.");
	}

}