package au.com.crm.customer.exception;

import java.io.Serializable;
import java.util.List;

public class ErrorBean implements Serializable {

	/**
	 * Serial version ID
	 */
	private static final long serialVersionUID = 7531365981199113530L;

	// Error category
	private String errorCategory;
	// List of error to be logged
	private List<Errors> errors;

	/**
	 * @return the errorCategory
	 */
	public String getErrorCategory() {
		return errorCategory;
	}

	/**
	 * @param errorCategory
	 *            the errorCategory to set
	 */
	public void setErrorCategory(String errorCategory) {
		this.errorCategory = errorCategory;
	}

	/**
	 * @return the errors
	 */
	public List<Errors> getErrors() {
		return errors;
	}

	/**
	 * @param errors
	 *            the list of errors to set
	 */
	public void setErrors(List<Errors> errors) {
		this.errors = errors;
	}

}
