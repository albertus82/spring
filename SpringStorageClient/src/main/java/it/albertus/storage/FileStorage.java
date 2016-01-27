package it.albertus.storage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Interfaccia che rappresenta un sistema di storage indipendentemente
 * dall'implementazione specifica (cloud, server FTP, file system, ecc.).
 */
public interface FileStorage {

	/**
	 * Fornisce un {@link java.io.InputStream InputStream} che punta ad un file
	 * memorizzato sullo storage. <b>Tale stream dovr&agrave; essere
	 * esplicitamente chiuso al termine delle operazioni di lettura.</b>
	 * 
	 * @param sourceFileName il nome del file memorizzato sullo storage.
	 * 
	 * @return un {@link java.io.InputStream InputStream} che punta al file da
	 *         scaricare.
	 * 
	 * @throws FileStorageException in caso di errori durante il recupero del
	 *         file dallo storage.
	 * @throws FileNotFoundException se il file richiesto non &egrave; presente
	 *         sullo storage.
	 */
	InputStream downloadAsStream(String sourceFileName) throws FileStorageException, FileNotFoundException;

	/**
	 * Scarica direttamente in locale un file memorizzato sullo storage.
	 * 
	 * @param sourceFileName il nome del file memorizzato sullo storage da
	 *        scaricare.
	 * 
	 * @param destinationPathFileName il nome del file di destinazione che
	 *        sar&agrave; creato, comprensivo del percorso.
	 * 
	 * @throws FileStorageException in caso di errori durante il recupero del
	 *         file dallo storage.
	 * @throws FileNotFoundException se il file richiesto non &egrave; presente
	 *         sullo storage.
	 * @throws IOException in caso di errore nella creazione del file di
	 *         destinazione.
	 */
	void downloadToFile(String sourceFileName, String destinationPathFileName) throws FileStorageException, FileNotFoundException, IOException;

	/**
	 * Scarica direttamente in locale un file memorizzato sullo storage.
	 * 
	 * @param sourceFileName il nome del file memorizzato sullo storage da
	 *        scaricare.
	 * 
	 * @param outputStream lo stream in cui scaricare il file. <b>Tale stream
	 *        dovr&agrave; essere esplicitamente chiuso al termine delle
	 *        operazioni di scrittura.</b>
	 * 
	 * @throws FileStorageException in caso di errori durante il recupero del
	 *         file dallo storage.
	 * @throws FileNotFoundException se il file richiesto non &egrave; presente
	 *         sullo storage.
	 * @throws IOException in caso di errore nella creazione del file di
	 *         destinazione.
	 */
	void downloadToStream(String sourceFileName, OutputStream outputStream) throws FileStorageException, FileNotFoundException, IOException;

	/**
	 * Carica un file memorizzato in locale direttamente sullo storage.
	 * 
	 * @param sourcePathFileName il nome del file da caricare, comprensivo del
	 *        percorso.
	 * @param destinationFileName il nome del file che sar&agrave; memorizzato
	 *        sullo storage. <b>Se tale nome &egrave; gi&agrave; presente sullo
	 *        storage, il relativo file sar&agrave; sovrascritto.</b> Utilizzare
	 *        il metodo {@link #exists(String)} per accertarsi che il file non
	 *        sia gi&agrave; presente.
	 * 
	 * @throws FileStorageException in caso di errori durante l'accesso allo
	 *         storage.
	 * @throws IOException in caso di errore nella creazione del file di
	 *         destinazione.
	 */
	void uploadFromFile(String sourcePathFileName, String destinationFileName) throws FileStorageException, IOException;

	/**
	 * Carica un file sullo storage a partire da un {@link java.io.InputStream
	 * InputStream}.
	 * 
	 * @param inputStream lo stream da cui leggere i dati da trasferire sullo
	 *        storage.
	 * @param destinationFileName il nome del file che sar&agrave; memorizzato
	 *        sullo storage. <b>Se tale nome &egrave; gi&agrave; presente sullo
	 *        storage, il relativo file sar&agrave; sovrascritto.</b> Utilizzare
	 *        il metodo {@link #exists(String)} per accertarsi che il file non
	 *        sia gi&agrave; presente.
	 * 
	 * @throws FileStorageException in caso di errori durante l'accesso allo
	 *         storage.
	 * @throws IOException in caso di errore nella creazione del file di
	 *         destinazione.
	 */
	void uploadAsStream(InputStream inputStream, String destinationFileName) throws FileStorageException, IOException;

	/**
	 * Verifica l'esistenza di un file sullo storage. &Egrave; possibile
	 * utilizzare questo metodo per evitare di sovrascrivere inavvertitamente un
	 * file sullo storage, tenendo conto che comunque, tra il controllo di
	 * esistenza e l'effettiva scrittura, potrebbe teoricamente essere mutato lo
	 * stato dello storage.
	 * 
	 * @param fileName il nome del file di cui verificare l'esistenza.
	 * 
	 * @return <code>true</code> se il file &egrave; presente sullo storage,
	 *         <code>false</code> altrimenti.
	 * 
	 * @throws FileStorageException in caso di errori durante l'accesso allo
	 *         storage.
	 */
	boolean exists(String fileName) throws FileStorageException;

}
