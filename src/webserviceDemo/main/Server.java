package main;

import javax.xml.ws.Endpoint;

import webservice.impl.DemoWebService;

public class Server {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Endpoint.publish("http://localhost:8080/DemoWebService", new DemoWebService());
	}

}
