package it.albertus.spring.batch;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Launcher {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job testJob;

	public static void main(final String... args) {
		final ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
		context.getBean(Launcher.class).launch(args);
		context.close();
	}

	private void launch(final String[] args) {
		final Map<String, JobParameter> parameters = new HashMap<String, JobParameter>();
		parameters.put("currentTimeMillis", new JobParameter(System.currentTimeMillis()));
		final JobParameters params = new JobParameters(parameters);
		try {
			jobLauncher.run(testJob, params);
		}
		catch (final Exception e) {
			e.printStackTrace();
		}
	}

}
