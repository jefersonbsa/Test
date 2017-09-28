package br.com.tw.entity;

import br.com.tw.util.ApplicationConfig;

public class Talk  implements Comparable<Talk>{

	private int timeDuration;
	private String title;

	public Talk(String title, int timeDuration) {
		super();
		this.timeDuration = timeDuration;
		this.title = title;
	}

	public int getTimeDuration() {
		return timeDuration ;
	}

	public String getTitle() {
		return title;
	}

	@Override
	public int compareTo(Talk talk) {
		return Integer.compare(talk.getTimeDuration(),this.getTimeDuration());
	}
	
	
	public String toString() {
		String extensiveTime;
		
		if ( this.timeDuration == 5)
			extensiveTime = ApplicationConfig.LIGHTNING_TALK;
		else
			extensiveTime = this.timeDuration + ApplicationConfig.TIME_TALK; 
		return this.getTitle() + " " + extensiveTime;
	}
	

}
