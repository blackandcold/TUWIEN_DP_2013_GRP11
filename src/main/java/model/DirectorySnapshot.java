package model;

import java.util.List;

public class DirectorySnapshot {

	private String directoryPath;
	private List<DirectorySnapshot> subDirectorySnapshots;
	
	public DirectorySnapshot(String directoryPath) {
		this.directoryPath = directoryPath;
	}
	
	public String getDirectoryPath() {
		return this.directoryPath;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof DirectorySnapshot)) {
			return false;
		}
		DirectorySnapshot comp = (DirectorySnapshot) obj;
		return comp.getDirectoryPath().equals(this.directoryPath);
	}

}
