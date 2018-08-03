package au.com.crm.customer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import au.com.crm.customer.constants.CustomerConstants;
import au.com.crm.customer.controller.CustomerController;
import au.com.crm.customer.exception.CustomerNotFoundException;
import au.com.crm.customer.resources.CreateCustomerResponse;
import au.com.crm.customer.resources.CustomerResource;
import au.com.crm.customer.resources.Response;
import au.com.crm.customer.service.CustomerService;

@RunWith(SpringRunner.class)
public class CustomerControllerTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerControllerTest.class);
	@InjectMocks
	CustomerController controller= new CustomerController(); 
	
	@Mock
	private CustomerService customerService;

	@Before
    public void setUp() {
		Mockito.when(customerService.createCustomer((CustomerResource)Mockito.anyObject())).thenReturn(CustomerTestHelper.getSaveResponse());
	}
	//Create Customer tests
	/**
	 * Assert Success status For CreateCustomer
	 */
	@Test
	public void assertCreateCustomerSuccess() {
		LOGGER.debug("Entering assertCreateCustomerSuccess");
		ResponseEntity<CreateCustomerResponse> responseEntity = controller.createCustomer(CustomerTestHelper.getCustomerRequest_CreateCustomer());
		Assert.assertEquals(CustomerConstants.SUCCESS_STATUS, responseEntity.getBody().getStatus());
		Assert.assertEquals(CustomerConstants.CREATE_MESSAGE, responseEntity.getBody().getMessage());
	}
	
	
	/**
	 * Assert Error status if CustomerId is not NULL 
	 * For Create Customer request both Customer Id and AddresssIds should be null 
	 */
	@Test
	public void assertInvalidRequestForCustomerIdError() {
		CustomerResource customerRequest = CustomerTestHelper.getCustomerRequest_CreateCustomer();
		customerRequest.setCustomerId(Integer.valueOf(1));
		ResponseEntity<CreateCustomerResponse> responseEntity = controller.createCustomer(customerRequest);
		Assert.assertEquals(CustomerConstants.ERROR_STATUS, responseEntity.getBody().getStatus());
		Assert.assertEquals(CustomerConstants.INVALID_REQUEST, responseEntity.getBody().getMessage());
	}
	
	/**
	 * Assert Error status if AddressId is not NULL 
	 * For Create Customer request both Customer Id and AddresssIds should be null 
	 */
	@Test
	public void assertInvalidReqeuestForAddressIdError() {
		CustomerResource customerRequest = CustomerTestHelper.getCustomerRequest_CreateCustomer();
		customerRequest.getAddresses().get(0).setAddressId(Integer.valueOf(1));
		ResponseEntity<CreateCustomerResponse> responseEntity = controller.createCustomer(customerRequest);
		Assert.assertEquals(CustomerConstants.ERROR_STATUS, responseEntity.getBody().getStatus());
		Assert.assertEquals(CustomerConstants.INVALID_REQUEST, responseEntity.getBody().getMessage());
	}
	
	//Update Customer Tests
	
	/**
	 * Assert the success of Update Customer call
	 * @throws CustomerNotFoundException
	 */
	@Test
	public void assertUpdateCustomerSuccess() throws CustomerNotFoundException{
		Mockito.when(customerService.updateCustomer((CustomerResource)Mockito.anyObject())).thenReturn(true);
		CustomerResource customerRequest = CustomerTestHelper.getCustomerRequest();
		ResponseEntity<Response> responseEntity = controller.updateCustomer(customerRequest);
		Assert.assertEquals(CustomerConstants.SUCCESS_STATUS, responseEntity.getBody().getStatus());
		Assert.assertEquals(CustomerConstants.UPDATE_MESSAGE, responseEntity.getBody().getMessage());
	}

	/**
	 * Error status  and Invalid Request message to be asserted if CustomerId of record to be udpated is NULL
	 * @throws CustomerNotFoundException
	 */
	@Test
	public void assertUpdateCustomerNullCustomerIdError() throws CustomerNotFoundException{
		Mockito.when(customerService.updateCustomer((CustomerResource)Mockito.anyObject())).thenReturn(true);
		CustomerResource customerRequest = CustomerTestHelper.getCustomerRequest();
		customerRequest.setCustomerId(null);
		ResponseEntity<Response> responseEntity = controller.updateCustomer(customerRequest);
		Assert.assertEquals(CustomerConstants.ERROR_STATUS, responseEntity.getBody().getStatus());
		Assert.assertEquals(CustomerConstants.INVALID_REQUEST, responseEntity.getBody().getMessage());
	}
	
	
	/**
	 * Error status to be asserted if record to be updated is not found in DB
	 * @throws CustomerNotFoundException
	 */
	@Test
	public void assertUpdateCustomer_RecordNotFoundError() throws CustomerNotFoundException{
		Mockito.when(customerService.updateCustomer((CustomerResource)Mockito.anyObject())).thenThrow(new CustomerNotFoundException(CustomerConstants.RECORD_NOT_FOUND));
		CustomerResource customerRequest = CustomerTestHelper.getCustomerRequest();
		ResponseEntity<Response> responseEntity = controller.updateCustomer(customerRequest);
		Assert.assertEquals(CustomerConstants.ERROR_STATUS, responseEntity.getBody().getStatus());
		Assert.assertEquals(CustomerConstants.RECORD_NOT_FOUND, responseEntity.getBody().getMessage());
	}
	
	//Delete customer Tests
	
	/**
	 * Check if delete request is successful
	 * @throws CustomerNotFoundException
	 */
	@Test
	public void assertDeleteCustomerSuccess() throws CustomerNotFoundException{
		Mockito.when(customerService.deleteCustomer(Integer.valueOf(1))).thenReturn(true);
		ResponseEntity<Response> responseEntity = controller.removeCustomer(Integer.valueOf(1));
		Assert.assertEquals(CustomerConstants.SUCCESS_STATUS, responseEntity.getBody().getStatus());
		Assert.assertEquals(CustomerConstants.DELETE_MESSAGE, responseEntity.getBody().getMessage());
	}
	
	/**
	 * Error status to be asserted if record to be deleted is not found in DB
	 * @throws CustomerNotFoundException
	 */
	@Test
	public void assertDeleteCustomer_RecordNotFoundError() throws CustomerNotFoundException{
		Mockito.when(customerService.deleteCustomer(Integer.valueOf(2))).thenThrow(new CustomerNotFoundException(CustomerConstants.RECORD_NOT_FOUND));
		ResponseEntity<Response> responseEntity = controller.removeCustomer(Integer.valueOf(2));
		Assert.assertEquals(CustomerConstants.ERROR_STATUS, responseEntity.getBody().getStatus());
		Assert.assertEquals(CustomerConstants.RECORD_NOT_FOUND, responseEntity.getBody().getMessage());
	}
	
}
