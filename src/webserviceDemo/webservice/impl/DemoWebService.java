package webservice.impl;

import java.util.Date;
import javax.jws.WebMethod;
import javax.jws.WebService;

import enrichment.AbstractResilientWebService;

import webservice.IDemoWebService;

@WebService(name="DemoWebService", serviceName="DemoWebService", targetNamespace="http://digpre.tuwien.ac.at/Demo", endpointInterface="webservice.IDemoWebService")
public class DemoWebService
	extends AbstractResilientWebService
	implements IDemoWebService {

	@Override
	@WebMethod
	public Date getServerTime() {
		return new Date();
	}

}
