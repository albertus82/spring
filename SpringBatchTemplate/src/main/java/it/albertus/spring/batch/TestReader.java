package it.albertus.spring.batch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.batch.item.database.AbstractPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;

public class TestReader extends AbstractPagingItemReader<String> {

	@Autowired
	private JdbcOperations jdbcOperations;

	private String statement;

	private List<Object> parameters = new ArrayList<Object>();

	@Override
	protected void doReadPage() {
		if (results == null) {
			results = new CopyOnWriteArrayList<String>();
		}
		else {
			results.clear();
		}

		List<String> resultList = jdbcOperations.queryForList(statement, String.class, parameters.toArray());
		results.addAll(resultList);
	}

	@Override
	protected void doJumpToPage(int itemIndex) {}

	public final void setStatement(String statement) {
		this.statement = statement;
	}

	public final void setParameters(List<Object> parameters) {
		this.parameters = parameters;
	}

}
