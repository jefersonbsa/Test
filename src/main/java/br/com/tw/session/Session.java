package br.com.tw.session;

import java.util.Collection;

import br.com.tw.entity.Talk;

public interface Session {
	
	public void add(Talk talk);
	public boolean isAvaliableTimeForTalk(Talk talk);
	public String getLastHourOfSession();
	public Collection<Talk> getTalks();
	public void addSpecialSession(SpecialSession session);
	public SpecialSession getSpecialSession();
}
