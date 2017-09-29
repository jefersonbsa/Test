package br.com.tw.util;

import java.io.FileNotFoundException;
import java.util.List;

import br.com.tw.entity.Talk;

public interface TalkReader {
	
	List<Talk> readListOfTalk() throws FileNotFoundException;

}
