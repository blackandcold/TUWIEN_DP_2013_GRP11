package inventoryservice;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import dao.DbSettings;

/**
 * Defines the settings for the inventory service
 * @author Stefan Weghofer
 */
@XmlRootElement
public class ServiceSettings {

	private DbSettings settings;
	private List<String> inventoryPaths;
	
	public ServiceSettings() {
		this.settings = new DbSettings();
		this.settings.setDbName("digpre");
		this.settings.setServerName("localhost");
		this.settings.setServerPort(27017);
		this.inventoryPaths = new ArrayList<String>();
		this.inventoryPaths.add("C:\\Users\\Stefan\\Desktop\\Temp\\test");
	}

	/**
	 * @return the settings
	 */
	public DbSettings getDbSettings() {
		return settings;
	}

	/**
	 * @param settings the settings to set
	 */
	public void setDbSettings(DbSettings settings) {
		this.settings = settings;
	}

	/**
	 * @return the inventoryPaths
	 */
	public List<String> getInventoryPaths() {
		return inventoryPaths;
	}

	/**
	 * @param inventoryPaths the inventoryPaths to set
	 */
	public void setInventoryPaths(List<String> inventoryPaths) {
		this.inventoryPaths = inventoryPaths;
	}
}
