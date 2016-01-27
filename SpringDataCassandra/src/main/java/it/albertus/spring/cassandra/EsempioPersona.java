package it.albertus.spring.cassandra;

import it.albertus.spring.cassandra.dao.PersonaDao;
import it.albertus.spring.cassandra.model.Persona;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.datastax.driver.core.Session;

@Component
public class EsempioPersona implements InitializingBean {

	private enum NomePersona {
		Mario, Giorgio, Paolo, Vittorio;
	}

	private enum CognomePersona {
		Rossi, Verdi, Bianchi, Neri;
	}

	@Autowired
	private Session cassandraSession;

	@Autowired
	private PersonaDao personaDao;

	@Value("${cassandra.session.keyspaceName}")
	private String keyspaceName;

	public void afterPropertiesSet() {
		/* Creazione keyspace (se non presente). Da eseguire con keyspace "system". */
		cassandraSession.execute("CREATE KEYSPACE IF NOT EXISTS " + keyspaceName + " WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 }");

		/* Eliminazione tabella */
		// cassandraSession.execute("DROP TABLE d_persone");

		/* Creazione tabella */
		cassandraSession.execute("CREATE TABLE IF NOT EXISTS d_persone (row_created_dttm TIMESTAMP, " +
                                                                       "codi_persona UUID PRIMARY KEY, " +
		                                                               "text_cognome TEXT, " +
		                                                               "text_nome TEXT, " +
		                                                               "data_nascita TIMESTAMP, " +
		                                                               "nume_altezza INT, " +
		                                                               "nume_peso INT)");
	}

	public void run() {
		/* INSERT... */
		Persona persona = new Persona();
		persona.setCognome(CognomePersona.values()[(int) (Math.random() * CognomePersona.values().length)].toString());
		persona.setNome(NomePersona.values()[(int) (Math.random() * NomePersona.values().length)].toString());
		persona.setDataNascita(new GregorianCalendar((int) (1900 + Math.random() * 100), (int) (Math.random() * Calendar.DECEMBER), (int) (1 + Math.random() * 30)).getTime());
		persona.setAltezza(Double.valueOf(150 + Math.random() * 50).intValue());
		persona.setPeso(Double.valueOf(50 + Math.random() * 60).intValue());
		persona.setId(UUID.randomUUID());
		persona.setDataCreazione(Calendar.getInstance().getTime());
		personaDao.save(persona);

		/* SELECT... */
		Iterable<Persona> righe = personaDao.findAll();
		System.out.println("Elenco persone:");
		for (Persona riga : righe) {
			System.out.println(riga.toString());
		}
	}

}
