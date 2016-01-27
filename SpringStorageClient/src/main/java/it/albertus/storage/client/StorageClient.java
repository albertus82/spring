package it.albertus.storage.client;

import it.albertus.storage.FileStorage;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;

public class StorageClient {

	private static final Log logger = LogFactory.getLog(StorageClient.class);

	private static final String lineSeparator = System.getProperty("line.separator");

	private FileStorage fileStorage;

	/* Iniezione */
	@Required
	public void setFileStorage(FileStorage fileStorage) {
		this.fileStorage = fileStorage;
	}

	/* Verifica iniezione */
	@PostConstruct
	private void afterPropertiesSet() {
		if (null == fileStorage) {
			logger.fatal("FileStorage non inizializzato. Verificare la configurazione di Spring.");
		}
	}

	public void download(String source, String destination) {
		/* Creazione directory se non presenti */
		File parent = new File(destination).getParentFile();
		if (parent != null) {
			parent.mkdirs();
		}

		try {
			/* Scaricamento diretto su file */
			fileStorage.downloadToFile(source, destination);

			/* Scaricamento tramite InputStream */
			if (source.endsWith(".txt")) {
				InputStream is = fileStorage.downloadAsStream(source);
				final String fileAsString = IOUtils.toString(is);
				is.close();
				System.out.println("Contenuto del file \"" + source + "\":" + lineSeparator + "[SOF]" + fileAsString + "[EOF]");
			}

			/* Scaricamento su OutputStream */
			BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(destination + ".bis"));
			fileStorage.downloadToStream(source, os);
			os.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void upload(String source, String destination) {
		try {
			/* Controllo esistenza file */
			System.out.println("Il file \"" + destination + "\" esiste gia'? " + (fileStorage.exists(destination) ? "Si'" : "No"));

			/* Caricamento diretto da file */
			fileStorage.uploadFromFile(source, destination);

			/* Caricamento tramite InputStream */
			BufferedInputStream is = new BufferedInputStream(new FileInputStream(source));
			fileStorage.uploadAsStream(is, destination + ".bis");
			is.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void delete(String fileName) {
		try {
			final boolean deleted = fileStorage.delete(fileName);
			System.out.println("File \"" + fileName + '"' + (deleted ? " " : " non ") + "cancellato.");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
