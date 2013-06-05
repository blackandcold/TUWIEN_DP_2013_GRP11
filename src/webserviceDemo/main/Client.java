package main;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.omg.CORBA.Environment;

import model.KeyValue;
import webservice.IDemoWebService;
import enrichment.dto.SystemChange;
import enrichment.dto.SystemChangeSynopsis;

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
            for(SystemChangeSynopsis syn : service.serviceChangesSince(this.getDate(1, 1, 2013))){
                System.out.println("++++++++++++++++++++++ Changes on: " + syn.getTimestamp() + " ++++++++++++++++++++++");
                for(SystemChange change : syn.getSystemChanges()){
                	for(KeyValue<String, String> context : change.getContexts()){
                		System.out.print(String.format("%s[%s] ", context.getKey(), context.getValue()==null?"":context.getValue()));
                	}
                	System.out.println();
                	System.out.print(change.getDifferenceType() + " " + change.getIdentifier() +": ");
                	switch(change.getDifferenceType()){
                	case Inserted:
                		System.out.print(change.getNewValue());
						break;
					case Deleted:
                		System.out.print(change.getOldValue());
						break;
					case Updated:
                		System.out.print(change.getOldValue() + " -> " + change.getNewValue());
						break;
                	}
                	System.out.println();
                	System.out.println();
                }
            }
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
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
