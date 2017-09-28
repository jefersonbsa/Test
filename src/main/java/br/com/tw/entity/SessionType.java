package br.com.tw.entity;

public enum SessionType {
	Morning("09:00AM",180,"Morning"),
	Afternoon("01:00PM",240,"Afternoon"),
	Lunch("12:00PM",60,"Lunch"),
	NetWorkEvent("05:00PM",60,"Networking Event");

	private String hour;
	private int timeDuration;
	private String sessionName;
	
	SessionType(String hour, int timeDuration, String sessionName) {
		this.hour = hour;
		this.timeDuration = timeDuration;
		this.sessionName = sessionName;
	}

	public String startSession() {
		return hour;
	}

	public int  getTimeDuration() {
		return this.timeDuration;
	}

	public String getSessionName() {
		return sessionName;
	}
}
