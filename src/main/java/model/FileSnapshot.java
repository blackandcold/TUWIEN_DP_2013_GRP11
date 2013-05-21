package model;

import com.google.code.morphia.annotations.Embedded;

/**
 * This class summarizes information about a single file if a path analysis is included
 * in the inventory service
 * @author Stefan Weghofer
 */
@Embedded
public class FileSnapshot {
	
	private String filePath;
	
	public FileSnapshot(String filePath) {
		this.filePath = filePath;
	}
	
	/**
	 * The file path of the target file
	 * @return the file path
	 */
	public String getFilePath() {
		return this.filePath;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof FileSnapshot)) {
			return false;
		}
		FileSnapshot comp = (FileSnapshot) obj;
		return comp.filePath.equals(this.filePath);
	}
}
