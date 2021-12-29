package com.jclscred.resource;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	private static final long serialVersionUID = 1L;

	private List<ValidationMessage> errors = new ArrayList<>();

	public ValidationError(Integer status, String msg, Long timestamp) {
		super(status, msg, timestamp);
	}

	public List<ValidationMessage> getErrors() {
		return errors;
	}

	public void addErros(String fieldName, String message) {
		errors.add(new ValidationMessage(fieldName, message));
	}

}
