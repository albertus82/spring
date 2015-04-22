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
	
	@Pointcut("execution(** it.albertus.spring.concerto.Interprete.esegui(..))") // Si puo' specificare sia Interprete che Orchestra.
	private void esecuzione() {}; // Marcatore

	@Before("@within(org.springframework.stereotype.Service) && execution(** it.albertus.spring.concerto.Interprete.esegui(..))")
	public void silenceCellPhones() {
		System.out.println(getClass().getSimpleName() + " - " + "Spegne telefoni cellulari...");
	}

	@Before("esecuzione()")
	public void takeSeats() {
		System.out.println(getClass().getSimpleName() + " - " + "Prende posto...");
	}

	@After(value = "esecuzione() && args(opera)")
	public void exitAll(String opera) { // Il tipo del parametro deve corrispondere a quello del parametro del metodo "esegui"!
		System.out.println(getClass().getSimpleName() + " - " + "Valuta la qualit\u00E0 dell'esecuzione di \"" + opera + "\"...");
	}

	@AfterReturning(value = "esecuzione()", returning = "durata")
	public void applause(int durata) {
		System.out.println(getClass().getSimpleName() + " - " + "CLAP CLAP CLAP!!! (L'esecuzione \u00E8 durata " + durata + " minuti).");
	}

	@AfterThrowing(value = "esecuzione()", throwing = "eccezione")
	public void demandRefund(Throwable eccezione) {
		System.out.println(getClass().getSimpleName() + " - " + "BUUU!!! RIDATECI I SOLDI! (" + eccezione.getClass().getSimpleName() + ')');
	}

}