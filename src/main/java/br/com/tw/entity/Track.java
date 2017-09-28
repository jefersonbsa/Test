package br.com.tw.entity;

import java.time.LocalTime;
import java.util.Iterator;
import java.util.List;

import br.com.tw.util.ApplicationConfig;

public class Track {

	private Session morningPeriod = new SessionImpl(180,ApplicationConfig.MORNINGSTARTSESSION);
	private Session afternonPeriod = new SessionImpl(240,ApplicationConfig.AFTERNOONTSESSION);
	private SpecialSession lunchPeriod = new SpecialSessionImpl(ApplicationConfig.LUNCH_NAME,ApplicationConfig.LUNCH_TIME);
	private SpecialSession networkPeriod = new SpecialSessionImpl(ApplicationConfig.NETWORK_NAME,ApplicationConfig.NETWORK_TIME);

	private int identificador;

	public Track(int identificador) {
		this.identificador = identificador;
	}

	public void add(Talk talk) {
		Session sessionPeriod = getSessionWithTimeAvailable(talk);
		sessionPeriod.add(talk);
	}
	
	private Session getSessionWithTimeAvailable(Talk talk) {
		if (!isTimeAvaliable(talk))
			defineTimeForNetworkEvent();
		
		if (morningPeriod.isAvaliableTimeForTalk(talk))
			return morningPeriod;
		
		return afternonPeriod;
	}

	public boolean isTimeAvaliable(Talk talk) {
		return morningPeriod.isAvaliableTimeForTalk(talk) || afternonPeriod.isAvaliableTimeForTalk(talk);
	}
	
	

	public void defineTimeForNetworkEvent() {
		LocalTime afternonPeriodTime = LocalTime.parse(afternonPeriod.getLastHourOfSession(), ApplicationConfig.TIME_FORMATTER);
		if (afternonPeriodTime.isBefore(LocalTime.of(4,0))) 
			networkPeriod.defineHour(afternonPeriod.getLastHourOfSession());
			
		networkPeriod.defineHour(afternonPeriod.getLastHourOfSession());

		
	}

	private long getIdentificador() {
		return identificador;
	}
	
	private String getName() {
		return Track.class.getSimpleName();
	}

	public String toString() {
		StringBuilder resultAllSession = new StringBuilder();

		resultAllSession.append(this.getName()).append(" ").append(this.getIdentificador()).append(":");

		resultAllSession.append(ApplicationConfig.NEWLINE);

		resultAllSession.append(morningPeriod.toString());
		resultAllSession.append(lunchPeriod.toString());
		resultAllSession.append(afternonPeriod.toString());
		resultAllSession.append(networkPeriod.toString());

		resultAllSession.append(ApplicationConfig.NEWLINE);

		return resultAllSession.toString();
	}
	
	public List<Talk> fillSession(List<Talk> talks) {
		Iterator<Talk> talkIterator = talks.iterator();
		while ( talkIterator.hasNext()) {
			Talk talk = talkIterator.next();
			if (!isTimeAvaliable(talk))
				break;
			add(talk);
			talkIterator.remove();
		}
		
		defineTimeForNetworkEvent();
		
		return talks;
	}

}
