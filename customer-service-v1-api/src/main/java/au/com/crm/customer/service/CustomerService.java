/**
 * 
 */
package au.com.crm.customer.service;

import org.springframework.stereotype.Service;

import au.com.crm.customer.exception.CustomerNotFoundException;
import au.com.crm.customer.resources.CustomerResource;


@Service
public interface CustomerService {
	
	public CustomerResource createCustomer(CustomerResource request);
	
	public boolean deleteCustomer(Integer customerId) throws CustomerNotFoundException;
	
	public boolean updateCustomer(CustomerResource request) throws CustomerNotFoundException;
}
