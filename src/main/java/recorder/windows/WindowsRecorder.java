package recorder.windows;

import model.DeviceSnapshot;
import model.HardwareSnapshot;
import recorder.AbstractRecorder;
/**
 * This class performs the inventory service for the Microsoft Windows operating system.
 * @author Johannes Kurz
 */
public class WindowsRecorder 
	extends AbstractRecorder {

	@Override
	protected HardwareSnapshot performHardwareInventory() 
        {
			DeviceSnapshot tmpSnapshot = new DeviceSnapshot();
			HardwareSnapshot hwSnapshot = new HardwareSnapshot();
			
			tmpSnapshot = generateDeviceSnapshot("Memory");
			hwSnapshot.addDeviceSnapshot(tmpSnapshot);
			
			tmpSnapshot = generateDeviceSnapshot("BIOS");
			hwSnapshot.addDeviceSnapshot(tmpSnapshot);
			
			tmpSnapshot = generateDeviceSnapshot("Mainboard");
			hwSnapshot.addDeviceSnapshot(tmpSnapshot);
			
            return hwSnapshot;
        }
	
		private DeviceSnapshot generateDeviceSnapshot(String hardWareName)
		{
			DeviceSnapshot tmpSnapshot = new DeviceSnapshot();
			String value;
			
			try 
			{
				switch(hardWareName)
				{
				case "Memory":
					value = jWMI.getWMIValue("SELECT * FROM Win32_ComputerSystem", "TotalPhysicalMemory");
					tmpSnapshot.addProperty("TotalPhysicalMemory", value);
					tmpSnapshot.setName("Memory");
					
					value = jWMI.getWMIValue("SELECT * FROM Win32_PhysicalMemory", "Description");
					tmpSnapshot.setDescription(value);
					
					value = jWMI.getWMIValue("SELECT * FROM Win32_PhysicalMemory", "FormFactor");
					tmpSnapshot.addProperty("FormFactor", value);
					
					value = jWMI.getWMIValue("SELECT * FROM Win32_PhysicalMemory", "Speed");
					tmpSnapshot.addProperty("Speed", value);
					
					
					tmpSnapshot.setName("Memory");
					break;
					
				case "BIOS":
					value = jWMI.getWMIValue("SELECT * FROM Win32_BIOS", "BIOSVersion");
					tmpSnapshot.addProperty("BIOSVersion", value);
					
					value = jWMI.getWMIValue("SELECT * FROM Win32_BIOS", "Caption");
					tmpSnapshot.setName(value);
					
					value = jWMI.getWMIValue("SELECT * FROM Win32_MemoryArray", "DeviceID");
					tmpSnapshot.setDescription(value);
					value = jWMI.getWMIValue("SELECT * FROM Win32_MemoryArray", "DeviceID");
					tmpSnapshot.setManufacturer(value);
					
					value = jWMI.getWMIValue("SELECT * FROM Win32_MemoryArray", "SerialNumber");
					tmpSnapshot.addProperty("SerialNumber", value);
					
					value = jWMI.getWMIValue("SELECT * FROM Win32_MemoryArray", "SMBIOSBIOSVersion");
					tmpSnapshot.setDriver(value);
					
					value = jWMI.getWMIValue("SELECT * FROM Win32_BIOS", "ReleaseDate");
					tmpSnapshot.addProperty("ReleaseDate", value);
					
					break;
					
				case "Mainboard":
					value = jWMI.getWMIValue("SELECT * FROM Win32_MotherboardDevice", "Description");
					tmpSnapshot.addProperty("Description", value);
					value = jWMI.getWMIValue("SELECT * FROM Win32_MotherboardDevice", "Name");
					tmpSnapshot.setName(value);
					value = jWMI.getWMIValue("SELECT * FROM Win32_MotherboardDevice", "PrimaryBusType");
					tmpSnapshot.addProperty("PrimaryBusType", value);
					
					
					break;
				}
			

				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            return tmpSnapshot;
			
		}
}
