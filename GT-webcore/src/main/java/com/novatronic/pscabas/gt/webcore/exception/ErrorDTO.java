package com.novatronic.pscabas.gt.webcore.exception;

import java.io.Serializable;

public class ErrorDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private String name;
	private String value;

	public ErrorDTO(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
