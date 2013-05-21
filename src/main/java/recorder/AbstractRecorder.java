package recorder;

import model.HardwareSnapshot;
import model.Inventory;
import model.OperatingSystemSnapshot;
import model.PathSnapshot;

/**
 * This class can be used as base class for other recorders. It provides os-independent methods
 * for performing the inventory service
 * @author Stefan Weghofer
 */
public abstract class AbstractRecorder 
	implements IRecorder {
	
	@Override
	public Inventory performInventory() {
		Inventory inv = new Inventory();
		inv.setHardwareSnapshot(this.performHardwareInventory());
		inv.setOperatingSystemSnapshot(this.performOSInventory());
		return inv;
	}

	@Override
	public Inventory performInventory(String[] fileTargets) {
		Inventory inv = this.performInventory();
		for(String path : fileTargets){
			inv.addPathSnapshot(this.performPathInventory(path));
		}
		return inv;
	}

	
	/* *********************************************************************
	 * OS-independent Methods 
	 * These are directly implemented and can be overridden in sub classes
	 * *********************************************************************/

	protected OperatingSystemSnapshot performOSInventory(){
		OperatingSystemSnapshot snapshot = new OperatingSystemSnapshot();
		snapshot.setSystemName(System.getProperty("os.name"));
		snapshot.setArchitecture(System.getProperty("os.arch"));
		snapshot.setSystemVersion(System.getProperty("os.version"));
		snapshot.setJavaVersion(System.getProperty("java.version"));
		return snapshot;
	}
	
	protected PathSnapshot performPathInventory(String path){
		PathSnapshot pathSnapshot = new PathSnapshot(path);
		
		//TODO: iterate the directory tree and create the snapshots
		//pathSnapshot.addFileSnapshot(...)
		return pathSnapshot;
	}
	

	/* *********************************************************************
	 * OS-dependent Methods 
	 * are implemented by the sub classes
	 * *********************************************************************/
	
	/**
	 * Performs the hardware inventory. As this is os-dependent, the 
	 * implementation is located in the sub classes
	 * @return a snapshot of the systems hardware
	 */
	protected abstract HardwareSnapshot performHardwareInventory();

}
