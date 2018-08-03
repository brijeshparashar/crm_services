package au.com.crm.customer.transformer;

import java.sql.Date;
import java.time.LocalDate;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import au.com.crm.customer.entity.AddressEntity;
import au.com.crm.customer.entity.CustomerEntity;
import au.com.crm.customer.resources.AddressResource;
import au.com.crm.customer.resources.CustomerResource;

@Component
public class CustomerTransformer implements Function<CustomerResource, CustomerEntity> {

	
	@Autowired
	private AddressTransformer addressTransformer;

	
	@Override
	public CustomerEntity apply(CustomerResource request) {
		
		CustomerEntity customerEntity = null;
		AddressEntity addressEntity = null;
		if ( request != null ) {
			customerEntity = new CustomerEntity();
			customerEntity.setCustomerId(request.getCustomerId());
			customerEntity.setFirstName(request.getFirstName());
			customerEntity.setLastName(request.getLastName());
			customerEntity.setEmailId(request.getEmailId());
			customerEntity.setAlternateEmailId(request.getAlternateEmailId());
			customerEntity.setAlternateEmailId(request.getAlternateEmailId());
			customerEntity.setCreatedDate(Date.valueOf(LocalDate.now()));
			customerEntity.setLastModifiedDate(null);
			
			for(AddressResource address: request.getAddresses()) {
				addressEntity = addressTransformer.apply(address);
				addressEntity.setCustomer(customerEntity);
				customerEntity.getAddresses().add(addressEntity);
			}
		}
		
		return customerEntity;
	}

}
