package model;

import java.util.HashMap;
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
	private HashMap<String, String> environmentVariables;
		
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
	public HashMap<String, String> getEnvironmentVariables() {
		return environmentVariables;
	}
	
	/**
	 * @param environmentVariables the environmentVariables to set
	 */
	public void setEnvironmentVariables(HashMap<String, String> environmentVariables) {
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
