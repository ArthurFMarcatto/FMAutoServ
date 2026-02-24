package br.com.fmautoserv.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import br.com.fmautoserv.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError> handleValidationErrors(MethodArgumentNotValidException ex) {

		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
				.collect(Collectors.toList());

		ApiError error = new ApiError(HttpStatus.BAD_REQUEST.value(), "Validation Error",
				"Erro de validação nos campos", errors);

		return ResponseEntity.badRequest().body(error);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiError> handleNotFound(ResourceNotFoundException ex) {

		ApiError error = new ApiError(HttpStatus.NOT_FOUND.value(), "Not Found", ex.getMessage(), null);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError> handleGeneric(Exception ex) {

		ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Error",
				"Erro inesperado no servidor", null);

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}
}
