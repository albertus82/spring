package it.albertus.spring.model;

import it.albertus.spring.model.validation.PasswordConstraint;
import it.albertus.spring.model.validation.StepOne;
import it.albertus.spring.model.validation.StepThree;
import it.albertus.spring.model.validation.StepTwo;
import it.albertus.spring.model.validation.UsernameConstraints;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

public class Utente implements Serializable {

	private static final long serialVersionUID = -8469320823598758896L;

	@UsernameConstraints(groups = { StepOne.class })
	private String username;

	@NotBlank(message = "La password \u00E8 obbligatoria.", groups = { StepOne.class })
	@Size(min = 5, max = 50, message = "{utente.pwd.size}", groups = { StepTwo.class })
	@PasswordConstraint(groups = { StepTwo.class })
	private transient String password;

	@Size(min = 1, max = 50, message = "{utente.nome}", groups = { StepThree.class })
	private String nome;

	@Size(min = 1, max = 50, message = "{utente.cognome}", groups = { StepThree.class })
	private String cognome;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Past(message = "{utente.dn.past}", groups = { StepThree.class })
	private Date dataNascita;

	private boolean abilitato;

	public Utente() {}

	public Utente(String username, String password, String nome, String cognome, Date dataNascita, boolean abilitato) {
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.abilitato = abilitato;
	}

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

	public boolean isAbilitato() {
		return abilitato;
	}

	public void setAbilitato(boolean abilitato) {
		this.abilitato = abilitato;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Utente)) {
			return false;
		}
		Utente other = (Utente) obj;
		if (username == null) {
			if (other.username != null) {
				return false;
			}
		}
		else if (!username.equals(other.username)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Utente [username=" + username + ", nome=" + nome + ", cognome=" + cognome + ", dataNascita=" + dataNascita + ", abilitato=" + abilitato + "]";
	}

}
