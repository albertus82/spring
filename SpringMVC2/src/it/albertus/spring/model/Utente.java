package it.albertus.spring.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class Utente implements Serializable {

	private static final long serialVersionUID = -8469030823598758896L;

	@NotNull
	@Size(min=1, max=50, message="Il nome \u00E8 obbligatorio e non pu\u00F2 essere pi\u00F9 lungo di 50 caratteri.")
	private String nome;
	
	@NotNull
	@Size(min=1, max=50, message="Il cognome \u00E8 obbligatorio e non pu\u00F2 essere pi\u00F9 lungo di 50 caratteri.")
	private String cognome;
	
	@NotNull
	@Size(min=1, max=50, message="L'username \u00E8 obbligatorio e non pu\u00F2 essere pi\u00F9 lungo di 50 caratteri.")
	private String username;
	
	@NotNull
	@Size(min=1, max=50, message="La password \u00E8 obbligatoria e non pu\u00F2 essere pi\u00F9 lunga di 50 caratteri.")
	private transient String password;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

}