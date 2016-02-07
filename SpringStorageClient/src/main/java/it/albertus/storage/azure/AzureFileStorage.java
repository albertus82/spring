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

import javax.activation.MimetypesFileTypeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlob;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import com.microsoft.azure.storage.blob.ListBlobItem;

/**
 * Interfacciamento con la libreria di Microsoft Azure Storage.
 * 
 * In un Azure Blob Container non esistono directory vere e proprie, tuttavia
 * nei nomi dei file pu&ograve; essere usato il separatore <tt>'/'</tt> (o
 * equivalentemente <tt>'\'</tt>) per simulare a tutti gli effetti la
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
	public boolean delete(final String fileName) throws FileStorageException {
		CloudBlobContainer container = getBlobContainerFromCloud();
		try {
			CloudBlockBlob blob = container.getBlockBlobReference(fileName);
			final boolean deleted = blob.deleteIfExists();
			logger.info("File \"" + fileName + "\" cancellato da Azure (container " + containerName + ")");
			return deleted;
		}
		catch (URISyntaxException use) {
			final String message = "Nome file \"" + fileName + "\" non valido per il container \"" + containerName + '"';
			logger.error(message + " - " + use.toString());
			throw new FileStorageException(message, use);
		}
		catch (StorageException se) {
			final String message = "Impossibile cancellare il file " + fileName + ": errore del servizio di storage";
			logger.error(message + " - " + se.toString());
			throw new FileStorageException(message, se);
		}
	}

	@Override
	public boolean exists(final String fileName) throws FileStorageException {
		CloudBlobContainer container = getBlobContainerFromCloud();
		try {
			CloudBlockBlob blob = container.getBlockBlobReference(fileName);
			return blob.exists();
		}
		catch (URISyntaxException use) {
			final String message = "Nome file \"" + fileName + "\" non valido per il container \"" + containerName + '"';
			logger.error(message + " - " + use.toString());
			throw new FileStorageException(message, use);
		}
		catch (StorageException se) {
			final String message = "Impossibile verificare l'esistenza del file " + fileName + ": errore del servizio di storage";
			logger.error(message + " - " + se.toString());
			throw new FileStorageException(message, se);
		}
	}

	@Override
	public void uploadFromFile(final String sourcePathFileName, final String destinationFileName) throws FileStorageException, IOException {
		CloudBlobContainer container = getBlobContainerFromCloud();
		try {
			final CloudBlockBlob blob = getUploadBlobReference(destinationFileName, container);
			setBlobContentType(blob, destinationFileName);
			blob.uploadFromFile(sourcePathFileName);
			logger.debug("File \"" + sourcePathFileName + "\" caricato su Azure in \"" + destinationFileName + "\"");
		}
		catch (URISyntaxException use) {
			final String message = "Nome file destinazione \"" + destinationFileName + "\" non valido per il container \"" + containerName + '"';
			logger.error(message + " - " + use.toString());
			throw new FileStorageException(message, use);
		}
		catch (StorageException se) {
			final String message = "Impossibile caricare il file " + sourcePathFileName + ": errore del servizio di storage";
			logger.error(message + " - " + se.toString());
			throw new FileStorageException(message, se);
		}
	}

	@Override
	public void uploadAsStream(final InputStream inputStream, final String destinationFileName) throws FileStorageException, IOException {
		CloudBlobContainer container = getBlobContainerFromCloud();
		try {
			final CloudBlockBlob blob = getUploadBlobReference(destinationFileName, container);
			setBlobContentType(blob, destinationFileName);
			blob.upload(inputStream, -1);
			logger.debug("File \"" + inputStream + "\" caricato su Azure in \"" + destinationFileName + "\"");
		}
		catch (URISyntaxException use) {
			final String message = "Nome file destinazione \"" + destinationFileName + "\" non valido per il container \"" + containerName + '"';
			logger.error(message + " - " + use.toString());
			throw new FileStorageException(message, use);
		}
		catch (StorageException se) {
			final String message = "Impossibile caricare lo stream " + inputStream + ": errore del servizio di storage";
			logger.error(message + " - " + se.toString());
			throw new FileStorageException(message, se);
		}
	}

	private CloudBlockBlob getUploadBlobReference(final String destinationFileName, CloudBlobContainer container) throws URISyntaxException, StorageException {
		final CloudBlockBlob blob = container.getBlockBlobReference(destinationFileName);
		final boolean exists = blob.exists();
		if (exists) {
			logger.warn("Il Blob \""+ destinationFileName + "\" esiste gia' su Azure. Si procede in sovrascrittura!");
		}
		return blob;
	}

	private void setBlobContentType(final CloudBlockBlob blob, final String fileName) {
		/* Perche' Microsoft non fa questa cosa automaticamente? */
		try {
			int index = fileName.indexOf('.');
			if (index != -1) {
				final String extension = fileName.substring(index).toLowerCase();
				if (".xbrl".equals(extension)) {
					blob.getProperties().setContentType("application/xml");
				}
				else if (".zip".equals(extension)) {
					blob.getProperties().setContentType("application/zip");
				}				
				else {
					blob.getProperties().setContentType(new MimetypesFileTypeMap().getContentType(fileName));
				}
			}
		}
		catch (RuntimeException re) {
			logger.warn("Impossibile determinare il Content Type per il file \"" + fileName + "\" - " + re.toString());
		}
	}

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
		CloudBlobContainer container = getBlobContainerFromCloud();
		CloudBlob blob = getBlob(fileName, container);
		return blob;
	}

	private CloudBlobContainer getBlobContainerFromCloud() throws FileStorageException {
		CloudStorageAccount storageAccount = getStorageAccount();
		CloudBlobClient blobClient = getBlobClient(storageAccount);
		CloudBlobContainer container = getBlobContainer(blobClient);
		return container;
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
