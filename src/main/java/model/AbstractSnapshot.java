package model;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * This class is the base class for all snapshot items. It provides easy means
 * for creating the hash value as well as the snapshot differences.
 * @author Stefan Weghofer
 */
public abstract class AbstractSnapshot
implements ISnapshot {

	// This field is saved only for performing database queries on it
	@Exclude
	private String hash;

	/* *******************************************************
	 * ISnapshot Method Implementation using Reflection
	 * *******************************************************/
	
	public String getHashValue() {
		if(hash == null || hash.equals("")){
			this.calculateHashValue();
		}
		return hash;
	}

	public String calculateHashValue() {
		String content = "";
		Class<?> clazz = this.getClass();
		for(Field f : clazz.getDeclaredFields()){
			try {
				if(f.isAnnotationPresent(Exclude.class)){
					continue;
				}
				f.setAccessible(true);
				Object value = f.get(this); 
				if(value != null) {
					if(List.class.isAssignableFrom(f.getType())) { // the field is a List,... progress with its elements
						ParameterizedType listType = (ParameterizedType) f.getGenericType();
						Class<?> listClass = (Class<?>) listType.getActualTypeArguments()[0];
						boolean compareSnapshots = ISnapshot.class.isAssignableFrom(listClass);
						List<?> list = (List<?>) value;
						for(Object element : list) {
							if(compareSnapshots) {
								ISnapshot sn = (ISnapshot) element;
								content += sn.calculateHashValue();
							} else {
								content += element.toString();
							}
						}
					} else if(Map.class.isAssignableFrom(f.getType())){ // the field is a map,... progress with its entries
						Map<?,?> map = (Map<?,?>) value;
						for(Object key : map.keySet()){
							content += String.format("[%s=%s]", key, map.get(key));
						}
					} else if(ISnapshot.class.isAssignableFrom(f.getType())) {
						ISnapshot sn = (ISnapshot) value;
						content += sn.calculateHashValue();
					} else {
						content += value.toString();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				f.setAccessible(false);
			}
		}
		this.hash = DigestUtils.md5Hex(content);
		return this.hash;
	}

	public List<SnapshotDifference> getDifference(ISnapshot sn) {
		if(sn.getClass() != this.getClass()){
			throw new RuntimeException("Can not compare snapshots of different types");
		}
		if(sn.getHashValue().equals(this.getHashValue())){
			return new ArrayList<SnapshotDifference>();
		}
		ArrayList<SnapshotDifference> differences = new ArrayList<SnapshotDifference>();
		Class<?> clazz = sn.getClass();
		String identifier = null;
		for(Field f : clazz.getDeclaredFields()){
			try {
				if(f.isAnnotationPresent(Exclude.class)){
					continue;
				}
				f.setAccessible(true);

				Object newValue = f.get(sn); // the sn instance represents the newer object
				Object oldValue = f.get(this); 

				if(oldValue == null && newValue == null) {
					//no changes, both are equivalent
				} else {
					if(List.class.isAssignableFrom(f.getType())) { // the field is a List,... progress with its elements
						List<?> oldList = (List<?>) oldValue;
						List<?> newList = (List<?>) newValue;
						if(oldList == null && newList != null) { // all elements were added
							for(Object element : newList){
								differences.add(SnapshotDifference.createInsert(f.getName(), element));
							}
						} else if(oldList != null && newList == null) { // all map entries were deleted
							for(Object element : oldList){
								differences.add(SnapshotDifference.createDelete(f.getName(), element));
							}
						} else { // compare entries manually
							ParameterizedType listType = (ParameterizedType) f.getGenericType();
							Class<?> listClass = (Class<?>) listType.getActualTypeArguments()[0];
							boolean compareSnapshots = ISnapshot.class.isAssignableFrom(listClass);
							for(Object element : oldList){
								if(!newList.contains(element)){
									differences.add(SnapshotDifference.createDelete(f.getName(), element));
								} else {
									if(compareSnapshots) {
										int index = newList.indexOf(element);
										ISnapshot newSN = (ISnapshot) newList.get(index);
										ISnapshot oldSN = (ISnapshot) element;
										differences.addAll(oldSN.getDifference(newSN));
									} // no else, only Snapshots can be compared by value
								}
							}
							for(Object element : newList){
								if(!oldList.contains(element)){
									differences.add(SnapshotDifference.createInsert(f.getName(), element));
								} 
							}
						}
					} else if(Map.class.isAssignableFrom(f.getType())){ // the field is a map,... progress with its entries
						Map<?,?> oldMap = (Map<?,?>) oldValue;
						Map<?,?> newMap = (Map<?,?>) newValue;
						if(oldMap == null && newMap != null) { // all map entries were added
							for(Object key : newMap.keySet()){
								differences.add(SnapshotDifference.createInsert(f.getName() + "." + key, newMap.get(key)));
							}
						} else if(oldMap != null && newMap == null) { // all map entries were deleted
							for(Object key : oldMap.keySet()){
								differences.add(SnapshotDifference.createDelete(f.getName() + "." + key, oldMap.get(key)));
							}
						} else { // compare entries manually
							for(Object key : oldMap.keySet()){
								if(newMap.containsKey(key)){
									if(!oldMap.get(key).equals(newMap.get(key))){
										differences.add(SnapshotDifference.createUpdate(f.getName() + "." + key, oldMap.get(key), newMap.get(key)));
									}
								} else {
									differences.add(SnapshotDifference.createDelete(f.getName() + "." + key, oldMap.get(key)));
								}
							}
							for(Object key : newMap.keySet()){
								if(!oldMap.containsKey(key)){
									differences.add(SnapshotDifference.createInsert(f.getName() + "." + key, newMap.get(key)));
								}
							}
						}
					} else if(ISnapshot.class.isAssignableFrom(f.getType())) {
						ISnapshot newSN = (ISnapshot) newValue;
						ISnapshot oldSN = (ISnapshot) oldValue;
						differences.addAll(oldSN.getDifference(newSN));
					} else {
						if((newValue != null && !newValue.equals(oldValue)) || (oldValue != null && !oldValue.equals(newValue))){
							System.out.println(f.getName() + ": " + oldValue + " -> " + newValue);
							differences.add(SnapshotDifference.createUpdate(f.getName(), oldValue, newValue));
						}
					}
				}
				if(f.isAnnotationPresent(Identifier.class)){
					identifier = oldValue.toString();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				f.setAccessible(false);
			}
		}
		// add identifier for more context information 
		for(SnapshotDifference diff : differences){
			diff.addContextAsFirst(clazz, identifier);
		}
		return differences;
	}

}
