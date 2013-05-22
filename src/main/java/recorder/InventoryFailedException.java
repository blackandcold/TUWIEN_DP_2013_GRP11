package recorder;

/**
 * This exception is thrown when any part of the inventory service failes
 * @author Stefan Weghofer
 *
 */
public class InventoryFailedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InventoryFailedException(String msg) {
		super(msg);
	}

	public InventoryFailedException(String msg, Throwable inner) {
		super(msg, inner);
	}
	
}
