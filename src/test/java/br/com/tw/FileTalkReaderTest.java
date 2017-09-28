package br.com.tw;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import br.com.tw.entity.Talk;
import br.com.tw.util.FileTalkReader;

public class FileTalkReaderTest {
	
	private FileTalkReader fileReader;

	@Test
	public void testwithInputFile_shouldCreateListOfTalk() throws IOException {
		testwithInpuFile("/input_conference_file", 19);
	}
	
	private void testwithInpuFile(String pathToFile, int numberOfLine) throws IOException {
		String filename = new FileTalkReaderTest().getClass().getResource(pathToFile).getFile();
		fileReader = new FileTalkReader(filename);
		List<Talk> talks = fileReader.readListOfTalk();
		assertThat(talks.size(), equalTo(numberOfLine));
	}

	
}
