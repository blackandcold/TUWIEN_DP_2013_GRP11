package main;

import javax.xml.ws.Endpoint;

import webservice.impl.EchoWebService;

/**
 * A small server hosting the echo web service
 * @author Stefan Weghofer
 *
 */
public class Server {

	public static void main(String[] args) {
		try {
			String url = "http://localhost:8080/EchoWebService";
			Endpoint.publish(url, new EchoWebService());
			System.out.println("Echo Web Service listening on " + url);
		} catch(Exception ex){
			System.out.println("Unexpected error: " + ex.getMessage());
		}
	}

}
