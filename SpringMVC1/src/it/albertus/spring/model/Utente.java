package it.albertus.spring.model;

import java.io.Serializable;
import java.util.Date;

public class Utente implements Serializable {

	private static final long serialVersionUID = -8469030823598758896L;

	private String nome;
	private String cognome;
	private transient String password;
	private Date dataNascita;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
