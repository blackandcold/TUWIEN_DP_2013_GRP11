package webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

import enrichment.IResilientWebService;

/**
 * A small web service, used for demonstrating the enrichment framework
 * @author Stefan Weghofer
 */
@WebService
public interface IEchoWebService extends IResilientWebService {

	/**
	 * Returns the input in upper case
	 * @param input to be processed
	 * @return the input in upper case
	 */
	@WebMethod
	String echo(String input);
	
}
