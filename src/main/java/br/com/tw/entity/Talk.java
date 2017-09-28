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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + timeDuration;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Talk other = (Talk) obj;
		if (timeDuration != other.timeDuration)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	

}
