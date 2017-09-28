package br.com.tw.entity;

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
			extensiveTime = "lightning";
		else
			extensiveTime = this.timeDuration + "min"; 
		return this.getTitle() + " " + extensiveTime;
	}
	

}
