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

	@Override
	public void write(final List<? extends Integer> items) throws Exception {
		for (int item : items) {
			jdbcOperations.update(statement, item);
		}
	}

	public final void setStatement(String statement) {
		this.statement = statement;
	}

}
