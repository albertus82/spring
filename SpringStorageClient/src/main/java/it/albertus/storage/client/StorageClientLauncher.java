package it.albertus.storage.client;

import it.albertus.storage.config.SpringContext;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class StorageClientLauncher {

	public static void main(String[] args) {
		if (args.length != 2 || args[0].trim().length() == 0 || args[1].trim().length() == 0) {
			throw new IllegalArgumentException("Parametri obbligatori: nomeFileSorgente percorsoDestinazione.");
		}

		final String source = args[0];
		final String destination = args[1] + '/' + args[0];

		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(SpringContext.class);

		StorageClient client = context.getBean(StorageClient.class);

		client.execute(source, destination);

		context.close();
	}

}
