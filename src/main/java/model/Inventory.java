package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

/**
 * This class represents the result of an inventory service run. It contains information
 * about the hardware, OS and any observed paths
 * @author Stefan Weghofer
 */
@Entity
public class Inventory extends AbstractSnapshot {

	@Id 
	private ObjectId id;

	private Date timestamp;

	@Embedded
	private HardwareSnapshot hardwareSnapshot;

	@Embedded
	private OperatingSystemSnapshot osSnapshot;

	@Embedded
	private List<PathSnapshot> pathSnapshots;

	public Inventory() {
		this.id =  new ObjectId();
		this.timestamp = new Date();
	}
	
	/**
	 * @return the id
	 */
	public ObjectId getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(ObjectId id) {
		this.id = id;
	}

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
	 * @return the hardwareSnapshot
	 */
	public HardwareSnapshot getHardwareSnapshot() {
		return hardwareSnapshot;
	}

	/**
	 * @param hardwareSnapshot the hardwareSnapshot to set
	 */
	public void setHardwareSnapshot(HardwareSnapshot hardwareSnapshot) {
		this.hardwareSnapshot = hardwareSnapshot;
	}

	/**
	 * @return the osSnapshot
	 */
	public OperatingSystemSnapshot getOperatingSystemSnapshot() {
		return osSnapshot;
	}

	/**
	 * @param osSnapshot the osSnapshot to set
	 */
	public void setOperatingSystemSnapshot(OperatingSystemSnapshot osSnapshot) {
		this.osSnapshot = osSnapshot;
	}

	/**
	 * @return the pathSnapshots
	 */
	public List<PathSnapshot> getPathSnapshots() {
		return pathSnapshots;
	}

	/**
	 * @param pathSnapshots the pathSnapshots to set
	 */
	public void setPathSnapshots(List<PathSnapshot> pathSnapshots) {
		this.pathSnapshots = pathSnapshots;
	}

	/**
	 * Adds a path snapshot to this inventory
	 * @param fileSnapshot the file snapshot to add
	 */
	public void addPathSnapshot(PathSnapshot pathSnapshot) {
		if(this.pathSnapshots == null) {
			this.pathSnapshots = new ArrayList<PathSnapshot>();
		}
		if(!this.pathSnapshots.contains(pathSnapshot)){
			this.pathSnapshots.add(pathSnapshot);
		}
	}


}
