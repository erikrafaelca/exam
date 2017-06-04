package com.uxp.exam.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uxp.exam.exception.ExceptionInfo;

@ControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ExceptionInfo handleExceptionInfo(HttpServletRequest req, HttpServletRequest res, Exception e) {
		ExceptionInfo ei = new ExceptionInfo(null,e.getMessage());
		ei.setUrl(req.getRequestURI());		
		return ei;
	}
}