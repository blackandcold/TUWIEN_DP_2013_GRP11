package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * This class is the base class for all snapshot items. It provides easy means
 * for creating the hash value as well as the snapshot differences.
 * @author Stefan Weghofer
 */
public abstract class AbstractSnapshot
	implements ISnapshot {
	
	// For database queries only
	private String hash;

	/**
	 * @return the hash
	 */
	public String getHash() {
		return hash;
	}

	/**
	 * @param hash the hash to set
	 */
	public void setHash(String hash) {
		this.hash = hash;
	}
	
	
//	
//	/* 
//	 * All snapshots save their data in the "value" hash map. Easier access is provided by the
//	 * sub classes, which hide this implementation detail.
//	 */
//	private HashMap<String, String> values;
//	
//	public AbstractSnapshot() {
//		values = new HashMap<String, String>();
//	}
//
//	/* *******************************************************
//	 * Protected Methods
//	 * *******************************************************/
//	
//	/**
//	 * Gets the value for the specified key 
//	 * @param key the key to retrieve the value
//	 * @return the value stored for the specified key
//	 */
//	protected String get(String key){
//		if(this.values.containsKey(key)){
//			return this.values.get(key);
//		}
//		return null; // no exceptions needed
//	}
//	
//	/**
//	 * Adds a new value. Overwrites any existing value for the given key
//	 * @param key the key to store the value
//	 * @param value the value to store
//	 */
//	protected void set(String key, String value){
//		this.values.put(key, value);
//	}
//	
//	
//	/* *******************************************************
//	 * Interface Implementations
//	 * *******************************************************/
//	
//	@Override
//	public HashMap<String, String> getValues() {
//		return this.values;
//	}
//	
//	@Override
//	public String getHashValue() {
//		// create ordered key list to ensure correct hashing behavior
//		List<String> keys = new ArrayList<>(values.keySet());
//		Collections.sort(keys);
//		// create content sum-up & hash it
//		String content = "";
//		for(String key : keys) {
//			content += values.get(key);
//		}
//		return DigestUtils.md5Hex(content);
//	}
//
//	@Override
//	public List<SnapshotDifference> getDifference(ISnapshot sn) {
//		List<SnapshotDifference> differences = new ArrayList<SnapshotDifference>();
//		String oldValue, newValue;
//		HashMap<String, String> snValues = sn.getValues();
//		for(String key : values.keySet()) {
//			if(!snValues.containsKey(key)) { // not in sn, but in current
//				differences.add(new SnapshotDifference(key, null, values.get(key)));
//			} else { 
//				oldValue = snValues.get(key);
//				newValue = values.get(key);
//				if(!oldValue.equals(newValue)) { // in both, but different values
//					differences.add(new SnapshotDifference(key, oldValue, newValue));
//				}
//			}
//		}
//		for(String key : snValues.keySet()) { // other way round to get ALL keys (= set union)
//			if(!values.containsKey(key)) { // if not in current, than add with current=null
//				differences.add(new SnapshotDifference(key, snValues.get(key), null));
//			}
//		}
//		return differences;
//	}
}
