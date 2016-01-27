package it.albertus.storage.client;

import it.albertus.storage.config.SpringContext;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UploadToFileStorage {

	public static void main(String[] args) {
		if (args.length != 2 || args[0].trim().length() == 0 || args[1].trim().length() == 0) {
			throw new IllegalArgumentException("Parametri obbligatori: nomeFileSorgente nomeFileDestinazione.");
		}

		final String source = args[0].trim();
		final String destination = args[1].trim();

		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(SpringContext.class);

		StorageClient client = context.getBean(StorageClient.class);

		client.upload(source, destination);

		context.close();
	}

}
