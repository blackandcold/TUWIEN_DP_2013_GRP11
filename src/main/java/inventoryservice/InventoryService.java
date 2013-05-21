package inventoryservice;

import dao.DAOFactory;
import dao.DbSettings;
import dao.IInventoryDAO;
import model.Inventory;
import recorder.IRecorder;
import recorder.RecorderFactory;
import recorder.RecorderType;

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

	private DbSettings settings;
	
	public InventoryService(){
		//TODO: load settings
		this.settings = new DbSettings();
		this.settings.setDbName("digpre");
		this.settings.setServerName("localhost");
		this.settings.setServerPort(27017);
	}
	
	private void run() {
		try {
			// get the correct recorder instance for the current operating system
			RecorderType type = this.determineRecorderType();
			IRecorder recorder = RecorderFactory.createRecorder(type);
			
			// create the inventory
			Inventory inv = recorder.performInventory();
			System.out.println("Java Version: " + inv.getOperatingSystemSnapshot().getJavaVersion());

			// save the result to the database
			IInventoryDAO inventoryDAO = DAOFactory.createInventoryDAO(this.settings);
			inventoryDAO.add(inv);

			System.out.println("Press any key to exit...");
			System.in.read();
			System.out.println("Finished...");
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

}
