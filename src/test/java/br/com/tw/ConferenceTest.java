package br.com.tw;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.com.tw.entity.Conference;
import br.com.tw.entity.Talk;

public class ConferenceTest {
	
	
	@Test
	public void testWithManyTrack_ShloudCreateOneTrack_usingAllSession() {
		
		
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
		
		Conference conference = new Conference(talkList);
		conference.organize();
		
	}

}
