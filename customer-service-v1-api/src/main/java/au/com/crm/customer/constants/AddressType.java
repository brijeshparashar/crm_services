package au.com.crm.customer.constants;

import org.apache.commons.lang3.StringUtils;

public enum AddressType {
		
	OFFICE("OFFICE"),
	HOME("HOME");
	
	private String value;
	
	/**
	 * Constructor
	 */
	private AddressType(String value) {
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	
	public static AddressType fromCode(String value) {
		for (AddressType type: values()) {
			if (StringUtils.equalsIgnoreCase(value, type.getValue())) {
				return type;
			}
		}
		return null;
	}
	
}
