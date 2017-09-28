package br.com.tw.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
		while (!talks.isEmpty()) {
			Track track = TrackBuilder.build().withSessionMorning().withSessionAfternoon().factory();
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
