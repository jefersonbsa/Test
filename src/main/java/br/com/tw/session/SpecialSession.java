package br.com.tw.session;

import java.time.LocalTime;

public interface SpecialSession {
	
	public void defineHour(String hour);
	public LocalTime getStartHour();

}
