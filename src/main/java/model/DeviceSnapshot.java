package model;

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
	
	// do we need them?
	private String freeField1;
	private String freeField2;
	private String freeField3;
	
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
	 * @return the freeField1
	 */
	public String getFreeField1() {
		return freeField1;
	}

	/**
	 * @param freeField1 the freeField1 to set
	 */
	public void setFreeField1(String freeField1) {
		this.freeField1 = freeField1;
	}

	/**
	 * @return the freeField2
	 */
	public String getFreeField2() {
		return freeField2;
	}

	/**
	 * @param freeField2 the freeField2 to set
	 */
	public void setFreeField2(String freeField2) {
		this.freeField2 = freeField2;
	}

	/**
	 * @return the freeField3
	 */
	public String getFreeField3() {
		return freeField3;
	}

	/**
	 * @param freeField3 the freeField3 to set
	 */
	public void setFreeField3(String freeField3) {
		this.freeField3 = freeField3;
	}
	
	
}
