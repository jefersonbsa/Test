package br.com.tw.util;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class ApplicationConfig {

	public static final String PATTERN_INPUT_LINE_TALK = "^(.+) (\\d*min|lightning)$";
	public static final String TIME_TALK = "min";
	public static final String LIGHTNING_TALK = "lightning";
	public static final String NEWLINE = System.getProperty("line.separator");
	public static final DateTimeFormatter TIME_FORMATTER = new DateTimeFormatterBuilder().appendPattern("hh:mma").toFormatter();
}
