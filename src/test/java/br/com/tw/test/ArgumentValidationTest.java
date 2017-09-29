package br.com.tw.test;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import br.com.tw.util.impl.ArgumentValidation;

public class ArgumentValidationTest {
	
	private ArgumentValidation argumentValidator  ;
	private String[] emptyArgs;
	private String[] manyArgs;
	private String[] notFoundArgs;
	private String[] fileEmptyArgs;

	@Before
	public void setup() {
		emptyArgs = new String[] {};
		manyArgs = new String[] {"file2", "file2"};
		notFoundArgs = new String[] {"fileNotFound"};
		fileEmptyArgs = new String[] {"/input_file_empty"};
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWithEmptyArgs_shouldGenerateException() {
		argumentValidator = new ArgumentValidation(emptyArgs);
		argumentValidator.validate();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWithManyArgs_shouldGenerateException() {
		argumentValidator = new ArgumentValidation(manyArgs);
		argumentValidator.validate();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWithFileNotFoundArgs_shouldGenerateException() {
		argumentValidator = new ArgumentValidation(notFoundArgs);
		argumentValidator.validate();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWithFileEmptyArgs_shouldGenerateException() {
		String[] filename = { new File("src/test/resource" + fileEmptyArgs).getAbsoluteFile().getPath()};
		argumentValidator = new ArgumentValidation(filename);
		argumentValidator.validate();
	}

}
