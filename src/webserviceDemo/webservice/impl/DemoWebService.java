package webservice.impl;

import java.util.Date;
import javax.jws.WebMethod;
import javax.jws.WebService;

import dao.DbSettings;

import enrichment.AbstractResilientWebService;
import enrichment.EnrichmentFailedException;

import webservice.IDemoWebService;

@WebService(name="DemoWebService", serviceName="DemoWebService", targetNamespace="http://digpre.tuwien.ac.at/Demo", endpointInterface="webservice.IDemoWebService")
public class DemoWebService
	extends AbstractResilientWebService
	implements IDemoWebService {
	
	public DemoWebService() {
		super(new DbSettings("localhost", 27017, "digpre"));
	}

	@Override
	@WebMethod
	public Date getServerTime() {
		return new Date();
	}

	@Override
	@WebMethod
	public String identifyYourself() throws EnrichmentFailedException {
		return "I am a demo webservice";
	}

}
