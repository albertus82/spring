package it.albertus.spring.cassandra.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

@Table("D_PERSONE")
public class Persona implements Serializable {

	private static final long serialVersionUID = 8083377623405711312L;

	@PrimaryKeyColumn(name = "CODI_PERSONA", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	private UUID id;

	@Column("TEXT_COGNOME")
	private String cognome;

	@Column("TEXT_NOME")
	private String nome;

	@Column("DATA_NASCITA")
	private Date dataNascita;

	@Column("NUME_ALTEZZA")
	private Integer altezza;

	@Column("NUME_PESO")
	private Integer peso;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public Integer getAltezza() {
		return altezza;
	}

	public void setAltezza(Integer altezza) {
		this.altezza = altezza;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	@Override
	public String toString() {
		return "Persona [id=" + id + ", cognome=" + cognome + ", nome=" + nome + ", dataNascita=" + dataNascita + ", altezza=" + altezza + ", peso=" + peso + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (!(obj instanceof Persona)) {
			return false;
		}
		Persona other = (Persona) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		}
		else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

}
