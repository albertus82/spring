package it.albertus.storage.client;

import it.albertus.storage.config.SpringContext;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DeleteFromFileStorage {

	public static void main(String[] args) {
		if (args.length != 1 || args[0].trim().length() == 0) {
			throw new IllegalArgumentException("Parametro obbligatorio: nomeFileDaCancellare.");
		}

		final String fileName = args[0].trim();

		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(SpringContext.class);

		StorageClient client = context.getBean(StorageClient.class);

		client.delete(fileName);

		context.close();
	}

}
