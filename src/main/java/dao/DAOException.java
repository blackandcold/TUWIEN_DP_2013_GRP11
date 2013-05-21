package dao;

/**
 * This exception is thrown by the DAOs to signal errors
 * @author Stefan Weghofer
 */
public class DAOException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DAOException(String msg) {
		super(msg);
	}

	public DAOException(String msg, Throwable inner) {
		super(msg, inner);
	}
	
}
