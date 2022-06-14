package com.novatronic.pscabas.gt.webcore.exceptios;

import java.util.Arrays;

import org.springframework.http.HttpStatus;


public class NullPointerException extends MigradorException{
	private static final long serialVersionUID = 1L;

	public NullPointerException(String code, String message) {
		super(code, HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
	}

	public NullPointerException(String code, String message, ErrorDTO data) {
		super(code, HttpStatus.INTERNAL_SERVER_ERROR.value(), message, Arrays.asList(data));
	}
}
