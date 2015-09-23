package it.albertus.spring.service;

public class TestRollbackException extends Exception {

	private static final long serialVersionUID = -1200842800693704728L;

	public TestRollbackException() {
		super();
	}

	public TestRollbackException(String message, Throwable cause) {
		super(message, cause);
	}

	public TestRollbackException(String message) {
		super(message);
	}

	public TestRollbackException(Throwable cause) {
		super(cause);
	}

}
