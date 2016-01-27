package it.albertus.storage.azure;

import it.albertus.storage.FileStorage;
import it.albertus.storage.FileStorageException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlob;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.ListBlobItem;

/**
 * Interfacciamento con la libreria di Microsoft Azure Storage.
 * 
 * In un Azure Blob Container non esistono directory vere e proprie, tuttavia
 * nei nomi dei file pu&ograve; essere usato il separatore <code>'/'</code> (o
 * equivalentemente <code>'\'</code>) per simulare a tutti gli effetti la
 * presenza di una struttura di directory.
 */
@Component
public class AzureFileStorage implements FileStorage {

	private static final Log logger = LogFactory.getLog(AzureFileStorage.class);

	@Value("${storage.azure.connectionString}")
	private String storageConnectionString;

	@Value("${storage.azure.containerName}")
	private String containerName;

	@Override
	public InputStream downloadAsStream(final String sourceFileName) throws FileStorageException, FileNotFoundException {
		CloudBlob blob = getBlobFromCloud(sourceFileName);

		InputStream is = null;
		try {
			is = blob.openInputStream();
			logger.debug("InputStream ottenuto per il file \"" + sourceFileName + "\"");
		}
		catch (StorageException se) {
			final String message = "Impossibile scaricare il file " + sourceFileName + ": errore del servizio di storage";
			logger.error(message + " - " + se.toString());
			throw new FileStorageException(message, se);
		}
		return is;
	}

	@Override
	public void downloadToFile(final String sourceFileName, final String destinationPathFileName) throws FileStorageException, FileNotFoundException, IOException {
		CloudBlob blob = getBlobFromCloud(sourceFileName);

		try {
			blob.downloadToFile(destinationPathFileName);
			logger.debug("File \"" + sourceFileName + "\" scaricato da Azure in \"" + destinationPathFileName + "\"");
		}
		catch (StorageException se) {
			final String message = "Impossibile scaricare il file " + sourceFileName + ": errore del servizio di storage";
			logger.error(message + " - " + se.toString());
			throw new FileStorageException(message, se);
		}
	}

	@Override
	public void downloadToStream(final String sourceFileName, final OutputStream outputStream) throws FileStorageException, FileNotFoundException {
		CloudBlob blob = getBlobFromCloud(sourceFileName);

		try {
			blob.download(outputStream);
			logger.debug("File \"" + sourceFileName + "\" scaricato da Azure in \"" + outputStream + "\"");
		}
		catch (StorageException se) {
			final String message = "Impossibile scaricare il file " + sourceFileName + ": errore del servizio di storage";
			logger.error(message + " - " + se.toString());
			throw new FileStorageException(message, se);
		}
	}

	private CloudBlob getBlobFromCloud(final String fileName) throws FileStorageException, FileNotFoundException {
		CloudStorageAccount storageAccount = getStorageAccount();
		CloudBlobClient blobClient = getBlobClient(storageAccount);
		CloudBlobContainer container = getBlobContainer(blobClient);
		CloudBlob blob = getBlob(fileName, container);
		return blob;
	}

	private CloudBlob getBlob(final String fileName, final CloudBlobContainer container) throws FileStorageException, FileNotFoundException {
		CloudBlob blob = null;

		Iterator<ListBlobItem> it = container.listBlobs(fileName).iterator();
		if (it.hasNext()) {
			ListBlobItem blobItem = it.next();
			if (it.hasNext()) {
				final String message = "Nome file incompleto, non valido o ambiguo: " + fileName;
				logger.error(message);
				throw new FileNotFoundException(message);
			}
			try {
				if (blobItem instanceof CloudBlob && ((CloudBlob) blobItem).getName().equals(fileName)) {
					blob = (CloudBlob) blobItem;
				}
				else {
					final String message = "File non trovato: " + fileName + "; individuata directory omonima.";
					logger.error(message);
					throw new FileNotFoundException(message);
				}
			}
			catch (URISyntaxException use) {
				final String message = "Nome risorsa non valido: " + fileName;
				logger.error(message);
				throw new FileStorageException(message, use);
			}
		}
		else {
			final String message = "File non trovato: " + fileName;
			logger.error(message);
			throw new FileNotFoundException(message);
		}
		return blob;
	}

	private CloudBlobContainer getBlobContainer(final CloudBlobClient blobClient) throws FileStorageException {
		CloudBlobContainer container = null;
		try {
			container = blobClient.getContainerReference(containerName);
			logger.debug("CloudBlobContainer: " + container);
		}
		catch (URISyntaxException use) {
			final String message = "Nome container non valido: " + containerName;
			logger.error(message + " - " + use.toString());
			throw new FileStorageException(message, use);
		}
		catch (StorageException se) {
			final String message = "Errore del servizio di storage Azure";
			logger.error(message + " - " + se.toString());
			throw new FileStorageException(message, se);
		}
		return container;
	}

	private CloudBlobClient getBlobClient(final CloudStorageAccount storageAccount) {
		CloudBlobClient blobClient = storageAccount.createCloudBlobClient();
		logger.debug("CloudBlobClient: " + blobClient);
		return blobClient;
	}

	private CloudStorageAccount getStorageAccount() throws FileStorageException {
		logger.debug("Stringa di connessione ad Azure: \"" + storageConnectionString + "\"");

		CloudStorageAccount storageAccount = null;
		try {
			storageAccount = CloudStorageAccount.parse(storageConnectionString);
			logger.debug("CloudStorageAccount: " + storageAccount);
		}
		catch (InvalidKeyException ike) {
			final String message = "Chiave account Azure non valida. Stringa di connessione: " + storageConnectionString;
			logger.error(message + " - " + ike.toString());
			throw new FileStorageException(message, ike);
		}
		catch (URISyntaxException use) {
			final String message = "Stringa di connessione Azure non valida: " + storageConnectionString;
			logger.error(message + " - " + use.toString());
			throw new FileStorageException(message, use);
		}
		return storageAccount;
	}

}
