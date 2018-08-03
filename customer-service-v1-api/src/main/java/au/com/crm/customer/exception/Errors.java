package au.com.crm.customer.exception;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

public class Errors implements Serializable {

	/**
	 * Serial version ID
	 */
	private static final long serialVersionUID = 8478162612558813760L;
	// Error code
	String errorCode;
	// Error message
	String errorMessage;

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode
	 *            the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage
	 *            the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return StringUtils.join(new Object[] { errorCode, errorMessage }, ",");
	}
}