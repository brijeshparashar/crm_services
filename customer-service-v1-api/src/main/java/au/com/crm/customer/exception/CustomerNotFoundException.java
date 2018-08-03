package au.com.crm.customer.exception;


public class CustomerNotFoundException extends Exception {
	 
    /**
	 * 
	 */
	private static final long serialVersionUID = 5930991654790958323L;

	public CustomerNotFoundException(String message) {
        super(message);
    }
}
