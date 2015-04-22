package it.albertus.spring.concerto;

public abstract class Interprete {
	
	private String nome;

	public abstract int esegui(String opera);

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}