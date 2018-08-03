package au.com.crm.customer.exception;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionsHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionsHandler.class);

	/**
	 * This method handles Exceptions of type IllegalArgumentException,
	 * InvocationTargetException.
	 * 
	 * @param Exception
	 * @return ResponseEntity<Errors>
	 */
	@ExceptionHandler({ Exception.class, SQLException.class })
	public ResponseEntity<Errors> handleSystemException(Exception se) {

		LOG.error("Exception occured :: {}", se);


		Errors error = new Errors();
		error.setErrorCode("500");
		error.setErrorMessage("Application Error");

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}

}
