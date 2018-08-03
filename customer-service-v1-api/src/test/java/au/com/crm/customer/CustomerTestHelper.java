package au.com.crm.customer;

import java.sql.Date;
import java.time.LocalDate;

import au.com.crm.customer.entity.AddressEntity;
import au.com.crm.customer.entity.CustomerEntity;
import au.com.crm.customer.resources.AddressResource;
import au.com.crm.customer.resources.CustomerResource;

public class CustomerTestHelper {
		
	public static CustomerResource getCustomerRequest() {
		CustomerResource customerRequest = new CustomerResource();
		customerRequest.setCustomerId(Integer.valueOf(1));
		customerRequest.setFirstName("John");
		customerRequest.setLastName("Smith");
		customerRequest.setEmailId("john.smith@gmail.com");
		customerRequest.setAlternateEmailId("johns@hotmail.com");
		customerRequest.getAddresses().add(getAddress(Integer.valueOf(1)));
		customerRequest.getAddresses().add(getAddress(Integer.valueOf(2)));
		
		return customerRequest; 
	}
	
	public static AddressResource getAddress(Integer id) {
		AddressResource addressRequest = new AddressResource();
		String idStr = String.valueOf(id);
		addressRequest.setAddressId(id);
		addressRequest.setUnitNumber(idStr+"a");
		addressRequest.setStreetName("Street"+idStr);
		addressRequest.setSuburb("Suburb"+idStr);
		addressRequest.setCountry("Country"+idStr);
		addressRequest.setPostCode("200"+id);
		addressRequest.setAddressType("HOME");
		
		
		return addressRequest; 
		
	}
 	
	public static CustomerEntity getCustomerEntityResponse() {
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setCustomerId(Integer.valueOf(1));
		customerEntity.setFirstName("John");
		customerEntity.setLastName("Smith");
		customerEntity.setEmailId("john.smith@gmail.com");
		customerEntity.setAlternateEmailId("johns@hotmail.com");
		customerEntity.setCreatedDate(Date.valueOf(LocalDate.now()));
		
		AddressEntity addressEntity1 = getAddressEntity(Integer.valueOf(1));
		addressEntity1.setCustomer(customerEntity);
		AddressEntity addressEntity2 = getAddressEntity(Integer.valueOf(2));
		addressEntity2.setCustomer(customerEntity);
		customerEntity.getAddresses().add(addressEntity1);
		customerEntity.getAddresses().add(addressEntity2);
		
		return customerEntity; 
	}
	
	public static AddressEntity getAddressEntity(Integer id) {
		AddressEntity addressEntity = new AddressEntity();
		String idStr = String.valueOf(id);
		addressEntity.setAddressId(id);
		addressEntity.setUnitNumber(idStr+"a");
		addressEntity.setStreetName("Street"+idStr);
		addressEntity.setSuburb("Suburb"+idStr);
		addressEntity.setCountry("Country"+idStr);
		addressEntity.setPostCode("200"+id);
		addressEntity.setAddressType("HOME");
		return addressEntity; 
	}
	
	public static CustomerResource getSaveResponse() {
		CustomerResource response = new CustomerResource();
		response.setCustomerId(1);
		response.setFirstName("John");
		response.setLastName("Smith");
		response.setEmailId("john.smith@gmail.com");
		response.setAlternateEmailId("johns@hotmail.com");
		response.getAddresses().add(getAddress_CreateCustomer(Integer.valueOf(1)));
		response.getAddresses().get(0).setAddressId(Integer.valueOf(1));
		response.getAddresses().add(getAddress_CreateCustomer(Integer.valueOf(2)));
		response.getAddresses().get(1).setAddressId(Integer.valueOf(2));
		return response;
	}
	
	public static CustomerResource getCustomerRequest_CreateCustomer() {
		CustomerResource customerRequest = new CustomerResource();
		customerRequest.setFirstName("John");
		customerRequest.setLastName("Smith");
		customerRequest.setEmailId("john.smith@gmail.com");
		customerRequest.setAlternateEmailId("johns@hotmail.com");
		customerRequest.getAddresses().add(getAddress_CreateCustomer(Integer.valueOf(1)));
		customerRequest.getAddresses().add(getAddress_CreateCustomer(Integer.valueOf(2)));
		
		return customerRequest; 
	}
	
	public static AddressResource getAddress_CreateCustomer(Integer id) {
		AddressResource addressRequest = new AddressResource();
		String idStr = String.valueOf(id);
		addressRequest.setUnitNumber(idStr+"a");
		addressRequest.setStreetName("Street"+idStr);
		addressRequest.setSuburb("Suburb"+idStr);
		addressRequest.setCountry("Country"+idStr);
		addressRequest.setPostCode("200"+id);
		addressRequest.setAddressType("HOME");
		
		
		return addressRequest; 
		
	}
		
}
