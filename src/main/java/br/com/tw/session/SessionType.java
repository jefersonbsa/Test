package br.com.tw.session;

public enum SessionType {
	MORNING("09:00AM",180,"Morning"),
	AFTERNOON("01:00PM",240,"Afternoon"),
	LUNCH("12:00PM",60,"Lunch"),
	NETWORKEVENT("05:00PM",60,"Networking Event");

	private String hour;
	private int timeDuration;
	private String sessionName;
	
	SessionType(String hour, int timeDuration, String sessionName) {
		this.hour = hour;
		this.timeDuration = timeDuration;
		this.sessionName = sessionName;
	}

	public String start() {
		return hour;
	}

	public int  time() {
		return this.timeDuration;
	}

	public String session() {
		return sessionName;
	}
}
