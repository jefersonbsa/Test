package br.com.tw.app;

import java.io.FileNotFoundException;
import java.util.List;

import br.com.tw.entity.Conference;
import br.com.tw.entity.Talk;
import br.com.tw.util.ArgumentValidation;
import br.com.tw.util.FileTalkReader;

/**
 * App for organize Talks in a conference
 *
 */
public class ConferenceSchedulerApp {
	
	ArgumentValidation validator;
	private FileTalkReader talkReader;
	private Conference conference;
	
	public static void main(String[] args) throws FileNotFoundException {
		
		ArgumentValidation validator = new ArgumentValidation(args);
		FileTalkReader talkReader = new FileTalkReader(args[0]);
		
		ConferenceSchedulerApp conferenceSchedulerApp = new ConferenceSchedulerApp(validator, talkReader) ;
		conferenceSchedulerApp.run();
	}
	
	public void run() throws FileNotFoundException {
		validator.validate();
		List<Talk> talks = talkReader.readListOfTalk();
		
		conference = new Conference(talks);
		conference.organize();
	}

	public ConferenceSchedulerApp(ArgumentValidation validator, FileTalkReader talkReader) {
		this.validator = validator;
		this.talkReader = talkReader;
	}
	
	public String toString() {
		return conference.toString();
	}

}
