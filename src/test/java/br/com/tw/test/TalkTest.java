package br.com.tw.test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import br.com.tw.entity.Talk;

public class TalkTest {
	
	@Test
	public void testWithTalk_ShouldCreateOneTalk() {
		Talk talk = new Talk("Rails Enterprice 1",60);
		
		assertThat(talk.getTimeDuration(), equalTo(60));
		assertThat(talk.getTitle(), equalTo("Rails Enterprice 1"));
	}
	
	@Test
	public void testWithTalk_ShouldCreateTwoTalk_comparable() {
		Talk talk1 = new Talk("Rails Enterprice 1",60);
		Talk talk2 = new Talk("Rails Enterprice 2",45);
		
		assertThat(talk1.getTimeDuration(), not(talk2.getTimeDuration()));
		assertThat(talk2.getTitle(), not(talk1.getTitle()));
		
		assertThat(talk1.equals(talk2), equalTo(false));
		
	}
}
