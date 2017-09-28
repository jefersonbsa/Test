package br.com.tw.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.tw.util.ApplicationConfig;

public class Conference {

	private List<Track> tracks = new ArrayList<>();
	private List<Talk> talks;

	public Conference(List<Talk> talks) {
		this.talks = talks;
	}

	public void organize() {
		Collections.sort(talks);
		fillTrack();
	}

	private void fillTrack() {
		int trackSequence = 1;
		
		while (!talks.isEmpty()) {
			Track track = new Track(trackSequence++);
			talks = track.fillSession(talks);
			tracks.add(track);
		}
	}

	public String toString() {
		StringBuilder resultOfAllTracks = new StringBuilder();
		for (Track track : tracks) {
			resultOfAllTracks.append(track.toString());
		}

		return resultOfAllTracks.toString();
	}

}
