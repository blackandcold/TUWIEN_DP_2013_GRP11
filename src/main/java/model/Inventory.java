package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Inventory {

	//private ObjectId _id;
	private Date timestamp;
	private HardwareSnapshot hardwareSnapshot;
	private OperatingSystemSnapshot softwareSnapshot;
	private List<DirectorySnapshot> directorySnapshots;

	public Inventory() {
		this.timestamp = new Date();
	}
	
	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public HardwareSnapshot getHardwareSnapshot() {
		return hardwareSnapshot;
	}

	public void setHardwareSnapshot(HardwareSnapshot hardwareSnapshot) {
		this.hardwareSnapshot = hardwareSnapshot;
	}

	public OperatingSystemSnapshot getSoftwareSnapshot() {
		return softwareSnapshot;
	}

	public void setSoftwareSnapshot(OperatingSystemSnapshot softwareSnapshot) {
		this.softwareSnapshot = softwareSnapshot;
	}

	public List<DirectorySnapshot> getDirectorySnapshots() {
		return directorySnapshots;
	}

	public void setDirectorySnapshots(List<DirectorySnapshot> directorySnapshots) {
		this.directorySnapshots = directorySnapshots;
	}

	public void addDirectorySnapshot(DirectorySnapshot directorySnapshot) {
		if(this.directorySnapshots == null) {
			this.directorySnapshots = new ArrayList<DirectorySnapshot>();
		}
		if(!this.directorySnapshots.contains(directorySnapshot)){
			this.directorySnapshots.add(directorySnapshot);
		}
	}
	
}
