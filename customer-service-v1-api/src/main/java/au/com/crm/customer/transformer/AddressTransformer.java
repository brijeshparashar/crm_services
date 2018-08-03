package au.com.crm.customer.transformer;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import au.com.crm.customer.constants.AddressType;
import au.com.crm.customer.entity.AddressEntity;
import au.com.crm.customer.resources.AddressResource;

@Component
public class AddressTransformer implements Function<AddressResource, AddressEntity> {

	@Override
	public AddressEntity apply(AddressResource request) {
		
		AddressEntity addressEntity = null;
		
		if ( request != null ) {
			addressEntity = new AddressEntity();
			addressEntity.setAddressId(request.getAddressId());
			addressEntity.setUnitNumber(request.getUnitNumber());
			addressEntity.setStreetName(request.getStreetName());
			addressEntity.setSuburb(request.getSuburb());
			addressEntity.setPostCode(request.getPostCode());
			addressEntity.setCountry(request.getCountry());
			addressEntity.setAddressType(AddressType.fromCode(request.getAddressType()).name());
		}
		
		return addressEntity;
	}

}
