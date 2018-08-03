package au.com.crm.customer.resources;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


public class CustomerResource implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 132312L;

	private Integer customerId;

	@NotEmpty(message="{null.customer.firstName}")
	@Size(max=20)
	private String firstName;
	
	@NotEmpty(message="{null.customer.lastName}")
	@Size(max=20)
	private String lastName;
	
	@NotEmpty(message="{null.customer.emailId}")
	@Email(message="{invalid.customer.emailId}")
	@Size(min=5,max=50)
	private String emailId;
	
	@Email(message="{invalid.customer.alternateEmailId}")
	@Size(min=5,max=50)
	private String alternateEmailId;
	@Valid
	private List<AddressResource> addresses = new ArrayList<>();
	
	/**
	 * Default Constructor
	 */
	public CustomerResource() {
		// empty constructor
	}
	 

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}




	public String getAlternateEmailId() {
		return alternateEmailId;
	}




	public void setAlternateEmailId(String alternateEmailId) {
		this.alternateEmailId = alternateEmailId;
	}




	public List<AddressResource> getAddresses() {
		return addresses;
	}


	public void setAddresses(List<AddressResource> addresses) {
		this.addresses = addresses;
	}




	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
	
	@Override
	public boolean equals(Object obj) {
		   return EqualsBuilder.reflectionEquals(this, obj);
	}
}
