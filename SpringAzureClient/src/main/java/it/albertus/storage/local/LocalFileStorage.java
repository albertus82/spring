package it.albertus.storage.local;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import it.albertus.storage.FileStorage;
import it.albertus.storage.FileStorageException;

public class LocalFileStorage implements FileStorage {

	@Override
	public InputStream downloadAsStream(String sourceFileName) throws FileStorageException, FileNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void downloadToFile(String sourceFileName, String destinationPathFileName) throws FileStorageException, FileNotFoundException, IOException {
		// TODO Auto-generated method stub

	}

}
