package com.acodes.api.error;



import com.acodes.api.model.ErrorMessage;

import javax.persistence.EntityNotFoundException;
import javax.validation.ValidationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalErrorHandler {

	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ValidationException.class)
	ErrorMessage exceptionHandler(ValidationException validationException) {
		return new ErrorMessage("400",validationException.getMessage());
	}
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(EntityNotFoundException.class)
	ErrorMessage exceptionHandler(EntityNotFoundException validationException) {
		return new ErrorMessage("404",validationException.getMessage());
	}
}
