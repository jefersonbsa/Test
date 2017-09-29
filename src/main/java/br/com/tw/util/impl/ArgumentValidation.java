package br.com.tw.util.impl;

import java.io.File;

import br.com.tw.util.ArgumentValidator;

public class ArgumentValidation implements ArgumentValidator {
	
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
	
	private void validateArgumentNotFound() {
		if (args.length < 1) throw new IllegalArgumentException("Argument not found");
	}

}
