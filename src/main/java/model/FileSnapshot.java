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
	private long lastModified;
	private long size;
	private boolean hidden;
	private boolean directory;
	private boolean couldNotRead;
	
	public FileSnapshot(String filePath) {
		this.filePath = filePath;
		this.couldNotRead = false; // set if a file or directory could not be analyzed
		this.size = -1;
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

	/**
	 * @return the lastModified
	 */
	public long getLastModified() {
		return lastModified;
	}

	/**
	 * @param lastModified the lastModified to set
	 */
	public void setLastModified(long lastModified) {
		this.lastModified = lastModified;
	}

	/**
	 * @return the size
	 */
	public long getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(long size) {
		this.size = size;
	}

	/**
	 * @return the hidden
	 */
	public boolean isHidden() {
		return hidden;
	}

	/**
	 * @param hidden the hidden to set
	 */
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	/**
	 * @return the directory
	 */
	public boolean isDirectory() {
		return directory;
	}

	/**
	 * @param directory the directory to set
	 */
	public void setDirectory(boolean directory) {
		this.directory = directory;
	}

	/**
	 * @return the couldNotRead
	 */
	public boolean isCouldNotRead() {
		return couldNotRead;
	}

	/**
	 * @param couldNotRead the couldNotRead to set
	 */
	public void setCouldNotRead(boolean couldNotRead) {
		this.couldNotRead = couldNotRead;
	}
}
