package main;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import webservice.IDemoWebService;

public class Client {

    public static void main(String[] args) {
        try {
        	Client client = new Client();
            client.run();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
        	QName serviceQName = new QName("http://digpre.tuwien.ac.at/Demo","DemoWebService");
			Service serviceFactory = Service.create(new URL("http://localhost:8080/DemoWebService?wsdl"), serviceQName);
			IDemoWebService service = serviceFactory.getPort(IDemoWebService.class);
			
            System.out.println("Current server time: " + service.getServerTime());
            System.out.println("Identity: " + service.identifyYourself());
            System.out.println("OS: " + service.identifySWEnvironment().getSystemName());
            System.out.println("Vars: " + service.identifySWEnvironment().getEnvironmentVariables());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
