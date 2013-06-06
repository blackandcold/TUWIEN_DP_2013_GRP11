package webservice.impl;

import javax.jws.WebMethod;
import javax.jws.WebService;

import webservice.IEchoWebService;
import dao.DbSettings;
import enrichment.AbstractResilientWebService;
import enrichment.EnrichmentFailedException;

@WebService(name="EchoWebService", serviceName="EchoWebService", targetNamespace="http://digpre.tuwien.ac.at/Demo", endpointInterface="webservice.IEchoWebService")
public class EchoWebService
	extends AbstractResilientWebService
	implements IEchoWebService {
	
	public EchoWebService() {
		super(new DbSettings("localhost", 27017, "digpre"));
	}

	@WebMethod
	public String identifyYourself() throws EnrichmentFailedException {
		return "I am an echo webservice";
	}

	@Override
	@WebMethod
	public String echo(String input) {
		if(input == null){
			return "";
		}
		return input.toUpperCase();
	}

}
