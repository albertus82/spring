package it.albertus.storage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Interfaccia che rappresenta un sistema di storage indipendentemente
 * dall'implementazione specifica (cloud, server FTP, file system, ecc.).
 */
public interface FileStorage {

	/**
	 * Fornisce un {@link java.io.InputStream InputStream} che punta ad un file
	 * memorizzato sullo storage.
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

}
