package it.albertus.spring.batch;

import java.util.List;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class TestWriter implements ItemWriter<Integer> {

	@Autowired
	private JdbcOperations jdbcOperations;

	private String statement;

	private double errorPercentage;

	@Override
	public void write(final List<? extends Integer> items) throws Exception {
		for (int item : items) {
			jdbcOperations.update(statement, item);
			if (Math.random() > (100.0 - errorPercentage) / 100.0) {
				throw new IllegalStateException("Eccezione simulata in fase di scrittura (probabilit\u00E0: " + errorPercentage + "%).");
			}
			System.out.println(item);
		}
	}

	public final void setStatement(String statement) {
		this.statement = statement;
	}

	public void setErrorPercentage(double errorPercentage) {
		this.errorPercentage = errorPercentage;
	}

}
