package br.com.tw.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.tw.entity.Talk;

public class FileTalkReader {
	
	
	private File file;
	List<Talk> talks = new ArrayList<Talk>();
	
	public FileTalkReader(String filename) {
		this.file = new File(filename);
	}
	
	public List<Talk> readListOfTalk(){
		convertFileToListOfTalk();
		return talks;
	}
	
	private void convertFileToListOfTalk() {
		
		try(Scanner scanner = new Scanner(file)){
			while(scanner.hasNext()) {
				Talk talk = readLineOfTalk(scanner.nextLine());
				talks.add(talk);
			}
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("File Not Found");
		}
	}

	private Talk readLineOfTalk(String line) {

		List<String> talkAtributes = parseLine(line);

		validateTalkAtributes(talkAtributes, line);

		String title = getTitle(talkAtributes);;
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
			throw new IllegalArgumentException("Invalid data format using in talk : " + line);

		if (!talkAtributes.get(0).equals(line))
			throw new IllegalArgumentException("Invalid data format using in talk : " + line);

		if (!talkAtributes.get(2).contains("min") && !talkAtributes.get(2).contains("lightning"))
			throw new IllegalArgumentException("Invalid data format using in talk : " + line);
	}

	private List<String> parseLine(String line) {
		List<String> talkAtributes = new ArrayList<String>();

		Pattern pattern = Pattern.compile(ApplicationConfig.PATTERN_INPUT_LINE_TALK);
		Matcher matcher = pattern.matcher(line);

		if (matcher.matches() && matcher.groupCount() > 0) {
			for (int i = 0; i <= matcher.groupCount(); i++) {
				talkAtributes.add(matcher.group(i));
			}
			return talkAtributes;
		}

		throw new IllegalArgumentException("Invalid data format using in talk : " + line);

	}

}
