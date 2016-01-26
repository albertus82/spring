package it.albertus.spring.cassandra.model;

import java.io.Serializable;

import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

@Table("D_TEST_SPRING_DATA_CASSANDRA")
public class TestSpringDataCassandra implements Serializable {

	private static final long serialVersionUID = 4732258867457406530L;

	@PrimaryKeyColumn(name = "SEQU_TEST", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	private Integer id;

	@Column("TEXT_STRINGA")
	private String testo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
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
		if (!(obj instanceof TestSpringDataCassandra)) {
			return false;
		}
		TestSpringDataCassandra other = (TestSpringDataCassandra) obj;
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

	@Override
	public String toString() {
		return "TestSpringDataCassandra [id=" + id + ", testo=" + testo + "]";
	}

}
