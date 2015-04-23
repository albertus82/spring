package it.test.esempio.geometra;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("geometra")
public class Geometra implements InitializingBean {
	
	private String nome;
	
	@Autowired
	private Calcolatrice calcolatrice;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double calcolaParcella(int metriQuadrati) {
		return calcolatrice.moltiplicazione(metriQuadrati, 20);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (calcolatrice == null) {
			throw new IllegalStateException("La calcolatrice non puo' essere null!");
		}
	}
	
}