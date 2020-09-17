package br.com.jopaulo.tarefas.infrastructure.web;

import org.springframework.data.rest.core.RepositoryConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.jopaulo.tarefas.domain.task.DuplicateTaskException;

@RestControllerAdvice
public class WebRequestExceptionHandler {

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ResteResponseError handlerException(RepositoryConstraintViolationException e) {
		return ResteResponseError.fromValidationError(e.getErrors());
	}
	
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ResteResponseError handlerException(DuplicateTaskException e) {
		return ResteResponseError.fromMessage(e.getMessage());
	}
}
