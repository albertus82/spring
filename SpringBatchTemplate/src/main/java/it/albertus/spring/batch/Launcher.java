package it.albertus.spring.batch;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Launcher {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job testJob;

	@Value("${cron.testJob}")
	private String cronExpression;

	public static void main(final String... args) {
		final ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("batch-context.xml");
		final Thread shutdownHook = new Thread("shutdownHook") {
			@Override
			public void run() {
				if (context != null) {
					context.close();
				}
			}
		};
		Runtime.getRuntime().addShutdownHook(shutdownHook);
	}

	public void launch() {
		final Map<String, JobParameter> parameters = new HashMap<String, JobParameter>();
		final Date startDate = new Date();
		parameters.put("startDate", new JobParameter(startDate.toString()));
		parameters.put("startTimeMillis", new JobParameter(startDate.getTime()));
		parameters.put("cronExpression", new JobParameter(cronExpression));
		final JobParameters params = new JobParameters(parameters);
		try {
			jobLauncher.run(testJob, params);
		}
		catch (final Exception e) {
			e.printStackTrace();
		}
	}

}
