package com.uxp.exam.exception;

import java.io.Serializable;

public class ExceptionInfo implements Serializable {
	private static final long serialVersionUID = -2905373437973829993L;
	private String url;
	private String message;
	
	public ExceptionInfo() {}
	
	public ExceptionInfo(String url, String msg) {
		this.url = url;
		this.message = msg;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}