package br.com.tw.entity;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import br.com.tw.util.ApplicationConfig;

public class SessionImpl implements Session {
	
	protected Map<String,Talk> talks = new LinkedHashMap<>();
	
	protected int timeAvaliable ;
	protected LocalTime startTime ;
	protected LocalTime startNextTalk;
	
	public SessionImpl(int timeAvaliable,String hour) {
		this.timeAvaliable = timeAvaliable;
		this.startTime = LocalTime.parse(hour,getFormatter());
		this.startNextTalk = this.startTime;
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
		return ApplicationConfig.TIME_FORMATTER;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		Iterator<Entry<String,Talk>> schedulesEntry = talks.entrySet().iterator();
		while(schedulesEntry.hasNext()) {
			Entry<String, Talk> hourEntry = schedulesEntry.next();
			result.append(hourEntry.getKey() + " " + hourEntry.getValue());
			result.append(ApplicationConfig.NEWLINE);
		}
		
		return result.toString();
		
	}

}
