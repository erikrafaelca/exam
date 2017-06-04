package com.uxp.exam.service;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
public class MessageByLocaleService {
	
	@Autowired
	private MessageSource messageSource;
	
	public String getMessage(String id, Object... params) {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage(id, params, locale);
	}

}
