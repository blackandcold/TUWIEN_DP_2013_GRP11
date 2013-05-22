package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import com.google.code.morphia.annotations.Embedded;

/**
 * This class summarizes important information concerning the operating system
 * @author Stefan Weghofer
 */
@Embedded
public class OperatingSystemSnapshot
	extends AbstractSnapshot {

	private String systemName;
	private String architecture;
	private String systemVersion;
	private String javaVersion;
	private String javaClassPath;
	private Map<String, String> environmentVariables;

	@Embedded
	private List<NetworkInterfaceSnapshot> networkSnapshots;
		
	/**
	 * @return the systemName
	 */
	public String getSystemName() {
		return systemName;
	}
	
	/**
	 * @param systemName the systemName to set
	 */
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	
	/**
	 * @return the architecture
	 */
	public String getArchitecture() {
		return architecture;
	}
	
	/**
	 * @param architecture the architecture to set
	 */
	public void setArchitecture(String architecture) {
		this.architecture = architecture;
	}
	
	/**
	 * @return the systemVersion
	 */
	public String getSystemVersion() {
		return systemVersion;
	}
	
	/**
	 * @param systemVersion the systemVersion to set
	 */
	public void setSystemVersion(String systemVersion) {
		this.systemVersion = systemVersion;
	}
	
	/**
	 * @return the environmentVariables
	 */
	public Map<String, String> getEnvironmentVariables() {
		return environmentVariables;
	}
	
	/**
	 * @param environmentVariables the environmentVariables to set
	 */
	public void setEnvironmentVariables(Map<String, String> environmentVariables) {
		this.environmentVariables = environmentVariables;
	}
	
	/**
	 * @param an environment variable as key and value pair
	 */
	public void addEnvironmentVariable(String key, String value) {
		if(this.environmentVariables == null) {
			this.environmentVariables = new HashMap<String, String>();
		}
		this.environmentVariables.put(key, value);
	}

	/**
	 * @return the javaVersion
	 */
	public String getJavaVersion() {
		return javaVersion;
	}

	/**
	 * @param javaVersion the javaVersion to set
	 */
	public void setJavaVersion(String javaVersion) {
		this.javaVersion = javaVersion;
	}

	/**
	 * @return the javaClassPath
	 */
	public String getJavaClassPath() {
		return javaClassPath;
	}

	/**
	 * @param javaClassPath the javaClassPath to set
	 */
	public void setJavaClassPath(String javaClassPath) {
		this.javaClassPath = javaClassPath;
	}

	/**
	 * @return the networkInterfaceSnapshot
	 */
	public List<NetworkInterfaceSnapshot> getNetworkInterfaceSnapshots() {
		return networkSnapshots;
	}

	/**
	 * @param fileSnapshots the fileSnapshots to set
	 */
	public void setNetworkInterfaceSnapshots(List<NetworkInterfaceSnapshot> networkSnapshots) {
		this.networkSnapshots = networkSnapshots;
	}
	
	/**
	 * Adds a network interface snapshot to this snapshot
	 * @param fileSnapshot the file snapshot to add
	 */
	public void addNetworkInterfaceSnapshot(NetworkInterfaceSnapshot networkSnapshot) {
		if(this.networkSnapshots == null) {
			this.networkSnapshots = new ArrayList<NetworkInterfaceSnapshot>();
		}
		if(!this.networkSnapshots.contains(networkSnapshot)){
			this.networkSnapshots.add(networkSnapshot);
		}
	}

	
	/* *******************************************************
	 * Interface Implementation
	 * *******************************************************/

	@Override
	public String getHashValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SnapshotDifference> getDifference(ISnapshot sn) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
