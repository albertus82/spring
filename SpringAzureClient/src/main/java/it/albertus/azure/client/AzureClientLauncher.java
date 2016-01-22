package it.albertus.azure.client;

import it.albertus.azure.config.SpringContext;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AzureClientLauncher {

	public static void main(String[] args) {
		if (args.length != 2 || args[0].trim().length() == 0 || args[1].trim().length() == 0) {
			throw new IllegalArgumentException("Parametri obbligatori: nomeFileSorgente percorsoDestinazione.");
		}

		final String source = args[0];
		final String destination = args[1] + '/' + args[0];

		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(SpringContext.class);

		FileStorage client = context.getBean(FileStorage.class);

		try {
			client.downloadToFile(source, destination);
			if (source.endsWith(".txt")) {
				System.out.println("Contenuto del file " + source + ": \"" + IOUtils.toString(client.downloadAsStream(source)) + "\"");
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			context.close();
		}
	}

}
