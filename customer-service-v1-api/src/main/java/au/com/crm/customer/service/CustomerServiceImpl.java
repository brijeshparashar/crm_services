package au.com.crm.customer.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import au.com.crm.customer.constants.CustomerConstants;
import au.com.crm.customer.entity.AddressEntity;
import au.com.crm.customer.entity.CustomerEntity;
import au.com.crm.customer.exception.CustomerNotFoundException;
import au.com.crm.customer.repository.CustomerRepository;
import au.com.crm.customer.resources.AddressResource;
import au.com.crm.customer.resources.CustomerResource;
import au.com.crm.customer.transformer.CustomerTransformer;

/**
 * CustomerServiceImpl class
 */
@Component
public class CustomerServiceImpl implements CustomerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CustomerTransformer customerTransformer;
	
	public CustomerServiceImpl() {
		
	}
	 
	/**
	 * Create Customer Service Method
	 * @param request 
	 * @return CustomerResponse
	 */
	@Override
	public CustomerResource createCustomer(CustomerResource request) {
		
		LOGGER.debug("Entering Create Service Call");
		CustomerEntity customerEntity= customerTransformer.apply(request);
		
//		LOGGER.debug("Customer to be saved : {}", customerEntity);
		customerEntity = customerRepository.save(customerEntity);
		
		return populateCustomerResponse(customerEntity);
	}

	
	/**
	 * Delete Customer Service Method
	 * CustomerNotFoundException will be thrown if record to be deleted not found in Database 
	 * @param request 
	 * @return boolean
	 */
	@Override
	public boolean deleteCustomer(Integer customerId) throws CustomerNotFoundException {
		
		CustomerEntity custEntity = customerRepository.findOne(customerId);
		if(custEntity!=null) {
			customerRepository.delete(custEntity);
		}else {
			throw new CustomerNotFoundException(CustomerConstants.RECORD_NOT_FOUND);
		}
		return true;
	}
	
	/**
	 * Update Customer Service Method
	 * CustomerNotFoundException will be thrown if record to be updated not found in Database 
	 * @param request 
	 * @return boolean
	 */
	@Override
	public boolean updateCustomer(CustomerResource request) throws CustomerNotFoundException {
		
		/*Transforming the input Request into Customer Entity*/
		CustomerEntity customerEntity = customerTransformer.apply(request);

		/*getting the Customer record from database*/
		CustomerEntity custEntityFromDB = customerRepository.findOne(request.getCustomerId());
		/*Throw exception if record to be updated does not exists in DB */
		if(custEntityFromDB==null) {
			throw new CustomerNotFoundException(CustomerConstants.RECORD_NOT_FOUND);
		}
		
		/*Address Ids from both entities i.e. from Input Request and from DB should match, if they are not null,
		 *else update won't happen and throw Exception CustomerRecordNotFound*/
		List<Integer> addressIdListDB = getAddressIds(custEntityFromDB);
		List<Integer> addressIdListRequest = getAddressIds(customerEntity);
		if(addressIdListDB.size()!=addressIdListRequest.size() || !addressIdListDB.containsAll(addressIdListRequest)) {
			throw new CustomerNotFoundException(CustomerConstants.RECORD_NOT_FOUND);
		}

		/*set the lastModifiedDate and copy the createdDate from record found from DB */
		customerEntity.setLastModifiedDate(Date.valueOf(LocalDate.now()));
		customerEntity.setCreatedDate(custEntityFromDB.getCreatedDate());
		
		/*Repository call to save as Ids are present for both Customer and Address objects, it would update the records in DB*/
		customerRepository.save(customerEntity);
		return true ;
	}

	/**
	 * This method will get all the non-null AddressIds and return as a List
	 * @param customerEntity
	 * @return List<Integer>
	 */
	private List<Integer> getAddressIds(CustomerEntity customerEntity) {
		List<Integer> addressIdList = new ArrayList<>();
		for(AddressEntity addressEntity : customerEntity.getAddresses()) {
			if(null!= addressEntity.getAddressId()) {
				addressIdList.add(addressEntity.getAddressId());
			}
		}
		return addressIdList;
	}
	
	/**
	 * convert CustomerEntity into CustomerResponse
	 * @param customerEntity
	 * @return
	 */
	private CustomerResource populateCustomerResponse(CustomerEntity customerEntity) {
		CustomerResource resource =null;
		AddressResource address = null ; 
		if(null!=customerEntity) {
			resource = new CustomerResource();
			resource.setCustomerId(customerEntity.getCustomerId());
			resource.setFirstName(customerEntity.getFirstName());
			resource.setLastName(customerEntity.getLastName());
			resource.setEmailId(customerEntity.getEmailId());
			resource.setAlternateEmailId(customerEntity.getAlternateEmailId());
			for(AddressEntity addressEntity : customerEntity.getAddresses()) {
				address= new AddressResource();
				address.setAddressId(addressEntity.getAddressId());
				address.setUnitNumber(addressEntity.getUnitNumber());
				address.setStreetName(addressEntity.getStreetName());
				address.setSuburb(addressEntity.getSuburb());
				address.setPostCode(addressEntity.getPostCode());
				address.setCountry(addressEntity.getCountry());
				address.setAddressType(addressEntity.getAddressType());
				resource.getAddresses().add(address);
			}
		}
		return resource;
	}
}
