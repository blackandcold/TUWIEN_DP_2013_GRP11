package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import model.DeviceSnapshot;
import model.HardwareSnapshot;
import model.KeyValue;
import model.NetworkInterfaceSnapshot;
import model.OperatingSystemSnapshot;
import util.Util;
import webservice.IEchoWebService;
import enrichment.EnrichmentFailedException;
import enrichment.dto.SystemChange;
import enrichment.dto.SystemChangeSynopsis;

/**
 * This client can be used to demonstrate the enrichment on the DemoWebService
 * @author Stefan Weghofer
 */
public class Client {

	public static void main(String[] args) {
		Client client = new Client();
		client.run();
	}

	private IEchoWebService service;

	public void run() {
		try {
			this.connect();

			System.out.print("Wellcome to the Echo Web Service Client. ");
			this.showCommands();
			System.out.print("> ");
			String command = this.getCommandLineInput().toLowerCase();
			while(!command.trim().equals("e")){
				try {
					switch(command){
					case "ca":
						this.performEcho();
						break;
					case "id": 
						this.performIdentification();
						break;
					case "sw": 
						this.showSWEnvironment();
						break;
					case "hw": 
						this.showHWEnvironment();
						break;
					case "ch": 
						this.showChanges();
						break;
					case "h": 
						this.showCommands();
						break;
					case "": 
						System.out.println(service.identifyYourself());
						break;
						default:
							System.out.println("Unkown command: " + command);
							this.showCommands();
					}
				} catch(EnrichmentFailedException ex) {
					System.out.println(ex.getMessage());
				}
				System.out.print("> ");
				command = this.getCommandLineInput().toLowerCase();
			}
			System.out.print("See you soon!");
		} catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Unexpected error: " + ex.getMessage());
			System.out.println("Terminating client...");
			System.exit(1);
		}
	}
	
	private void connect() throws MalformedURLException{
		QName serviceQName = new QName("http://digpre.tuwien.ac.at/Demo","EchoWebService");
		Service serviceFactory = Service.create(new URL("http://localhost:8080/EchoWebService?wsdl"), serviceQName);
		service = serviceFactory.getPort(IEchoWebService.class);
	}

	private void showCommands(){
		System.out.println("Following commands ara available: ");
		System.out.println("ca	Sends the [input] to the web service. The service will return it with uppercase letters");
		System.out.println("id	Identifies the web service");
		System.out.println("sw	Shows the software environment of the service");
		System.out.println("hw	Shows the hardware environment of the service");
		System.out.println("ch	Shows the changes to the service host since [date] ");
		System.out.println("h	Prints the command overview ");
		System.out.println("e	Exits from the client ");
	}
	
	private void performEcho(){
		System.out.print("Specify input: ");
		String input = this.getCommandLineInput().toLowerCase();
		System.out.println(service.echo(input));
	}
	
	private void performIdentification() throws EnrichmentFailedException{
		String id = service.identifyYourself();
		System.out.println(id);
	}
	
	private void showSWEnvironment() throws EnrichmentFailedException {
		OperatingSystemSnapshot os = service.identifySWEnvironment();
		System.out.println("System: " + os.getSystemName() + " " + os.getSystemVersion());
		System.out.println("Architecture: " + os.getArchitecture());
		System.out.println("Java Version: " + os.getJavaVersion());
		System.out.println("\nEnvironment Variables: ");
		for(String key : os.getEnvironmentVariables().keySet()){
			System.out.println("\t" + key + ": " + os.getEnvironmentVariables().get(key));
		}
		System.out.println("\nNetwork configuration: ");
		for(NetworkInterfaceSnapshot n : os.getNetworkInterfaceSnapshots()){
			if(n.getAddresses() != null) { // only output the "sensible" ones
				System.out.println("\tName: " + n.getDisplayName());
				System.out.println("\tDisplay name: " + n.getDisplayName());
				System.out.println("\tMAC: " + n.getHardwareAddress());
				System.out.println("\tAddresses: ");
				for(String address : n.getAddresses()){
					System.out.println("\t\t" + address);
				}
				System.out.println("");
			}
		}
	}

	private void showHWEnvironment() throws EnrichmentFailedException {
		HardwareSnapshot hw = service.identifyHWEnvironment();
		if(hw.getDeviceSnapshots() != null){
			for(DeviceSnapshot device : hw.getDeviceSnapshots()){
				System.out.println("Name: " + device.getName());
				if(device.getProperties() != null){
					for(String key : device.getProperties().keySet()){
						System.out.println("\t" + key + ": " + device.getProperties().get(key));
					}
				}
				System.out.println("");
			}
		} else {
			System.out.println("No devices found... ");
		}
	}

	private void showChanges() throws EnrichmentFailedException, ParseException{
		System.out.print("Specify date [dd.MM.yyyy HH:mm:ss]: ");
		String input = this.getCommandLineInput();
		Date date = Util.parseDate(input, "dd.MM.yyyy HH:mm:ss");
		List<SystemChangeSynopsis> changes = service.serviceChangesSince(date);
		if(changes != null && changes.size() > 0) {
			for(SystemChangeSynopsis syn : changes){
				System.out.println("++++++++++++++++++++++ Changes on: " + Util.formatDate(syn.getTimestamp()) + " ++++++++++++++++++++++");
				for(SystemChange change : syn.getSystemChanges()){
					if(change.getContexts() != null) {
						for(KeyValue<String, String> context : change.getContexts()){
							System.out.print(String.format("%s%s ", context.getKey(), context.getValue()==null?"":"["+context.getValue()+"]"));
						}
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
					System.out.println("\n");
				}
			}
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		} else {
			System.out.println("No changes detected since " + input);
		}
	}


	private String getCommandLineInput(){
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			return br.readLine();
		} catch (IOException e) {
			throw new RuntimeException("Illegal command line input");
		}
	}

}
