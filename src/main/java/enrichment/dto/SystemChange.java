package enrichment.dto;

import java.util.ArrayList;
import java.util.List;

import model.DifferenceType;
import model.Inventory;
import model.KeyValue;
import model.SnapshotDifference;

public class SystemChange {

	private List<KeyValue<String, String>> contexts;
	private String identifier;
	private DifferenceType type;
	private String oldValue;
	private String newValue;
	
	public SystemChange() {	}

	/**
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * @param identifier the identifier to set
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	/**
	 * @return the type
	 */
	public DifferenceType getDifferenceType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setDifferenceType(DifferenceType type) {
		this.type = type;
	}

	/**
	 * @return the oldValue
	 */
	public String getOldValue() {
		return oldValue;
	}

	/**
	 * @param oldValue the oldValue to set
	 */
	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	/**
	 * @return the newValue
	 */
	public String getNewValue() {
		return newValue;
	}

	/**
	 * @param newValue the newValue to set
	 */
	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	/**
	 * @return the contexts
	 */
	public List<KeyValue<String, String>> getContexts() {
		return contexts;
	}
	
	/**
	 * @return the contexts
	 */
	public void setContexts(List<KeyValue<String, String>> contexts) {
		this.contexts = contexts;
	}
	
	/**
	 * @return the contexts
	 */
	public void addContext(String context, String identifier) {
		if(this.contexts == null){
			this.contexts = new ArrayList<KeyValue<String, String>>();
		}
		this.contexts.add(new KeyValue<String, String>(context, identifier));
	}
	
	public static SystemChange create(SnapshotDifference diff){
		SystemChange result = new SystemChange();
		for(KeyValue<Class<?>, String> entry : diff.getContexts()){
			if(!entry.getKey().equals(Inventory.class)){ // skip Inventory... all belong to this
				result.addContext(entry.getKey().getSimpleName().replaceAll("Snapshot", ""), entry.getValue()); // no need for "Snapshot" everywhere
			}
		}
		result.identifier = diff.getIdentifier();
		result.type = diff.getDifferenceType();
		result.oldValue = diff.getOldValue()==null?null:diff.getOldValue().toString();
		result.newValue = diff.getNewValue()==null?null:diff.getNewValue().toString();
		return result;
	}
}
