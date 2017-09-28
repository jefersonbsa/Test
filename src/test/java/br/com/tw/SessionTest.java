package br.com.tw;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import br.com.tw.entity.Session;
import br.com.tw.entity.SessionImpl;
import br.com.tw.entity.SessionType;
import br.com.tw.entity.Talk;

public class SessionTest {
	
	@Test
	public void testWithMorningSession_shouldCreateSession_usingOneTalk() {
		String[] hoursOfTalks = new String[1];
		Session session = new SessionImpl(SessionType.MORNING);
		Talk talk = new Talk("Rails Enterprise",60);
		
		session.add(talk);
		
		assertThat(session.isAvaliableTimeForTalk(talk), equalTo(true));
		assertThat(session.getTalks().size(), equalTo(1));
		
		
		session.getTalks().keySet().toArray(hoursOfTalks);
		String[] expectedhoursOfTalks = {"09:00AM"};
		
		assertThat(hoursOfTalks, equalTo(expectedhoursOfTalks));
	}
	
	@Test
	public void testWithAfternoonSession_shouldCreateSession_usingFourTalk() {
		String[] hoursOfTalks = new String[4];
		String[] expectedhoursOfTalks = {"01:00PM","02:00PM","03:00PM","04:00PM"};
		
		Session session = new SessionImpl(SessionType.AFTERNOON);
		Talk talk1 = new Talk("Rails Enterprise",60);
		Talk talk2 = new Talk("Rails Enterprise 1",60);
		Talk talk3 = new Talk("Rails Enterprise 2",60);
		Talk talk4 = new Talk("Rails Enterprise 3",60);
		
		session.add(talk1);
		session.add(talk2);
		session.add(talk3);
		session.add(talk4);
		session.getTalks().keySet().toArray(hoursOfTalks);
		
		assertThat(session.isAvaliableTimeForTalk(talk4), equalTo(false));
		assertThat(session.getTalks().size(), equalTo(4));
		assertThat(hoursOfTalks, equalTo(expectedhoursOfTalks));
	}

}
