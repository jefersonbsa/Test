package br.com.tw.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import br.com.tw.entity.Talk;
import br.com.tw.session.Session;
import br.com.tw.session.SessionType;
import br.com.tw.session.impl.SessionImpl;

public class SessionTest {
	
	@Test
	public void testWithMorningSession_shouldCreateSession_usingOneTalk() {
		Session session = new SessionImpl(SessionType.MORNING);
		Talk talk = new Talk("Rails Enterprise",60);
		
		session.add(talk);
		
		assertThat(session.isAvaliableTimeForTalk(talk), equalTo(true));
		assertThat(session.getTalks().size(), equalTo(1));
	}
	
	@Test
	public void testWithAfternoonSession_shouldCreateSession_usingFourTalk() {
		
		Session session = new SessionImpl(SessionType.AFTERNOON);
		Talk talk1 = new Talk("Rails Enterprise",60);
		Talk talk2 = new Talk("Rails Enterprise 1",60);
		Talk talk3 = new Talk("Rails Enterprise 2",60);
		Talk talk4 = new Talk("Rails Enterprise 3",60);
		
		session.add(talk1);
		session.add(talk2);
		session.add(talk3);
		session.add(talk4);
		
		assertThat(session.isAvaliableTimeForTalk(talk4), equalTo(false));
		assertThat(session.getTalks().size(), equalTo(4));
	}

}
