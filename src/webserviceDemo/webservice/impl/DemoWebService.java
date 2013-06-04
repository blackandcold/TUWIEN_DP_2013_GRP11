package webservice.impl;

import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebService;

import webservice.IDemoWebService;
import dao.DbSettings;
import enrichment.AbstractResilientWebService;
import enrichment.EnrichmentFailedException;

@WebService(name="DemoWebService", serviceName="DemoWebService", targetNamespace="http://digpre.tuwien.ac.at/Demo", endpointInterface="webservice.IDemoWebService")
public class DemoWebService
	extends AbstractResilientWebService
	implements IDemoWebService {
	
	public DemoWebService() {
		super(new DbSettings("localhost", 27017, "digpre"));
	}

	@WebMethod
	public Date getServerTime() {
		return new Date();
	}
	
	@WebMethod
	public String identifyYourself() throws EnrichmentFailedException {
		return "I am a demo webservice";
	}

}
