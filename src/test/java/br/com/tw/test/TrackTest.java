package br.com.tw.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.com.tw.entity.Talk;
import br.com.tw.entity.Track;
import br.com.tw.session.SessionType;
import br.com.tw.util.TrackBuilder;

public class TrackTest {
	
	@Test
	public void testWithOneTrack_ShouldCreateOneTrack_usingMorningSession() {
		Track track = TrackBuilder.getBuilder().withSessionMorning().withSessionAfternoon().build();
		Talk talk = new Talk("Rails Enterprice",60);
		track.add(talk);
		
		assertThat(track.isTimeAvaliable(talk), equalTo(true));
		assertThat(track.getSessions().size(), equalTo(2));
		assertThat(track.getSessions().get(SessionType.MORNING.session()).getTalks().size(), equalTo(1));
	}
	
	@Test
	public void testWithManyTrack_ShouldCreateOneTrack_usingSession() {
		Track track = TrackBuilder.getBuilder().withSessionMorning().withSessionAfternoon().build();
		
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
		
		List<Talk> remaining = track.fillSession(talkList);
		
		
		assertThat(track.getSessions().get(SessionType.MORNING.session()).getTalks().size(), equalTo(3));
		assertThat(track.getSessions().get(SessionType.AFTERNOON.session()).getTalks().size(), equalTo(7));
		assertThat(remaining.size(), equalTo(2));
		
	}

	
}
