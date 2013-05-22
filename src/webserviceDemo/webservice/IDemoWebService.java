package webservice;

import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebService;

import enrichment.IResilientWebService;

@WebService
public interface IDemoWebService extends IResilientWebService {

	@WebMethod
	Date getServerTime();
	
}
