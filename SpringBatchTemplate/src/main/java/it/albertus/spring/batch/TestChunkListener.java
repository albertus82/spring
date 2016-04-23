package it.albertus.spring.batch;

import java.util.List;

import org.springframework.batch.core.annotation.OnWriteError;

public class TestChunkListener {

	@OnWriteError
	public void onWriterError(final Exception exception, final List<? extends Integer> items) {
		exception.printStackTrace();
	}

}
