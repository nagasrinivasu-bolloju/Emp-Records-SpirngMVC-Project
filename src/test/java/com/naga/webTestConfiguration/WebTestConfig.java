package com.naga.webTestConfiguration;

import java.util.Locale;
import java.util.Properties;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

public final class WebTestConfig {
	private WebTestConfig() {}
	
	public static SimpleMappingExceptionResolver exceptionResolver() {
	    SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
	
	    Properties exceptionMappings = new Properties();
	    exceptionMappings.put("java.lang.Exception", "error/400");
	    exceptionMappings.put("java.lang.NoHandlerFoundException", "error/404");
	    exceptionMappings.put("java.lang.RuntimeException", "error/500");
	    
	    exceptionResolver.setExceptionMappings(exceptionMappings);
	
	    Properties statusCodes = new Properties();
	
	    statusCodes.put("error/404", "404");
	    statusCodes.put("error/500", "500");
	    statusCodes.put("error/400", "400");
	
	    exceptionResolver.setStatusCodes(statusCodes);
	
	    return exceptionResolver;
	}
	
	public static LocaleResolver fixedLocaleResolver() {
        return new FixedLocaleResolver(Locale.ENGLISH);
    }
 
    public static ViewResolver jspViewResolver() {
        InternalResourceViewResolver viewResolver = 
                new InternalResourceViewResolver();
 
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/views/");
        viewResolver.setSuffix(".jsp");
 
        return viewResolver;
    }
}
