package br.com.tw;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.com.tw.entity.Talk;
import br.com.tw.entity.Track;
import br.com.tw.entity.TrackBuilder;

public class TrackTest {
	
	@Test
	public void testWithOneTrack_ShloudCreateOneTrack_usingMorningSession() {
		Track track = TrackBuilder.build().withSessionMorning().withSessionAfternoon().factory();
		track.add(new Talk("Rails Enterprice",60));
		
		System.out.println(track.toString());
	}
	
	@Test
	public void testWithManyTrack_ShloudCreateOneTrack_usingAllSession() {
		Track track = TrackBuilder.build().withSessionMorning().withSessionAfternoon().factory();
		
		List<Talk> talkList = new ArrayList<Talk>();
		
		talkList.add(new Talk("Rails Enterprice 1",60));
		talkList.add(new Talk("Rails Enterprice 2",60));
		talkList.add(new Talk("Rails Enterprice 3",60));
		talkList.add(new Talk("Rails Enterprice 4",30));
		talkList.add(new Talk("Rails Enterprice 5",5));
		talkList.add(new Talk("Rails Enterprice 6",45));
		talkList.add(new Talk("Rails Enterprice 7",45));
		talkList.add(new Talk("Rails Enterprice 8",45));
		talkList.add(new Talk("Rails Enterprice 9",30));
		talkList.add(new Talk("Rails Enterprice 10",30));
		talkList.add(new Talk("Rails Enterprice 11",30));
		talkList.add(new Talk("Rails Enterprice 12",30));
		
		for(Talk talk: talkList) {
			if (track.isTimeAvaliable(talk))
				track.add(talk);
			else {
				track.defineTimeForNetworkEvent();
				break;
			}
		}
		System.out.println(track.toString());
	}

	
}
