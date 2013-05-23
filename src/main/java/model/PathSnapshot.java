package model;

import java.util.ArrayList;
import java.util.List;

import com.google.code.morphia.annotations.Embedded;

/**
 * This class summarizes all file snapshots of any given path
 * @author Stefan Weghofer
 */
@Embedded
public class PathSnapshot {

	private String path;
	private boolean exists;
	
	@Embedded
	private List<FileSnapshot> fileSnapshots;

	// empty constructor for MongoDB
	public PathSnapshot() {}
	
	public PathSnapshot(String path) {
		this.path = path;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @return the fileSnapshots
	 */
	public List<FileSnapshot> getFileSnapshots() {
		return fileSnapshots;
	}

	/**
	 * @param fileSnapshots the fileSnapshots to set
	 */
	public void setFileSnapshots(List<FileSnapshot> fileSnapshots) {
		this.fileSnapshots = fileSnapshots;
	}
	
	/**
	 * Adds a file snapshot to this path snapshot
	 * @param fileSnapshot the file snapshot to add
	 */
	public void addFileSnapshot(FileSnapshot fileSnapshot) {
		if(this.fileSnapshots == null) {
			this.fileSnapshots = new ArrayList<FileSnapshot>();
		}
		if(!this.fileSnapshots.contains(fileSnapshot)){
			this.fileSnapshots.add(fileSnapshot);
		}
	}

	/**
	 * Adds all file snapshots to this path snapshot
	 * @param fileSnapshots the snapshots to add
	 */
	public void addAllFileSnapshots(List<FileSnapshot> fileSnapshots) {
		for(FileSnapshot sn : fileSnapshots){
			this.addFileSnapshot(sn);
		}
	}

	/**
	 * @return the exists
	 */
	public boolean isExists() {
		return exists;
	}

	/**
	 * @param exists the exists to set
	 */
	public void setExists(boolean exists) {
		this.exists = exists;
	}

}
