package br.com.tw.entity;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import br.com.tw.util.ApplicationConfig;

public class SessionLunch implements SpecialSession {
	
	private static final String LUNCH_TIME = "12:00PM";
	private static final String LUNCH_NAME= "Lunch";
	private LocalTime startTime;
	private String sessionName = LUNCH_NAME;
	
	public SessionLunch() {
		defineHour(LUNCH_TIME);
	}

	@Override
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
