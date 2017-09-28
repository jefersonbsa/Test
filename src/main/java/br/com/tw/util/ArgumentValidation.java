package br.com.tw.util;

import java.io.File;

public class ArgumentValidation {
	
	private String[] args;
	
	public ArgumentValidation(String[] args) {
		this.args = args;
	}
	

	public void validate() {
		
		
		validateArgumentNotFound();
		validateManyArgumentReceive(args);
		
		File file = new File(args[0]);
		
		validateFileNotFound(file);
		validateFileIsEmpty(file);
		
	}

	private void validateFileIsEmpty(File file) {
		if (file.length() == 0) {
			throw new IllegalArgumentException("File is empty.");
		}
	}

	private void validateFileNotFound(File file) {
		if (!file.exists()) {
			throw new IllegalArgumentException("File not found.");
		}
	}

	private void validateManyArgumentReceive(String[] args) {
		if (args.length > 1) {
			throw new IllegalArgumentException("Many argument receive.");
		}
	}
	
	public void validateArgumentNotFound() {
		if (args.length < 1) throw new IllegalArgumentException("Argument not found");
	}

}
