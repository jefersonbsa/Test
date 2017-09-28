package br.com.tw;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import br.com.tw.app.ConferenceSchedulerApp;
import br.com.tw.util.ApplicationConfig;
import br.com.tw.util.ArgumentValidation;
import br.com.tw.util.FileTalkReader;

public class ConferenceSchedulerAppTest {
	
	@Test
	public void testwithInputFile_shouldCreateOneFile_resultTwoTrack() throws IOException {
		testwithInpuFile("/input_file_conference_with_two_track");
	}
	
	@Test
	public void testwithInputFile_shouldCreateOneFile_resultOneTrack() throws IOException {
		testwithInpuFile("/input_file_conference_with_one_track");
	}
	
	@Test
	public void testwithInputFile_shouldCreateOneFile_resultEarlyNetworkEvent() throws IOException {
		testwithInpuFile("/input_file_conference_with_early_netwok");
	}
	
	private void testwithInpuFile(String pathToFile) throws IOException {
		String filename = new File("src/test/resource/" + pathToFile).getAbsoluteFile().getPath();
		String[] args = {filename};
		
		ArgumentValidation validator = new ArgumentValidation(args);
		FileTalkReader talkReader = new FileTalkReader(filename);
		
		ConferenceSchedulerApp conferenceSchedulerApp = new ConferenceSchedulerApp(validator, talkReader) ;
		conferenceSchedulerApp.run();
		
		
		assertTrue(contentEquals(expectedResultFilename(pathToFile),conferenceSchedulerApp.toString()));
		
	}
	
	private static boolean contentEquals(String expectedFile,String resultString) throws FileNotFoundException {
		StringBuilder resultFile = new StringBuilder();
		StringBuilder resultOutputString = new StringBuilder();
		String filename = new File("src/test/resource/" + expectedFile).getAbsoluteFile().getPath() ;
		Scanner scannerFile = new Scanner(new File(filename));
		Scanner scannerString = new Scanner(resultString);
		while(scannerFile.hasNext()) {
			resultFile.append(scannerFile.nextLine());
		}
		while(scannerString.hasNext()) {
			resultOutputString.append(scannerString.nextLine());
		}
		resultOutputString.append(ApplicationConfig.NEWLINE);
		
		scannerFile.close();
		scannerString.close();
		
		return scannerFile.toString().equals(scannerString.toString());
	}
	
	private String expectedResultFilename(String expectedFile) {
		return expectedFile + "_expected";
	}

}
