package recorder;

import java.io.File;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import model.FileSnapshot;
import model.HardwareSnapshot;
import model.Inventory;
import model.NetworkInterfaceSnapshot;
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
	public Inventory performInventory(List<String> fileTargets) {
		Inventory inv = this.performInventory();
		if(fileTargets != null) {
			for(String path : fileTargets){
				inv.addPathSnapshot(this.performPathInventory(path));
			}
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
		snapshot.setJavaClassPath(System.getProperty("java.class.path"));
		snapshot.setEnvironmentVariables(System.getenv());
		snapshot.setNetworkInterfaceSnapshots(this.performNetworkInterfaceInventory());
		return snapshot;
	}
	
	protected List<NetworkInterfaceSnapshot> performNetworkInterfaceInventory(){
		try {
			List<NetworkInterfaceSnapshot> networkSnapshots = new ArrayList<NetworkInterfaceSnapshot>();
			NetworkInterfaceSnapshot networkSn;
			Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
	        for (NetworkInterface netint : Collections.list(nets)) {
	        	networkSn = new NetworkInterfaceSnapshot();
	        	networkSn.setDisplayName(netint.getDisplayName());
	        	networkSn.setName(netint.getName());
	        	networkSn.setHardwareAddress(netint.getHardwareAddress());
	            for (InterfaceAddress address : netint.getInterfaceAddresses()) {
	                networkSn.addAddress(String.format("%s/%d", address.getAddress().getHostAddress(), address.getNetworkPrefixLength()));
	            }
	        	networkSnapshots.add(networkSn);
	        }
			return networkSnapshots;
		} catch (SocketException e) {
			throw new InventoryFailedException("Inventory on network interfaces failed", e);
		}
	}
	
	protected PathSnapshot performPathInventory(String path){
		File file = new File(path);
		PathSnapshot pathSnapshot = new PathSnapshot(path);
		pathSnapshot.setExists(file.exists());
		if(file.exists()) {
			pathSnapshot.setFileSnapshots(this.performFileInventory(file));
		}
		return pathSnapshot;
	}
	
	private List<FileSnapshot> performFileInventory(File file){
		List<FileSnapshot> fileSnapshots = new ArrayList<FileSnapshot>();
		FileSnapshot fileSnapshot = new FileSnapshot(file.getAbsolutePath());
		fileSnapshot.setDirectory(file.isDirectory());
		fileSnapshot.setHidden(file.isHidden());
		fileSnapshot.setLastModified(new Date(file.lastModified()));
		fileSnapshots.add(fileSnapshot);
		if(file.isDirectory()) {
			if(file.listFiles() != null) {
				for(File child : file.listFiles()) {
					fileSnapshots.addAll(this.performFileInventory(child));
				}
			} else {
				fileSnapshot.setCouldNotRead(true);
			}
		} else {
			fileSnapshot.setSize(file.length()); // length() only returns a meaningful result on files
		}
		return fileSnapshots;
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
