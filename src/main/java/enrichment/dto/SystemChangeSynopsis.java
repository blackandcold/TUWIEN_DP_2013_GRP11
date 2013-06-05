package enrichment.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.SnapshotDifference;

/**
 * This class 
 * @author Stefan Weghofer
 */
public class SystemChangeSynopsis {

	private Date timestamp;
	private List<SystemChange> systemChanges;
		
	public SystemChangeSynopsis() { }
	
	/**
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	/**
	 * @return the systemChanges
	 */
	public List<SystemChange> getSystemChanges() {
		return systemChanges;
	}
	
	/**
	 * @return the systemChanges
	 */
	public void setSystemChanges(List<SystemChange> changes) {
		this.systemChanges = changes;
	}

	/**
	 * Creates a new synopsis for the given date and snapshot differences
	 * @param timestamp of the synopsis
	 * @param differences of the synopsis
	 * @return a new system change synopsis
	 */
	public static SystemChangeSynopsis create(Date timestamp, List<SnapshotDifference> differences){
		SystemChangeSynopsis timeline = new SystemChangeSynopsis();
		timeline.timestamp = timestamp;
		timeline.systemChanges = new ArrayList<SystemChange>();
		for(SnapshotDifference diff : differences){
			timeline.systemChanges.add(SystemChange.create(diff));
		}
		return timeline;
	}
}
