package au.com.crm.customer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import au.com.crm.customer.entity.CustomerEntity;
import au.com.crm.customer.resources.CustomerResource;
import au.com.crm.customer.transformer.CustomerTransformer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerTransformerTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerTransformerTest.class);
	
	@Autowired
	private CustomerTransformer customerTransformer;
	
	private CustomerEntity customerEntity;
	
	@Before
    public void setUp() {
        customerEntity = CustomerTestHelper.getCustomerEntityResponse();
    }
 	
	@Test
	public void customerTransformerApply() {
		LOGGER.debug("Testing apply method");
		CustomerEntity customerEntityResult = null;
		CustomerResource customerRequest= CustomerTestHelper.getCustomerRequest();
		customerEntityResult = customerTransformer.apply(customerRequest);
		Assert.assertNotNull("Entity Returned successfully", customerEntityResult);
		Assert.assertEquals(customerEntity, customerEntityResult);
	}
	
	@Test
	public void customerTransformerApplyWithNullInput() {
		LOGGER.debug("Testing apply method with null input");
		CustomerEntity customerEntityResult = null;
		customerEntityResult = customerTransformer.apply(null);
		Assert.assertNull("Entity Returned null", customerEntityResult);
	}

}
