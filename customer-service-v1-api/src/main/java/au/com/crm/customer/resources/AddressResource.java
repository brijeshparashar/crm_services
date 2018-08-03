package au.com.crm.customer.resources;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotEmpty;

import au.com.crm.customer.Validator.ValidateAddressRequest;

@ValidateAddressRequest
public class AddressResource implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer addressId;
	
	@NotEmpty(message="{null.address.unitNumber}")
	@Size(max=5,message= "{length.address.unitNumber}")
	private String unitNumber;
	
	
	@NotEmpty(message="{null.address.streetName}")
	@Size(max=20,message="{length.address.streetName}")
	private String streetName;
	
	
	@NotEmpty(message="{null.address.suburb}")
	@Size(max=20,message= "{length.address.suburb}")
	private String suburb;
	
	@NotEmpty(message="{null.address.postCode}")
	@Size(min=4,max=10,message= "{length.address.postCode}")
	private String postCode;
	
	
	@NotEmpty(message="{null.address.country}")
	@Size(max=20,message= "{length.address.country}")
	private String country;	
	
	@NotEmpty(message="{null.address.addressType}")
	@Size(max=10)
	private String addressType;
	
	
	

	public Integer getAddressId() {
		return addressId;
	}



	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}



	public String getUnitNumber() {
		return unitNumber;
	}



	public void setUnitNumber(String unitNumber) {
		this.unitNumber = unitNumber;
	}



	public String getStreetName() {
		return streetName;
	}



	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}



	public String getSuburb() {
		return suburb;
	}



	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}



	public String getPostCode() {
		return postCode;
	}



	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}



	public String getCountry() {
		return country;
	}



	public void setCountry(String country) {
		this.country = country;
	}



	public String getAddressType() {
		return addressType;
	}



	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}



	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
	@Override
	public boolean equals(Object obj) {
		   return EqualsBuilder.reflectionEquals(this, obj);
	}
	
}
