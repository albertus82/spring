package it.albertus.storage.client;

import it.albertus.storage.FileStorage;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StorageClient {

	private static final String lineSeparator = System.getProperty("line.separator");

	@Autowired
	private FileStorage fileStorage;

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
