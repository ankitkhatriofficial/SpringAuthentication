package com.SpringAuthentication.handler;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @author Ankit Khatri
 */

/* Class to control Unexpected exceptions while the application is ruuning */
@ControllerAdvice
public class GlobalExceptionsHandler {

	/* Handles 404 error and redirect to 404 JSP webpage */
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = { NoHandlerFoundException.class, HttpRequestMethodNotSupportedException.class })
	public ModelAndView handle404() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("access/404.jsp");
		return mav;
	}

	/* Handles 500 error and redirect to 500 JSP webpage */
	@ExceptionHandler(RuntimeException.class)
	public ModelAndView myError() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("access/500.jsp");
		return mav;
	}

	/* InitBinder to trim all the inputed strings provided by the user */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
		binder.registerCustomEditor(String.class, stringtrimmer);
	}

}
