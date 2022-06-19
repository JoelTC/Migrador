package com.novatronic.pscabas.gt.webcore.domains.responses;

import java.io.Serializable;

public class Response<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String Statuts;
	private String code;
	private String message;
	private T data;
	
	public Response(String statuts, String code, String message) {
		Statuts = statuts;
		this.code = code;
		this.message = message;
	}
	
	public Response(String statuts, String code, String message, T data) {
		Statuts = statuts;
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public String getStatuts() {
		return Statuts;
	}
	
	public void setStatuts(String statuts) {
		Statuts = statuts;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public T getData() {
		return data;
	}
	
	public void setData(T data) {
		this.data = data;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}