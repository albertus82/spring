package it.albertus.spring.concerto;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Audience {

	@Before("execution(** it.albertus.spring.concerto.Esecuzione.esegui(..))")
	public void silenceCellPhones() {
		System.out.println("Silencing cell phones");
	}

	@Before("execution(** it.albertus.spring.concerto.Esecuzione.esegui(..))")
	public void takeSeats() {
		System.out.println("Taking seats");
	}

	@AfterReturning("execution(** it.albertus.spring.concerto.Esecuzione.esegui(..))")
	public void applause() {
		System.out.println("CLAP CLAP CLAP!!!");
	}

	@AfterThrowing("execution(** it.albertus.spring.concerto.Esecuzione.esegui(..))")
	public void demandRefund() {
		System.out.println("Demanding a refund");
	}
}