package br.com.tw.entity;

import java.time.LocalTime;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import br.com.tw.session.Session;
import br.com.tw.session.SessionType;
import br.com.tw.util.ApplicationConfig;

public class Track {


	private int identificador;
	private Map<String,Session> sessions;

	public Track(int identificador, Map<String,Session> sessions) {
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
		
		if (sessions.get(SessionType.MORNING.session()).isAvaliableTimeForTalk(talk))
			return sessions.get(SessionType.MORNING.session());
		
		return sessions.get(SessionType.AFTERNOON.session());
	}

	public boolean isTimeAvaliable(Talk talk) {
		return sessions.get(SessionType.MORNING.session()).isAvaliableTimeForTalk(talk) || sessions.get(SessionType.AFTERNOON.session()).isAvaliableTimeForTalk(talk);
	}
	
	public Map<String, Session> getSessions(){
		return Collections.unmodifiableMap(this.sessions);
	}

	public void defineTimeForNetworkEvent() {
		LocalTime afternonPeriodTime = LocalTime.parse(sessions.get(SessionType.AFTERNOON.session()).getLastHourOfSession(), ApplicationConfig.TIME_FORMATTER);
		if (afternonPeriodTime.isBefore(LocalTime.of(4,0))) 
			sessions.get(SessionType.AFTERNOON.session()).getSpecialSession().defineHour(sessions.get(SessionType.AFTERNOON.session()).getLastHourOfSession());
			
		sessions.get(SessionType.AFTERNOON.session()).getSpecialSession().defineHour(sessions.get(SessionType.AFTERNOON.session()).getLastHourOfSession());

		
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
		
		Iterator<Entry<String,Session>> sessionsEntry = sessions.entrySet().iterator();
		while(sessionsEntry.hasNext()) {
			Entry<String, Session> sessionName = sessionsEntry.next();
			resultAllSession.append(sessionName.getValue().toString());
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
