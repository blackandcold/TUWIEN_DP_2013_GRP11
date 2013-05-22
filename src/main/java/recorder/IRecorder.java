package recorder;

import java.util.List;

import model.Inventory;

/**
 * This interface defines the methods for a system recorder
 * @author Stefan Weghofer
 */
public interface IRecorder {

	/**
	 * Performs a hardware and software analysis of the current system
	 * @return an inventory item describing the hard- and software environment
	 */
	Inventory performInventory();
	
	/**
	 * Performs a hardware and software analysis of the current system. For each path specified in
	 * fileTargets, a file analysis is performed
	 * @param fileTargets a list of file paths to analyze
	 * @return an inventory item describing the hard- and software environment
	 */
	Inventory performInventory(List<String> fileTargets);

}
