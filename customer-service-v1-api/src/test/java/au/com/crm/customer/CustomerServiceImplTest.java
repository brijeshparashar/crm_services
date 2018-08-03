package au.com.crm.customer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import au.com.crm.customer.entity.CustomerEntity;
import au.com.crm.customer.exception.CustomerNotFoundException;
import au.com.crm.customer.repository.CustomerRepository;
import au.com.crm.customer.resources.AddressResource;
import au.com.crm.customer.resources.CustomerResource;
import au.com.crm.customer.service.CustomerService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceImplTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImplTest.class);

	@Autowired
	private CustomerService customerService;
	@MockBean
	private CustomerRepository customerRepositoryMock;

	@Before
	public void setUp() {
		Mockito.when(customerRepositoryMock.save((CustomerEntity) Mockito.anyObject()))
				.thenReturn(CustomerTestHelper.getCustomerEntityResponse());
		Mockito.when(customerRepositoryMock.findOne(Integer.valueOf(1)))
				.thenReturn(CustomerTestHelper.getCustomerEntityResponse());
	}

	// Test cases - create Customer

	/**
	 * validate createCustomer Success Response
	 */
	@Test
	public void assertCreateCustomer() {
		LOGGER.debug("assert create");
		CustomerResource customerRequest = CustomerTestHelper.getCustomerRequest_CreateCustomer();
		CustomerResource customerResponse = customerService.createCustomer(customerRequest);
		LOGGER.debug("create response returned {}", customerResponse);
		Assert.assertNotNull("Entity Returned successfully", customerResponse);
		Assert.assertEquals(customerResponse, CustomerTestHelper.getSaveResponse());
	}

	/* Test cases - update Customer */
	/**
	 * validate update Customer Success
	 */
	@Test
	public void assertUpdateCustomerSuccess() throws CustomerNotFoundException {
		CustomerResource customerRequest = CustomerTestHelper.getCustomerRequest();
		Assert.assertTrue("Record Updated", customerService.updateCustomer(customerRequest));
	}

	/**
	 * validate update Service success when new address is added to existing
	 * customer
	 */
	@Test
	public void assertSuccessIfNewAddressAdded_UpdateCustomer() throws CustomerNotFoundException {
		CustomerResource customerRequest = CustomerTestHelper.getCustomerRequest();
		AddressResource addressRequest = CustomerTestHelper.getAddress_CreateCustomer(Integer.valueOf(100));
		customerRequest.getAddresses().add(addressRequest);
		Assert.assertTrue("Record Updated", customerService.updateCustomer(customerRequest));
	}

	/**
	 * validate Customer Record Not found exception is thrown when Customer to be
	 * updated is not in DB.
	 */
	@Test(expected = CustomerNotFoundException.class)
	public void assertExceptionWhenCustomerRecordNotFound_UpdateCustomer() throws CustomerNotFoundException {
		CustomerResource customerRequest = CustomerTestHelper.getCustomerRequest();
		customerRequest.setCustomerId(Integer.valueOf(2));
		customerService.updateCustomer(customerRequest);
	}

	/**
	 * validate Customer Record Not found exception is thrown when AddressIds of
	 * existing addresses in input request don't match with the ids in DB.
	 */
	@Test(expected = CustomerNotFoundException.class)
	public void assertExceptionIfAddressIdNotMatch_UpdateCustomer() throws CustomerNotFoundException {
		CustomerResource customerRequest = CustomerTestHelper.getCustomerRequest();
		customerRequest.getAddresses().get(0).setAddressId(Integer.valueOf(100));
		customerService.updateCustomer(customerRequest);
	}

	/**
	 * validate Customer Record Not found exception is thrown when Number of
	 * existing addresses doesnt match with the ones in DB.
	 */
	@Test(expected = CustomerNotFoundException.class)
	public void assertExceptionIfNoOfAddressNotMatch_UpdateCustomer() throws CustomerNotFoundException {
		CustomerResource customerRequest = CustomerTestHelper.getCustomerRequest();
		customerRequest.getAddresses().remove(0);
		customerService.updateCustomer(customerRequest);
	}

	// Test cases - Delete Customer
	/**
	 * validate Customer Record Not found exception is thrown when Customer is not found in DB. 
	 */
	@Test(expected = CustomerNotFoundException.class)
	public void assertExceptionIfCustomerRecordNotFound_DeleteCustomer() throws CustomerNotFoundException {
		customerService.deleteCustomer(Integer.valueOf(100));
	}
	
	/**
	 * validate Delete customer success  
	 */
	@Test
	public void assertDeleteCustomerSuccess() throws CustomerNotFoundException {
		Assert.assertTrue(customerService.deleteCustomer(Integer.valueOf(1)));
	}

}
