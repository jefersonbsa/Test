package br.com.tw.entity;

import java.time.LocalTime;
import java.util.Iterator;
import java.util.List;

import br.com.tw.util.ApplicationConfig;

public class Track {

	private Session morningPeriod = new SessionImpl(180,ApplicationConfig.MORNINGSTARTSESSION);
	private Session afternonPeriod = new SessionImpl(240,ApplicationConfig.AFTERNOONTSESSION);
	private SessionLunch lunchPeriod = new SessionLunch();
	private SessionNetworkEvent networkPeriod = new SessionNetworkEvent();

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

		resultAllSession.append(this.sessionContent(morningPeriod));
		resultAllSession.append(this.specialSessionContent(lunchPeriod));
		resultAllSession.append(this.sessionContent(afternonPeriod));
		resultAllSession.append(this.specialSessionContent(networkPeriod));

		resultAllSession.append(ApplicationConfig.NEWLINE);
		resultAllSession.append(ApplicationConfig.NEWLINE);

		return resultAllSession.toString();
	}
	
	private String sessionContent(Session session) {
		if (session==null)
			return "";
		
		return session.toString();
	}
	
	private String specialSessionContent(SpecialSession session) {
		if (session==null)
			return "";
		
		return session.toString();
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
