package it.albertus.spring.batch;

import org.springframework.batch.item.ItemProcessor;

public class TestProcessor implements ItemProcessor<String, Integer> {

	@Override
	public Integer process(final String item) throws Exception {
		int length = -1;
		if (item != null) {
			length = item.length();
		}
		return length;
	}

}
