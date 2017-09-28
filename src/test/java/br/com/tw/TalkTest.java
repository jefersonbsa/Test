package br.com.tw;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class TalkTest {
	
	public static void main(String[] args) {
		LocalTime time = LocalTime.of(9, 0);
		System.out.println(time);
		
		time = time.plusMinutes(240);
		DateTimeFormatter dtf = new DateTimeFormatterBuilder().appendPattern("hh:mm a").toFormatter();
		LocalTime start = LocalTime.of(9, 0);
		LocalTime end = start.plusMinutes(240);

		System.out.println(dtf.format(start) + " to " + dtf.format(end));
		
	}

}
