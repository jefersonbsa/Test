package br.com.tw.util;

import java.util.LinkedHashMap;
import java.util.Map;

import br.com.tw.entity.Track;
import br.com.tw.session.Session;
import br.com.tw.session.SessionType;
import br.com.tw.session.SpecialSession;
import br.com.tw.session.impl.SessionImpl;
import br.com.tw.session.impl.SpecialSessionImpl;

public class TrackBuilder {
	
	private TrackBuilder() {}
	
	private Map<String,Session> sessions = new LinkedHashMap<>();
	private static int sequence = 0;
	
	public Track build() {
		incrementSequece();
		return new Track(sequence,sessions);
	}
	
	public static TrackBuilder getBuilder() {
		return new TrackBuilder();
	}
	
	public TrackBuilder withSessionMorning() {
		Session morningSession = new SessionImpl(SessionType.MORNING);
		SpecialSession lunchSession = new SpecialSessionImpl(SessionType.LUNCH);
		morningSession.addSpecialSession(lunchSession);
		sessions.put(SessionType.MORNING.session(),morningSession);
		return this;
	}
	
	public TrackBuilder withSessionAfternoon() {
		Session afternonPeriod = new SessionImpl(SessionType.AFTERNOON);
		SpecialSession networkPeriod = new SpecialSessionImpl(SessionType.NETWORKEVENT);
		afternonPeriod.addSpecialSession(networkPeriod);		
		sessions.put(SessionType.AFTERNOON.session(),afternonPeriod);
		return this;
	}
	
	private static void incrementSequece() {
		sequence++;
	}

}
