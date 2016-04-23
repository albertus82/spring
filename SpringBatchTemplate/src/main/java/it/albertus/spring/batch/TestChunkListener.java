package it.albertus.spring.batch;

import java.util.Date;
import java.util.List;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.annotation.OnWriteError;

public class TestChunkListener {

	private static int counter;

	@BeforeStep
	public void beforeStep(final StepExecution stepExecution) {
		System.out.println("Inizio esecuzione \"" + stepExecution.getStepName() + "\" n. " + ++counter + "...");
	}

	@AfterStep
	public ExitStatus afterStep(final StepExecution stepExecution) {
		final ExitStatus exitStatus = stepExecution.getExitStatus().addExitDescription(new Date().toString());
		System.out.println("Fine esecuzione step \"" + stepExecution.getStepName() + "\" n. " + counter + " (" + exitStatus + ").");
		return exitStatus;
	}

	@OnWriteError
	public void onWriterError(final Exception exception, final List<? extends Integer> items) {
		System.err.println("Errore durante la scrittura dei seguenti elementi: " + items + '.');
		exception.printStackTrace();
	}

}
