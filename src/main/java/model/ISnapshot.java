package model;

import java.util.List;

/**
 * This interface specifies the fundamental methods for any snapshot 
 * @author Stefan Weghofer
 */
public interface ISnapshot {
	
	/**
	 * Calculates a hash value for this snapshot object. Can be used to quickly compare 
	 * the whole object to another one
	 * @return a hash value of this object
	 */
	String calculateHashValue();
	
	/**
	 * Gets the differences between the current snapshot and the passed one
	 * @param sn as Snapshot. The snapshot to which the difference shall be calculated
	 * @return a list of snapshot differences, one for each diverging value
	 */
	List<SnapshotDifference> getDifference(ISnapshot sn);
	
}
