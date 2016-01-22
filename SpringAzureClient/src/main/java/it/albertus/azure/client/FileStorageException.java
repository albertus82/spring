package it.albertus.azure.client;

import java.io.IOException;

public class FileStorageException extends IOException {

	private static final long serialVersionUID = 8725715820464224758L;

	public FileStorageException() {
		super();
	}

	public FileStorageException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileStorageException(String message) {
		super(message);
	}

	public FileStorageException(Throwable cause) {
		super(cause);
	}

}
