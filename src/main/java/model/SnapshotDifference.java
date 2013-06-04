package model;

/**
 * Represents a difference in two snapshot items
 * @author Stefan Weghofer
 */
public class SnapshotDifference {

	private Class<?> scope;
	private String identifier;
	private DifferenceType type;
	private Object oldValue;
	private Object newValue;
	
	public SnapshotDifference(Class<?> scope, String identifier, DifferenceType type, Object oldValue, Object newValue) {
		this.scope = scope;
		this.identifier = identifier;
		this.type = type;
		this.oldValue = oldValue;
		this.newValue = newValue;
	}

	/**
	 * @return the identifier
	 */
	public Class<?> getScope() {
		return scope;
	}
	
	/**
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}
	
	/**
	 * @return the type
	 */
	public DifferenceType getDifferenceType() {
		return type;
	}
	
	/**
	 * @return the oldValue
	 */
	public Object getOldValue() {
		return oldValue;
	}
	
	/**
	 * @return the newValue
	 */
	public Object getNewValue() {
		return newValue;
	}
	
	/**
	 * Specifies the type of difference
	 * @author Stefan Weghofer
	 */
	public enum DifferenceType {
		Inserted,
		Updated,
		Deleted
	}
	
	/**
	 * Factory method for creating a snapshot difference for an inserted field value
	 * @param identifier for the difference
	 * @param oldValue of the field
	 * @param newValue of the field
	 * @return a snapshot difference encapsulating the object change
	 */
	public static SnapshotDifference createInsert(Class<?> scope, String identifier, Object newValue){
		return new SnapshotDifference(scope, identifier, DifferenceType.Inserted, null, newValue);
	}

	/**
	 * Factory method for creating a snapshot difference for an updated field value
	 * @param identifier for the difference
	 * @param oldValue of the field
	 * @param newValue of the field
	 * @return a snapshot difference encapsulating the object change
	 */
	public static SnapshotDifference createUpdate(Class<?> scope, String identifier, Object oldValue, Object newValue){
		return new SnapshotDifference(scope, identifier, DifferenceType.Updated, oldValue, newValue);
	}

	/**
	 * Factory method for creating a snapshot difference for a deleted field value
	 * @param identifier for the difference
	 * @param oldValue of the field
	 * @param newValue of the field
	 * @return a snapshot difference encapsulating the object change
	 */
	public static SnapshotDifference createDelete(Class<?> scope, String identifier, Object oldValue){
		return new SnapshotDifference(scope, identifier, DifferenceType.Deleted, oldValue, null);
	}
	
}
