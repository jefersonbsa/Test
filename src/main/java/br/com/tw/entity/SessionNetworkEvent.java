package br.com.tw.entity;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import br.com.tw.util.ApplicationConfig;

public class SessionNetworkEvent implements SpecialSession {

	private LocalTime startTime;
	private String sessionName = "Networking Event";
	

	public SessionNetworkEvent() {
		defineHour("05:00PM");
	}

	@Override
	public void defineHour(String hour) {
		this.startTime = LocalTime.parse(hour,getFormatter());
	}
	
	public DateTimeFormatter getFormatter() {
		return ApplicationConfig.TIME_FORMATTER;
	}
	
	@Override
	public String toString() {
			return (getFormatter().format(startTime) + " "  + sessionName);
	}

}
