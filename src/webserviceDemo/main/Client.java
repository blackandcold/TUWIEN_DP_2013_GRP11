package main;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;

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
            System.out.println("Vars: " + service.swEnvironmentChangesSince(this.getDate(1, 1, 2013)));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

	private Date getDate(int day, int month, int year){
		Calendar date = Calendar.getInstance();
		date.set(Calendar.YEAR, year);
		date.set(Calendar.MONTH, month);
		date.set(Calendar.DAY_OF_MONTH, day);
		return date.getTime();
	}

}
