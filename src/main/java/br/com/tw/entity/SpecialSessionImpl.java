package br.com.tw.entity;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import br.com.tw.util.ApplicationConfig;

public class SpecialSessionImpl implements SpecialSession{

	private LocalTime startTime;
	private String sessionName;
	
	public SpecialSessionImpl(String sessionName, String hour) {
		this.sessionName = sessionName;
		this.defineHour(hour);
	}

	public void defineHour(String hour) {
		this.startTime = LocalTime.parse(hour, getFormatter());
	}

	public DateTimeFormatter getFormatter() {
		return ApplicationConfig.TIME_FORMATTER;
	}

	@Override
	public String toString() {

		StringBuilder result = new StringBuilder();
		result.append(getFormatter().format(startTime) + " " + sessionName);
		result.append(ApplicationConfig.NEWLINE);
		
		return result.toString();
	}

}