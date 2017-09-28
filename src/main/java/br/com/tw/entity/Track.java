package br.com.tw.entity;

import java.time.LocalTime;
import java.util.Iterator;
import java.util.List;

import br.com.tw.util.ApplicationConfig;

public class Track {


	private int identificador;
	private List<Session> sessions;

	public Track(int identificador,List<Session> sessions) {
		this.identificador = identificador;
		this.sessions = sessions;
	}

	public void add(Talk talk) {
		Session sessionPeriod = getSessionWithTimeAvailable(talk);
		sessionPeriod.add(talk);
	}
	
	private Session getSessionWithTimeAvailable(Talk talk) {
		if (!isTimeAvaliable(talk))
			defineTimeForNetworkEvent();
		
		if (sessions.get(0).isAvaliableTimeForTalk(talk))
			return sessions.get(0);
		
		return sessions.get(1);
	}

	public boolean isTimeAvaliable(Talk talk) {
		return sessions.get(0).isAvaliableTimeForTalk(talk) || sessions.get(1).isAvaliableTimeForTalk(talk);
	}
	
	

	public void defineTimeForNetworkEvent() {
		LocalTime afternonPeriodTime = LocalTime.parse(sessions.get(1).getLastHourOfSession(), ApplicationConfig.TIME_FORMATTER);
		if (afternonPeriodTime.isBefore(LocalTime.of(4,0))) 
			sessions.get(1).getSpecialSession().defineHour(sessions.get(1).getLastHourOfSession());
			
		sessions.get(1).getSpecialSession().defineHour(sessions.get(1).getLastHourOfSession());

		
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

		for (Session session : sessions) {
			resultAllSession.append(session.toString());
		}

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
