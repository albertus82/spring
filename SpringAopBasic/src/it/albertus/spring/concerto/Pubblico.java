package it.albertus.spring.concerto;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Pubblico {
	
	@Pointcut("execution(** it.albertus.spring.concerto.Esecuzione.esegui(..))") // Si puo' specificare sia Esecuzione che Sinfonia.
	private void esecuzione() {}; // Marcatore

	@Before("esecuzione()")
	public void silenceCellPhones() {
		System.out.println(getClass().getSimpleName() + " - " + "Spegne telefoni cellulari");
	}

	@Before("esecuzione()")
	public void takeSeats() {
		System.out.println(getClass().getSimpleName() + " - " + "Prende posto");
	}

	@AfterReturning("esecuzione()")
	public void applause() {
		System.out.println(getClass().getSimpleName() + " - " + "Applausi!!!");
	}

	@AfterThrowing("esecuzione()")
	public void demandRefund() {
		System.out.println(getClass().getSimpleName() + " - " + "Richiede rimborso");
	}
}