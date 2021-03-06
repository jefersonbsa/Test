package br.com.tw.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import br.com.tw.entity.Talk;
import br.com.tw.util.impl.FileTalkReader;

public class FileTalkReaderTest {
	
	private FileTalkReader fileReader;

	@Test
	public void testwithInputFile_shouldCreateListOfTalk() throws IOException {
		testwithInpuFile("/input_conference_file", 19);
	}
	
	private void testwithInpuFile(String pathToFile, int numberOfLine) throws IOException {
		String filename = new File("src/test/resource" + pathToFile).getAbsoluteFile().getPath();
		fileReader = new FileTalkReader();
		List<Talk> talks = fileReader.readListOfTalk(filename);
		assertThat(talks.size(), equalTo(numberOfLine));
	}

	
}
