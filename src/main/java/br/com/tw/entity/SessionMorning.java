package br.com.tw.entity;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class SessionMorning implements Session {
	
	private static final String newLine = System.getProperty("line.separator");
	private static final String MorningStartSession = "09:00AM";
	
	private int timeAvaliable = 180;
	private LocalTime startTime = LocalTime.parse(MorningStartSession,getFormatter());
	private LocalTime startNextTalk = this.startTime;
	
	private Map<String,Talk> talks = new LinkedHashMap<String,Talk>();
	
	public SessionMorning() {
	}
	
	public void add(Talk talk) {
		if (!isAvaliableTimeForTalk(talk))
			throw new IllegalArgumentException("Time in actual session unavaliable for talk : " + talk.getTitle());
		
		talks.put(getLastHourOfSession(), talk);
		
		decreaseTimeAvaliable(talk);
		increaseHourNextTalk(talk);
	}
	
	public String getLastHourOfSession() {
		return getFormatter().format(this.startNextTalk);
	}


	public Map<String, Talk> getTalks() {
		return Collections.unmodifiableMap(talks);
	}

	public boolean isAvaliableTimeForTalk(Talk talk) {
		return this.timeAvaliable >= talk.getTimeDuration();
	}
	
	private void decreaseTimeAvaliable(Talk talk) {
		this.timeAvaliable -= talk.getTimeDuration();
	}
	
	private void increaseHourNextTalk(Talk talk) {
		this.startNextTalk = this.startNextTalk.plusMinutes(talk.getTimeDuration());
	}

	public DateTimeFormatter getFormatter() {
		return new DateTimeFormatterBuilder().appendPattern("hh:mma").toFormatter();
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		Iterator<Entry<String,Talk>> schedulesEntry = talks.entrySet().iterator();
		while(schedulesEntry.hasNext()) {
			Entry<String, Talk> hourEntry = schedulesEntry.next();
			result.append(hourEntry.getKey() + " " + hourEntry.getValue());
			result.append(newLine);
		}
		
		return result.toString();
		
	}
}
