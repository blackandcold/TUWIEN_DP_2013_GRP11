package enrichment;

import javax.xml.ws.WebFault;

/**
 * This exception is thrown when a resilient webservice method was invoked
 * but failed to return meaningful results about the environment
 * @author Stefan Weghofer
 */
@WebFault(name="EnrichmentFailedException")
public class EnrichmentFailedException extends Exception {

	private static final long serialVersionUID = 2531599348549218552L;

	public EnrichmentFailedException() {
		super();
	}
	
	public EnrichmentFailedException(String msg) {
		super(msg);
	}
	
	public EnrichmentFailedException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public EnrichmentFailedException(Throwable cause) {
		super(cause);
	}

}
