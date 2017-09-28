package br.com.tw.entity;

import java.util.LinkedList;
import java.util.List;

import br.com.tw.util.ApplicationConfig;

public class TrackBuilder {
	
	private TrackBuilder() {}
	
	private Session morningPeriod;
	private Session afternonPeriod;
	private SpecialSession lunchPeriod;
	private SpecialSession networkPeriod;
	
	private List<Session> sessions = new LinkedList<>();
	private static int sequence = 0;
	
	public Track factory() {
		sequence++;
		return new Track(sequence,sessions);
	}
	
	public static TrackBuilder build() {
		return new TrackBuilder();
	}
	
	public TrackBuilder withSessionMorning() {
		morningPeriod = new SessionImpl(180,ApplicationConfig.MORNINGSTARTSESSION);
		lunchPeriod = new SpecialSessionImpl(ApplicationConfig.LUNCH_NAME,ApplicationConfig.LUNCH_TIME);
		morningPeriod.addSpecialSession(lunchPeriod);
		sessions.add(morningPeriod);
		return this;
	}
	
	public TrackBuilder withSessionAfternoon() {
		afternonPeriod = new SessionImpl(240,ApplicationConfig.AFTERNOONTSESSION);
		networkPeriod = new SpecialSessionImpl(ApplicationConfig.NETWORK_NAME,ApplicationConfig.NETWORK_TIME);
		afternonPeriod.addSpecialSession(networkPeriod);		
		sessions.add(afternonPeriod);
		return this;
	}

}
