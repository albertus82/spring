package it.albertus.storage.local;

import it.albertus.storage.FileStorage;
import it.albertus.storage.FileStorageException;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Implementazione di {@link FileStorage} che preleva file da un file system
 * locale.
 */
@Component
public class LocalFileStorage implements FileStorage {

	@Value("${storage.local.path}")
	private String sourcePath;

	@Override
	public InputStream downloadAsStream(final String sourceFileName) throws FileNotFoundException {
		return new BufferedInputStream(new FileInputStream(sourcePath + File.separatorChar + sourceFileName));
	}

	@Override
	public void downloadToFile(final String sourceFileName, final String destinationPathFileName) throws IOException {
		BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(destinationPathFileName));
		downloadToStream(sourceFileName, os);
		os.close();
	}

	@Override
	public void downloadToStream(final String sourceFileName, final OutputStream outputStream) throws IOException {
		InputStream is = downloadAsStream(sourceFileName);
		IOUtils.copy(is, outputStream);
		is.close();
		outputStream.flush();
	}

	@Override
	public void uploadFromFile(String sourcePathFileName, String destinationFileName) throws IOException {
		InputStream is = new BufferedInputStream(new FileInputStream(sourcePathFileName));
		uploadAsStream(is, destinationFileName);
		is.close();
	}

	@Override
	public void uploadAsStream(InputStream inputStream, String destinationFileName) throws FileStorageException, IOException {
		BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(sourcePath + File.separatorChar + destinationFileName));
		IOUtils.copy(inputStream, os);
		os.close();
	}

	@Override
	public boolean exists(String fileName) throws FileStorageException {
		File file = new File(sourcePath + File.separatorChar + fileName);
		return file.exists();
	}

}
