package com.naga.controller;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionHandlingController {
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value=Exception.class)
	public String nullPointer(Model m)
	{
		System.out.println("Inside Exception handing class.. null pointer exception occured.");
		m.addAttribute("errorMsg","Internal Server Error Occured!!");
		return "error";
	}
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoHandlerFoundException.class)
    public String handleError404(Model m)   {
		System.out.println("Inside Exception handing class.. Nohandler found exception occured.");
		m.addAttribute("errorMsg","Sorry Required Page Not Found!!!");
            return "error";
    }
	
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException()   {
		System.out.println("Inside Exception handing class.. runtime exception occured.");
		//m.addAttribute("errorMsg","Sorry Required Page Not Found!!!");
            return "error";
    }
}
