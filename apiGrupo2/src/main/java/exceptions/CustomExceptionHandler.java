package exceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice //tratamento de exceções, atua em todos os controlladores
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<String> erros = new ArrayList<>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			erros.add(error.getDefaultMessage());
		} //Class FieldError -- pesquisar sobre

		ErrorResponse erro = new ErrorResponse(status.value(), "Preencha todos os campos", erros);

		return super.handleExceptionInternal(ex, erro, headers, status, request);
	}
	
	@ExceptionHandler(MyEntityNotFoundException.class)
	protected ResponseEntity<Object> handleMyEntityNotFoundException(MyEntityNotFoundException ex) {
		return ResponseEntity.notFound().build();
	}

	@ExceptionHandler(ConstraintViolationException.class)
	protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
		List<String> errors = new ArrayList<>();
		Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
		for (ConstraintViolation<?> violation : violations) {
			String mensagem = violation.getMessage();
			String campo = violation.getPropertyPath().toString();
			String nomeCampo = campo.substring(campo.lastIndexOf('.') + 1);
			errors.add("Campo '" + nomeCampo + "': " + mensagem);
		}

		ErrorResponse erro = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Erro de validação", errors);

		return handleExceptionInternal(ex, erro, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
//    @ExceptionHandler(Exception.class)
//    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
//        List<String> details = new ArrayList<>();
//        details.add(ex.getLocalizedMessage());
//        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
//        ErroResponse error = new ErroResponse(httpStatus.value(), "Erro Interno no Servidor", details);
//
//        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
