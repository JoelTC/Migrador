package com.novatronic.pscabas.gt.webcore.exceptios;

import java.util.ArrayList;
import java.util.List;

public class MigradorException extends Exception{
	
	private static final long serialVersionUID = 1L;

	private final String code;
	private final int responseCode;
	private final List<ErrorDTO> errorList = new ArrayList<>();
	
	public MigradorException(String code, int responseCode, String message) {
		super(message);
		this.code = code;
		this.responseCode = responseCode;
	}

	public MigradorException(String code, int responseCode, String message, List<ErrorDTO> errorList) {
		super(message);
		this.code = code;
		this.responseCode = responseCode;
		this.errorList.addAll(errorList);
	}

	public String getCode() {
		return code;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public List<ErrorDTO> getErrorList() {
		return errorList;
	}
}