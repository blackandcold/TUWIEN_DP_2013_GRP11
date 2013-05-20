package model;

/**
 * Represents a difference in two snapshot items
 * @author Stefan Weghofer
 */
public class SnapshotDifference {

	private String identifier;
	private String oldValue;
	private String newValue;
	
	public SnapshotDifference(String identifier, String oldValue, String newValue) {
		this.identifier = identifier;
		this.oldValue = oldValue;
		this.newValue = newValue;
	}
	
	/**
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}
	
	/**
	 * @return the oldValue
	 */
	public String getOldValue() {
		return oldValue;
	}
	
	/**
	 * @return the newValue
	 */
	public String getNewValue() {
		return newValue;
	}
	
}
