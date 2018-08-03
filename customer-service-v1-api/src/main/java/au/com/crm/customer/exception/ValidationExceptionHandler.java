package au.com.crm.customer.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Handles Validation exceptions
 *
 */
@Order(Ordered.HIGHEST_PRECEDENCE + 1)
@RestControllerAdvice
public class ValidationExceptionHandler {
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorBean> handleMethodArgumentNotValidException(MethodArgumentNotValidException maex) {
		BindingResult result = maex.getBindingResult();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(processFieldErrors(result.getAllErrors()));
	}
 
	private ErrorBean processFieldErrors(List<ObjectError> objectErrors) {

		ErrorBean errorResponse = new ErrorBean();
		List<Errors> errorList = new ArrayList<>();
		for (ObjectError objectError : objectErrors) {
			Errors data = new Errors();
			data.setErrorCode(objectError.getCode());
			data.setErrorMessage(objectError.getDefaultMessage());
			errorList.add(data);
		}
		errorResponse.setErrors(errorList);
		errorResponse.setErrorCategory("Validation Error");
		return errorResponse;
	}

}
