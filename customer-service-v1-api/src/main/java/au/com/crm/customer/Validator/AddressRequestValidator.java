package au.com.crm.customer.Validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.crm.customer.constants.AddressType;
import au.com.crm.customer.resources.AddressResource;

public class AddressRequestValidator implements ConstraintValidator<ValidateAddressRequest, AddressResource> {

	private static final Logger	LOGGER	= LoggerFactory.getLogger(AddressRequestValidator.class);
	
	@Override
	public void initialize(ValidateAddressRequest arg0) {
		LOGGER.debug("Initializing the validator for Address request");
	}

	@Override
	public boolean isValid(AddressResource request, ConstraintValidatorContext context) {
		return isValidAddressType(request, context) ;
	}

	
	private boolean isValidAddressType(AddressResource request, ConstraintValidatorContext context) {
		LOGGER.debug("Validating AddressType");
		
		if ( AddressType.fromCode(request.getAddressType()) == null ) {
			context.buildConstraintViolationWithTemplate("{invalid.address.addressType}").addConstraintViolation();
			return false;
		}
		
		return true;
	}

}
