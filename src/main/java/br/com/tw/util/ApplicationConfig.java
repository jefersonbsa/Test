package br.com.tw.util;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class ApplicationConfig {

	private ApplicationConfig() {}
	
	public static final String PATTERN_INPUT_LINE_TALK = "^(.+) (\\d*min|lightning)$";
	public static final String TIME_TALK = "min";
	public static final String LIGHTNING_TALK = "lightning";
	public static final String NEWLINE = System.getProperty("line.separator");
	public static final DateTimeFormatter TIME_FORMATTER = new DateTimeFormatterBuilder().appendPattern("hh:mma").toFormatter();
	
	public static final String MORNINGSTARTSESSION = "09:00AM";
	public static final String AFTERNOONTSESSION = "01:00PM";
	
	public static final String LUNCH_NAME= "Lunch";
	public static final String LUNCH_TIME = "12:00PM";
	
	public static final String NETWORK_NAME = "Networking Event";
	public static final String NETWORK_TIME = "05:00PM";
}
