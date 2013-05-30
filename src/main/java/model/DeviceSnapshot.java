package model;

import java.util.HashMap;

import com.google.code.morphia.annotations.Embedded;

/**
 * This class summarizes information about a single piece of hardware
 * @author Stefan Weghofer
 */
@Embedded
public class DeviceSnapshot {
	
	private String name;
	private String description;
	private String manufacturer;
	private String driver;
	private HashMap<String, String> properties;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the manufacturer
	 */
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 * @param manufacturer the manufacturer to set
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * @return the driver
	 */
	public String getDriver() {
		return driver;
	}

	/**
	 * @param driver the driver to set
	 */
	public void setDriver(String driver) {
		this.driver = driver;
	}


	/**
	 * @return the properties
	 */
	public HashMap<String, String> getProperties() {
		return properties;
	}

	/**
	 * @param properties the properties to set
	 */
	public void setProperties(HashMap<String, String> properties) {
		this.properties = properties;
	}

	/**
	 * @param properties the properties to set
	 */
	public void addProperty(String name, String value) {
		if(this.properties == null) {
			this.properties = new HashMap<String, String>();
		}
		this.properties.put(name, value);
	}
	
	
}
