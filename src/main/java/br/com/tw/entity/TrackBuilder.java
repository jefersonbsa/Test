package br.com.tw.entity;

import java.util.LinkedHashMap;
import java.util.Map;

public class TrackBuilder {
	
	private TrackBuilder() {}
	
	private Map<String,Session> sessions = new LinkedHashMap<>();
	private static int sequence = 0;
	
	public Track factory() {
		incrementSequece();
		return new Track(sequence,sessions);
	}
	
	public static TrackBuilder build() {
		return new TrackBuilder();
	}
	
	public TrackBuilder withSessionMorning() {
		Session morningSession = new SessionImpl(SessionType.Morning);
		SpecialSession lunchSession = new SpecialSessionImpl(SessionType.Lunch);
		morningSession.addSpecialSession(lunchSession);
		sessions.put("Morning",morningSession);
		return this;
	}
	
	public TrackBuilder withSessionAfternoon() {
		Session afternonPeriod = new SessionImpl(SessionType.Afternoon);
		SpecialSession networkPeriod = new SpecialSessionImpl(SessionType.NetWorkEvent);
		afternonPeriod.addSpecialSession(networkPeriod);		
		sessions.put("Afternoon",afternonPeriod);
		return this;
	}
	
	private static void incrementSequece() {
		sequence++;
	}

}
