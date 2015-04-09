package it.albertus.spring.concerto;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Pubblico {

	@Before("execution(** it.albertus.spring.concerto.Esecuzione.esegui(..))")
	public void silenceCellPhones() {
		System.out.println("Spegne telefoni cellulari");
	}

	@Before("execution(** it.albertus.spring.concerto.Esecuzione.esegui(..))")
	public void takeSeats() {
		System.out.println("Prende posto");
	}

	@AfterReturning("execution(** it.albertus.spring.concerto.Esecuzione.esegui(..))")
	public void applause() {
		System.out.println("Applausi!!!");
	}

	@AfterThrowing("execution(** it.albertus.spring.concerto.Esecuzione.esegui(..))")
	public void demandRefund() {
		System.out.println("Richiede rimborso");
	}
}