package recorder.windows;

import model.HardwareSnapshot;
import recorder.AbstractRecorder;

/**
 * This class performs the inventory service for the Microsoft Windows operating system.
 * @author Stefan Weghofer
 */
public class WindowsRecorder 
	extends AbstractRecorder {

	@Override
	protected HardwareSnapshot performHardwareInventory() {
            return null;
 	}

}
