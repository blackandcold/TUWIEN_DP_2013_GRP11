package inventoryservice;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import model.Inventory;
import model.OperatingSystemSnapshot;
import model.SnapshotDifference;
import recorder.IRecorder;
import recorder.RecorderFactory;
import recorder.RecorderType;
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
			// get the correct recorder instance for the current operating system
			RecorderType type = this.determineRecorderType();
			IRecorder recorder = RecorderFactory.createRecorder(type);
			
			// create the inventory
			Inventory inv = recorder.performInventory(this.settings.getInventoryPaths());
			inv.calculateHashValue(); // calculate once so the .hash-fields are set for the db queries

			// save the result to the database
			IInventoryDAO inventoryDAO = DAOFactory.createInventoryDAO(this.settings.getDbSettings());
			inventoryDAO.add(inv);
			
			System.out.println("Press any key to exit...");
			System.in.read();
		} catch(Exception ex){
			ex.printStackTrace();
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
		} catch (JAXBException e) {
			e.printStackTrace();
			System.out.println("Settings could not be loaded");
			System.exit(1);
		}
	}

}
