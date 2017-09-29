package br.com.tw.app;

import java.io.FileNotFoundException;
import java.util.List;

import br.com.tw.entity.Conference;
import br.com.tw.entity.Talk;
import br.com.tw.util.ArgumentValidator;
import br.com.tw.util.TalkReader;
import br.com.tw.util.impl.ArgumentValidation;
import br.com.tw.util.impl.FileTalkReader;

/**
 * App for organize Talks in a conference
 *
 */
public class ConferenceSchedulerApp {
	
	private ArgumentValidator validator;
	private TalkReader talkReader;
	private Conference conference;
	
	
	
	/**
	 * Initialization of Application with path to file of Talks
	 * 
	 * @param pathToFile
	 * @throws FileNotFoundException
	 */
	public static void main(String[] pathToFile) throws FileNotFoundException {
		
		ArgumentValidator validator = new ArgumentValidation(pathToFile);
		TalkReader talkReader = new FileTalkReader(pathToFile[0]);
		
		ConferenceSchedulerApp conferenceSchedulerApp = new ConferenceSchedulerApp(validator, talkReader) ;
		conferenceSchedulerApp.execute();
	}
	
	public void execute() throws FileNotFoundException {
		validator.validate();
		List<Talk> talks = talkReader.readListOfTalk();
		
		conference = new Conference(talks);
		conference.organize();
	}

	public ConferenceSchedulerApp(ArgumentValidator validator, TalkReader talkReader) {
		this.validator = validator;
		this.talkReader = talkReader;
	}
	
	public String toString() {
		return conference.toString();
	}

}
