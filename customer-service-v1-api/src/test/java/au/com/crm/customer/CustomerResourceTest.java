package au.com.crm.customer;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import au.com.crm.customer.resources.AddressResource;
import au.com.crm.customer.resources.CustomerResource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerResourceTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerResourceTest.class);
	
	
	private static Validator validator;
	
	@Before
    public void setUp() {
        
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
	

 /**
  * Validation check for First Name 
  * Null and length check
  */
	@Test
	public void assertFirstNameValidationChecks() {
		LOGGER.debug("FirstName validation check");
		
		CustomerResource customerRequest= CustomerTestHelper.getCustomerRequest();
		customerRequest.setFirstName(null);
		Set<ConstraintViolation<CustomerResource>> violations = validator.validate(customerRequest);
		Assert.assertTrue("FirstName null violation", !violations.isEmpty());
		Iterator<ConstraintViolation<CustomerResource>> iterator =violations.iterator(); 
		Assert.assertEquals(iterator.next().getMessageTemplate(),"{null.customer.firstName}");
		
		//FirstName > 20 
		customerRequest.setFirstName("NamesNamesNamesNamesN");
		violations = validator.validate(customerRequest);
		LOGGER.debug("value of violations"+violations.isEmpty());
		Assert.assertTrue("FirstName greater than 20", !violations.isEmpty());
		
		//FirstName = 20
		customerRequest.setFirstName("NamesNamesNamesNames");
		violations = validator.validate(customerRequest);
		Assert.assertTrue("FirstName equal to 20", violations.isEmpty());
	}
	
	
	/**
	  * Validation check for Last Name 
	  * Null and length check
	  */
	@Test
	public void assertLastNameValidationChecks() {
		LOGGER.debug("LastName validation check");
		
		CustomerResource customerRequest= CustomerTestHelper.getCustomerRequest();
		customerRequest.setLastName(null);
		Set<ConstraintViolation<CustomerResource>> violations = validator.validate(customerRequest);
		Assert.assertTrue("LastName null violation", !violations.isEmpty());
		Iterator<ConstraintViolation<CustomerResource>> iterator =violations.iterator(); 
		Assert.assertEquals(iterator.next().getMessageTemplate(),"{null.customer.lastName}");
		
		//LastName > 20 
		customerRequest.setLastName("NamesNamesNamesNamesN");
		violations = validator.validate(customerRequest);
		LOGGER.debug("value of violations"+violations.isEmpty());
		Assert.assertTrue("LastName greater than 20", !violations.isEmpty());
		
		//LastName = 20
		customerRequest.setLastName("NamesNamesNamesNames");
		violations = validator.validate(customerRequest);
		Assert.assertTrue("FirstName equal to 20", violations.isEmpty());
	}	
	
	/**
	  * Validation check for Email
	  * Null and length check
	  */
	@Test
	public void assertEmailIdValidationChecks() {
		LOGGER.debug("AlternateEmailId validation check");
		
		CustomerResource customerRequest= CustomerTestHelper.getCustomerRequest();
		customerRequest.setEmailId(null);
		Set<ConstraintViolation<CustomerResource>> violations = validator.validate(customerRequest);
		Assert.assertTrue("EmailId null violation", !violations.isEmpty());
		Iterator<ConstraintViolation<CustomerResource>> iterator =violations.iterator(); 
		Assert.assertEquals(iterator.next().getMessageTemplate(),"{null.customer.emailId}");

		
		//EmailId > 50 
		customerRequest.setEmailId("NamesNamesNamesNamesNamesNamesNamesNames@NamesNames.com");
		violations = validator.validate(customerRequest);
		Assert.assertTrue("EmailId greater than 50", !violations.isEmpty());
		
		//EmailId = 50
		customerRequest.setEmailId("NamesNamesNamesNamesNamesNames@NamesNamesNames.com");
		violations = validator.validate(customerRequest);
		Assert.assertTrue("Emailid equal to 50", violations.isEmpty());
	}
	
	/**
	  * Validation check for AlternateEmailId
	  * Null and length check
	  */
	@Test
	public void assertAlternateEmailIdValidationChecks() {
		LOGGER.debug("AlternateEmailId validation check");
		
		CustomerResource customerRequest= CustomerTestHelper.getCustomerRequest();
		Set<ConstraintViolation<CustomerResource>> violations ;
		//EmailId > 50 
		customerRequest.setAlternateEmailId("NamesNamesNamesNamesNamesNamesNamesNames@NamesNames.com");
		violations = validator.validate(customerRequest);
		Assert.assertTrue("AlternateEmailId greater than 50", !violations.isEmpty());
		
		//EmailId = 50
		customerRequest.setAlternateEmailId("NamesNamesNamesNamesNamesNames@NamesNamesNames.com");
		violations = validator.validate(customerRequest);
		Assert.assertTrue("AlternateEmailid equal to 50", violations.isEmpty());

	
	}
	
	/**
	  * Format Validation check for EmailId
	  * 
	  */
	@Test
	public void assertEmailIdValid() {
		
		CustomerResource customerRequest= CustomerTestHelper.getCustomerRequest();
		customerRequest.setEmailId("abcabc");
		Set<ConstraintViolation<CustomerResource>> violations = validator.validate(customerRequest);
		
		LOGGER.debug("violations in EmailID {}",violations);
		Assert.assertTrue("EmailId not valid", !violations.isEmpty());
	}
	
	/**
	  * Format Validation check for EmailId
	  * 
	  */
	@Test
	public void assertAlternateEmailIdValid() {
		
		CustomerResource customerRequest= CustomerTestHelper.getCustomerRequest();
		customerRequest.setAlternateEmailId("abcabc");
		Set<ConstraintViolation<CustomerResource>> violations = validator.validate(customerRequest);
		Assert.assertTrue("AlternateEmailId not valid", !violations.isEmpty());
	}

	
	/**
	  * Validation check for UnitNumber
	  * Null and length check
	  */
	@Test
	public void assertUnitNumberValidationChecks() {
		LOGGER.debug("UnitNumber validation check");
		
		AddressResource addressRequest= CustomerTestHelper.getAddress(1);
		addressRequest.setUnitNumber(null);
		Set<ConstraintViolation<AddressResource>> violations = validator.validate(addressRequest);
		Assert.assertTrue("UnitNumber null violation", !violations.isEmpty());
		Iterator<ConstraintViolation<AddressResource>> iterator =violations.iterator(); 
		Assert.assertEquals(iterator.next().getMessageTemplate(),"{null.address.unitNumber}");
		
		//unitNumber > 5 
		addressRequest.setUnitNumber("12345a");
		violations = validator.validate(addressRequest);
		Assert.assertTrue("UnitNumber greater than 5", !violations.isEmpty());
		
		//unitNumber = 5
		addressRequest.setUnitNumber("12345");
		violations = validator.validate(addressRequest);
		System.out.println("violations in unit number "+violations);
		Assert.assertTrue("UnitNumber equal to 5", violations.isEmpty());
	}
	
	/**
	  * Validation check for StreetName
	  * Null and length check
	  */
	@Test
	public void assertStreetNameValidationChecks() {
		LOGGER.debug("StreetName validation check");
		
		AddressResource addressRequest= CustomerTestHelper.getAddress(1);
		addressRequest.setStreetName(null);
		Set<ConstraintViolation<AddressResource>> violations = validator.validate(addressRequest);
		Assert.assertTrue("StreetName null violation", !violations.isEmpty());
		Iterator<ConstraintViolation<AddressResource>> iterator =violations.iterator(); 
		Assert.assertEquals(iterator.next().getMessageTemplate(),"{null.address.streetName}");
		
		//StreetName > 20 
		addressRequest.setStreetName("StreetNameStreetName1");
		violations = validator.validate(addressRequest);
		Assert.assertTrue("StreetName greater than 20", !violations.isEmpty());
		
		//StreetName = 20
		addressRequest.setStreetName("StreetNameStreetName");
		violations = validator.validate(addressRequest);
		Assert.assertTrue("StreetName equal to 20", violations.isEmpty());
	}
	
	/**
	  * Validation check for Suburb
	  * Null and length check
	  */
	@Test
	public void assertSuburbValidationChecks() {
		LOGGER.debug("Suburb validation check");
		
		AddressResource addressRequest= CustomerTestHelper.getAddress(1);
		addressRequest.setSuburb(null);
		Set<ConstraintViolation<AddressResource>> violations = validator.validate(addressRequest);
		Assert.assertTrue("Suburb null violation", !violations.isEmpty());
		Iterator<ConstraintViolation<AddressResource>> iterator =violations.iterator(); 
		Assert.assertEquals(iterator.next().getMessageTemplate(),"{null.address.suburb}");
		
		//Suburb > 20 
		addressRequest.setSuburb("SuburbSuburbSuburbSub");
		violations = validator.validate(addressRequest);
		Assert.assertTrue("Suburb greater than 20", !violations.isEmpty());
		
		//Suburb = 20
		addressRequest.setSuburb("SuburbSuburbSuburbSu");
		violations = validator.validate(addressRequest);
		Assert.assertTrue("Suburb equal to 20", violations.isEmpty());
	}
	
	
	/**
	  * Validation check for PostCode
	  * Null and length check
	  */
	@Test
	public void assertPostCodeValidationChecks() {
		LOGGER.debug("Suburb validation check");
		
		AddressResource addressRequest= CustomerTestHelper.getAddress(1);
		addressRequest.setPostCode(null);
		Set<ConstraintViolation<AddressResource>> violations = validator.validate(addressRequest);
		Assert.assertTrue("PostCode null violation", !violations.isEmpty());
		Iterator<ConstraintViolation<AddressResource>> iterator =violations.iterator(); 
		Assert.assertEquals(iterator.next().getMessageTemplate(),"{null.address.postCode}");
		
		//PostCode > 10 
		addressRequest.setPostCode("PostCode123");
		violations = validator.validate(addressRequest);
		Assert.assertTrue("PostCode greater than 10", !violations.isEmpty());
		
		//PostCode = 10
		addressRequest.setPostCode("PostCode34");
		violations = validator.validate(addressRequest);
		Assert.assertTrue("PostCode equal to 10", violations.isEmpty());
	}
	
	/**
	  * Validation check for Country
	  * Null and length check
	  */
	@Test
	public void assertCountryValidationChecks() {
		LOGGER.debug("Country validation check");
		
		AddressResource addressRequest= CustomerTestHelper.getAddress(1);
		addressRequest.setCountry(null);
		Set<ConstraintViolation<AddressResource>> violations = validator.validate(addressRequest);
		Assert.assertTrue("Country null violation", !violations.isEmpty());
		Iterator<ConstraintViolation<AddressResource>> iterator =violations.iterator(); 
		Assert.assertEquals(iterator.next().getMessageTemplate(),"{null.address.country}");
		
		//Country > 20 
		addressRequest.setCountry("CountryCountryCountry");
		violations = validator.validate(addressRequest);
		Assert.assertTrue("Country greater than 20", !violations.isEmpty());
		
		//Country = 20
		addressRequest.setCountry("Country");
		violations = validator.validate(addressRequest);
		Assert.assertTrue("PostCode equal to 20", violations.isEmpty());
	}
	
	
	/**
	  * Validation check for AddressType
	  * Null and length check
	  */
	@Test
	public void assertAddressTypeInvalidValue() {
		LOGGER.debug("AddressType invalid value check");
		
		AddressResource addressRequest= CustomerTestHelper.getAddress(1);
		addressRequest.setAddressType("ALTERNATE");
		Set<ConstraintViolation<AddressResource>> violations = validator.validate(addressRequest);
		Assert.assertTrue("AddressType violation", !violations.isEmpty());
		Iterator<ConstraintViolation<AddressResource>> iterator =violations.iterator(); 
		Assert.assertEquals(iterator.next().getMessageTemplate(),"{invalid.address.addressType}");

	}
	
	/**
	  * Validation check for AddressType
	  * Null and length check
	  */
	@Test
	public void assertAddressTypeValidValueHOME() {
		LOGGER.debug("AddressType valid value check");
		AddressResource addressRequest= CustomerTestHelper.getAddress(1);
		Set<ConstraintViolation<AddressResource>> violations = validator.validate(addressRequest);
		Assert.assertTrue("AddressType violation", violations.isEmpty());

	}
	
	/**
	  * Validation check for AddressType
	  * Null and length check
	  */
	@Test
	public void assertAddressTypeValidValueOFFICE() {
		LOGGER.debug("AddressType valid value check");
		AddressResource addressRequest= CustomerTestHelper.getAddress(1);
		addressRequest.setAddressType("OFFICE");
		Set<ConstraintViolation<AddressResource>> violations = validator.validate(addressRequest);
		Assert.assertTrue("AddressType violation", violations.isEmpty());

	}
	
}
