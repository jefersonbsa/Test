package br.com.tw.util.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.tw.entity.Talk;
import br.com.tw.util.ApplicationConfig;
import br.com.tw.util.TalkReader;

public class FileTalkReader implements TalkReader{
	
	private static final String INVALID_DATA_FORMAT_USING_IN_TALK = "Invalid data format using in talk : ";
	private File file;
	List<Talk> talks = new ArrayList<>();
	
	public List<Talk> readListOfTalk(String filename) throws FileNotFoundException{
		this.file = new File(filename);
		convertFileToListOfTalk();
		return talks;
	}
	
	private void convertFileToListOfTalk() throws FileNotFoundException {
		
		try(Scanner scanner = new Scanner(file)){
			while(scanner.hasNext()) {
				Talk talk = readLineOfTalk(scanner.nextLine());
				talks.add(talk);
			}
		} 
	}

	private Talk readLineOfTalk(String line) {

		List<String> talkAtributes = parseLine(line);

		validateTalkAtributes(talkAtributes, line);

		String title = getTitle(talkAtributes);
		int time = getTimeWithCorretFormat(talkAtributes);

		return new Talk(title, time);
	}


	private int getTimeWithCorretFormat(List<String> talkAtributes) {
		String timeDescription = talkAtributes.get(2);
		int time;
		if (timeDescription.contains(ApplicationConfig.LIGHTNING_TALK)) {
			time = 5;
		} else {
			String[] separedTime = timeDescription.split(ApplicationConfig.TIME_TALK);
			time = Integer.parseInt(separedTime[0]);
		}
		return time;
	}

	private String getTitle(List<String> talkAtributes) {
		return talkAtributes.get(1);
	}

	private void validateTalkAtributes(List<String> talkAtributes, String line) {

		if (talkAtributes.isEmpty() || talkAtributes.size() > 3)
			throw new IllegalArgumentException(INVALID_DATA_FORMAT_USING_IN_TALK + line);

		if (!talkAtributes.get(0).equals(line))
			throw new IllegalArgumentException(INVALID_DATA_FORMAT_USING_IN_TALK + line);

		if (!talkAtributes.get(2).contains(ApplicationConfig.TIME_TALK) && !talkAtributes.get(2).contains(ApplicationConfig.LIGHTNING_TALK))
			throw new IllegalArgumentException(INVALID_DATA_FORMAT_USING_IN_TALK + line);
	}

	private List<String> parseLine(String line) {
		List<String> talkAtributes = new ArrayList<>();

		Pattern pattern = Pattern.compile(ApplicationConfig.PATTERN_INPUT_LINE_TALK);
		Matcher matcher = pattern.matcher(line);

		if (matcher.matches() && matcher.groupCount() > 0) {
			for (int i = 0; i <= matcher.groupCount(); i++) {
				talkAtributes.add(matcher.group(i));
			}
			return talkAtributes;
		}

		throw new IllegalArgumentException(INVALID_DATA_FORMAT_USING_IN_TALK + line);

	}

}
