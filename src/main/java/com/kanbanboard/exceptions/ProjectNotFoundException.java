package com.kanbanboard.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ProjectNotFoundException extends Exception {

	public ProjectNotFoundException(String message) {
		super(message);
	}
}
