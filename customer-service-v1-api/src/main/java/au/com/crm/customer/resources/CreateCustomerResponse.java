package au.com.crm.customer.resources;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CreateCustomerResponse extends Response{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7152733834023152856L;

	private CustomerResource customerResource;

	public CreateCustomerResponse(String status,String message, CustomerResource customerResource) {
		this.status = status;
		this.message= message;
		this.customerResource= customerResource;
	}
	
		
	public CustomerResource getCustomerResource() {
		return customerResource;
	}


	public void setCustomerResource(CustomerResource customerResource) {
		this.customerResource = customerResource;
	}


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}	
}
