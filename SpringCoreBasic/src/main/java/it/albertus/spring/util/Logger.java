package it.albertus.spring.util;

import java.util.Date;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component @Primary
public class Logger {

	enum Level {
		DEBUG,
		INFO,
		ERROR;
	}
	
	public void info(String message, Class<? extends Object> clazz) {
		System.out.println(new Date() + " - " + clazz.getName() + " - " + Level.INFO + " - " + message);
	}
	
	public void error(String message, Class<? extends Object> clazz) {
		System.err.println(new Date() + " - " + clazz.getName() + " - " + Level.ERROR + " - " + message);
	}

}
