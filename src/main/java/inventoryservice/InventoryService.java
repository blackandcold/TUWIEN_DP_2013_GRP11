package inventoryservice;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import model.Inventory;
import recorder.IRecorder;
import recorder.RecorderFactory;
import recorder.RecorderType;
import util.Util;
import dao.DAOFactory;
import dao.IInventoryDAO;

/**
 * A small console application, allowing the execution of the inventory service
 * @author Stefan Weghofer
 */
public class InventoryService
{

	public static void main(String[] args)
	{
		InventoryService service = new InventoryService();
		service.run();
	}

	private ServiceSettings settings;
	
	public InventoryService(){
		this.loadSettings();
	}
	
	private void run() {
		try {
			System.out.println("Performing system inventory. This might take a few seconds...");
			
			// get the correct recorder instance for the current operating system
			RecorderType type = this.determineRecorderType();
			IRecorder recorder = RecorderFactory.createRecorder(type);
			
			// create the inventory
			Inventory inv = recorder.performInventory(this.settings.getInventoryPaths());
			inv.calculateHashValue(); // calculate once so the .hash-fields are set for the db queries

			// save the result to the database
			IInventoryDAO inventoryDAO = DAOFactory.createInventoryDAO(this.settings.getDbSettings());
			inventoryDAO.add(inv);
			
			System.out.println("Inventory finished. Timestamp: " + Util.formatDate(inv.getTimestamp()) + ". Hash: " + inv.getHashValue());
		} catch(Exception ex){
			System.out.println("Creating inventory failed: " + ex.getMessage());
		}
	}
	
	private RecorderType determineRecorderType() {
		String os = System.getProperty("os.name");
		if(os.toLowerCase().contains("windows")){
			return RecorderType.Windows;
		}
		throw new RuntimeException("No recorder could be found for the current operating system");
	}
	
	private void loadSettings() {
		try {
	    	JAXBContext context = JAXBContext.newInstance(ServiceSettings.class);
		    File settingsFile = new File("settings.xml");
		    if(!settingsFile.exists()) { 
		    	// if no settings exists, create a new file with the standard settings
		    	this.settings = new ServiceSettings();
		    	Marshaller m = context.createMarshaller();
		        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		        m.marshal(this.settings, settingsFile);
		    } else { 
		    	Unmarshaller u = context.createUnmarshaller();
		    	this.settings = (ServiceSettings) u.unmarshal(new File("./settings.xml"));
			}
		} catch (JAXBException ex) {
			System.out.println("Settings could not be loaded: " + ex.getMessage());
			System.out.println("Shutting down inventory services...");
			System.exit(1);
		}
	}

}
