package au.com.crm.customer.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import au.com.crm.customer.constants.CustomerConstants;
import au.com.crm.customer.exception.CustomerNotFoundException;
import au.com.crm.customer.resources.AddressResource;
import au.com.crm.customer.resources.CreateCustomerResponse;
import au.com.crm.customer.resources.CustomerResource;
import au.com.crm.customer.resources.Response;
import au.com.crm.customer.service.CustomerService;
/*
 * Controller Class for Customer
 */
@RestController
@RequestMapping(value = CustomerConstants.PATH_CUSTOMER_CONTROLLER)
public class CustomerController {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerService customerService; 

	/**
	 * Controller method for CreateCustomer
	 * @param request Object of CustomerRequest
	 * @return ResponseEntity<Response>
	 */
	@RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CreateCustomerResponse> createCustomer(@RequestBody @Valid CustomerResource request) {
		LOGGER.debug("Create Customer Service : {}", request);
		
		//Check if request is valid, create customer request should not have non-NULL CustomerId/AddressId
		if(!isCreateCustomerRequestValid(request)){
			LOGGER.error("Request is invalid: Input Request for createCustomer cannot have CustomerId or AddressId");
			return new ResponseEntity<>(new CreateCustomerResponse(CustomerConstants.ERROR_STATUS,CustomerConstants.INVALID_REQUEST,null), HttpStatus.BAD_REQUEST);
		}
		/*Create Service Call returns back Customer and Address ids in response*/ 
		CustomerResource customerResource = customerService.createCustomer(request);
		return new ResponseEntity<>(new CreateCustomerResponse(CustomerConstants.SUCCESS_STATUS,CustomerConstants.CREATE_MESSAGE,customerResource), HttpStatus.OK);
	}

	
	/**
	 * Controller method for Delete Customer 
	 * @param customerId
	 * @return ResponseEntity<Response>
	 */
	@RequestMapping(value = "{customerId}",method = RequestMethod.DELETE)
	public ResponseEntity<Response> removeCustomer(@PathVariable Integer customerId) {
		LOGGER.debug("Customer remove Service : {}", customerId);
		try {
			/*Delete Service call*/
			customerService.deleteCustomer(customerId);
		
		return new ResponseEntity<>(new Response(CustomerConstants.SUCCESS_STATUS,CustomerConstants.DELETE_MESSAGE), HttpStatus.OK);
		}catch(CustomerNotFoundException ex) {
			LOGGER.error("Customer Not Found : Exception occured while deleting the customer : {}", customerId);
			return new ResponseEntity<>(new Response(CustomerConstants.ERROR_STATUS,ex.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	
	/**
	 * Controller method for UpdateCustomer
	 * @param request Object of CustomerRequest
	 * @return ResponseEntity<Response>
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Response> updateCustomer(@RequestBody @Valid CustomerResource request) {
		LOGGER.debug("Customer Service Update: {}", request);
		try{
			
			/*IF Customer Id is not null then proceed to updateCustomer call else throw Bad request error.*/
			if(null!=request.getCustomerId()) {
				/*Update Service call*/
				customerService.updateCustomer(request);
				return new ResponseEntity<>(new Response(CustomerConstants.SUCCESS_STATUS,CustomerConstants.UPDATE_MESSAGE), HttpStatus.OK);
			}else {
				LOGGER.error("CustomerId cannot be null for UpdateCustomer");
				return new ResponseEntity<>(new Response(CustomerConstants.ERROR_STATUS,CustomerConstants.INVALID_REQUEST), HttpStatus.BAD_REQUEST);
			}
		}catch(CustomerNotFoundException ex) {
			LOGGER.error("Customer Not Found : Exception occured while updating Customer : {}", ex.getMessage());
			return new ResponseEntity<>(new Response(CustomerConstants.ERROR_STATUS,ex.getMessage()), HttpStatus.NOT_FOUND);
		}
	}
	
	
	/**
	 * This method checks if request is valid or not. 
	 * i.e. if Customer Id and AddressIds are null for createCustomer Request
	 * return false if any of them are non-null
	 * 
	 * @param request Object of CustomerRequest
	 * @return boolean
	 */
	private boolean isCreateCustomerRequestValid(CustomerResource request) {
		if(null!=request.getCustomerId()) {
			return false;
		}
		for(AddressResource address: request.getAddresses()) {
			if(null!=address.getAddressId()) {
				return false;
			}
		}
		return true;
	}

}
