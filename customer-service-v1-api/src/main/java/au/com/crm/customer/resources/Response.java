package au.com.crm.customer.resources;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


public class Response implements Serializable {

	public Response() {
	
	}
	 
	public Response(String status,String message) {
		this.status = status;
		this.message= message;
	}
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 145645L;
	
	
	
	
	protected String status; 
	
	protected String message; 
	

	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}
