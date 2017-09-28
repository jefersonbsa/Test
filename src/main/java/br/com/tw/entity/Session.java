package br.com.tw.entity;

import java.util.Map;

public interface Session {
	
	public void add(Talk talk);
	public boolean isAvaliableTimeForTalk(Talk talk);
	public String getLastHourOfSession();
	public Map<String, Talk> getTalks();
}
