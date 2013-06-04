package model;

import java.util.ArrayList;
import java.util.List;

import com.google.code.morphia.annotations.Embedded;

/**
 * This class summarizes the hardware composition of the monitored system
 * @author Stefan Weghofer
 */
@Embedded
public class HardwareSnapshot extends AbstractSnapshot {
	
	@Embedded
	private List<DeviceSnapshot> deviceSnapshots;

	public HardwareSnapshot() {}
	

	/**
	 * @return the deviceSnapshots
	 */
	public List<DeviceSnapshot> getDeviceSnapshots() {
		return deviceSnapshots;
	}

	/**
	 * @param deviceSnapshots the device snapshots to set
	 */
	public void setDeviceSnapshots(List<DeviceSnapshot> deviceSnapshots) {
		this.deviceSnapshots = deviceSnapshots;
	}
	
	/**
	 * Adds a device snapshot to this hardware snapshot
	 * @param deviceSnapshot the device snapshot to add
	 */
	public void addDeviceSnapshot(DeviceSnapshot deviceSnapshot) {
		if(this.deviceSnapshots == null) {
			this.deviceSnapshots = new ArrayList<DeviceSnapshot>();
		}
		if(!this.deviceSnapshots.contains(deviceSnapshot)){
			this.deviceSnapshots.add(deviceSnapshot);
		}
	}

	/**
	 * Adds all device snapshots to this hardware snapshot
	 * @param deviceSnapshots the snapshots to add
	 */
	public void addAllDeviceSnapshots(List<DeviceSnapshot> deviceSnapshots) {
		for(DeviceSnapshot sn : deviceSnapshots){
			this.addDeviceSnapshot(sn);
		}
	}
}
