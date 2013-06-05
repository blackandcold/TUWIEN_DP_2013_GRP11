package model;

import java.util.ArrayList;
import java.util.List;

import com.google.code.morphia.annotations.Embedded;

/**
 * This class summarizes information on a network interface
 * @author Stefan Weghofer
 */
@Embedded
public class NetworkInterfaceSnapshot extends AbstractSnapshot {

	@Identifier
	private String name;
	
	private String displayName;
	private List<String> addresses;
	private String hardwareAddress;
	
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
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}
	
	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	/**
	 * @return the addresses
	 */
	public List<String> getAddresses() {
		return addresses;
	}
	
	/**
	 * @param addresses the addresses to set
	 */
	public void setAddresses(List<String> addresses) {
		this.addresses = addresses;
	}
	
	/**
	 * @param addresses the addresses to set
	 */
	public void addAddress(String address) {
		if(this.addresses == null) {
			this.addresses = new ArrayList<String>();
		}
		if(!this.addresses.contains(address)) {
			this.addresses.add(address);
		}
	}

	/**
	 * @return the hardwareAddress
	 */
	public String getHardwareAddress() {
		return hardwareAddress;
	}

	/**
	 * @param hardwareAddress the hardwareAddress to set
	 */
	public void setHardwareAddress(String hardwareAddress) {
		this.hardwareAddress = hardwareAddress;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof NetworkInterfaceSnapshot)){
			return false;
		}
		NetworkInterfaceSnapshot o = (NetworkInterfaceSnapshot) obj;
		return o.name.equals(this.name);
	}

	@Override
	public String toString() {
		return this.name;
	}
	
}
