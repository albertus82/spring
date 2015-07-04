package it.albertus.spring.ioc;

import it.albertus.spring.ioc.geometra.Geometra;
import it.albertus.util.ThreadUtils;

import java.sql.SQLException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = { "it.albertus" }, excludeFilters = { @Filter(type = FilterType.REGEX, pattern = { "it.albertus.spring.ioc.soffitta.*" }) })
public class TestIoc {

	public static void main(String... args) throws SQLException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestIoc.class);

		Geometra geometra = (Geometra) context.getBean("geometra");

		System.out.println("Calcolo parcella in corso...");
		System.out.println(String.format(">>> %.2f EUR <<<", geometra.calcolaParcella(120)));
		System.out.println("Calcolo parcella concluso.");

		ThreadUtils.sleep(1500);

		context.close();
	}

}
