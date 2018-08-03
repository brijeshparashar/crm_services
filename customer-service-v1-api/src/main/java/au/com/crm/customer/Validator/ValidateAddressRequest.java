package au.com.crm.customer.Validator;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ TYPE, METHOD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = { AddressRequestValidator.class })
@Documented
/**
 * This annotation validates the incoming request for Address to check if Address Type is valid or not.
 * 
 */
public @interface ValidateAddressRequest {
	String message() default "{invalid.address.addressType}";

    @SuppressWarnings("rawtypes")
	Class[] groups() default {};

    Class<Payload>[] payload() default {};
}