package recorder.windows;

import java.net.UnknownHostException;

import model.HardwareSnapshot;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIComServer;
import org.jinterop.dcom.core.JIProgId;
import org.jinterop.dcom.core.JISession;

import recorder.AbstractRecorder;
/**
 * This class performs the inventory service for the Microsoft Windows operating system.
 * @author Stefan Weghofer
 */
public class WindowsRecorder 
	extends AbstractRecorder {

	@Override
	protected HardwareSnapshot performHardwareInventory() {
		/*
            String os = System.getProperty("s.name").toLowerCase();
            try 
            {   
                if(os.contains("win"))
                {
                    return windowsSystemInventory();
                }
                else if(os.contains("mac"))
                {
                    // TODO mac specific HW
                }
                else if(os.contains("nix")|| os.contains("nux"))
                {
                    // TODO unix specific HW
                }
            } catch (JIException ex) {
                Logger.getLogger(WindowsRecorder.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(WindowsRecorder.class.getName()).log(Level.SEVERE, null, ex);
            }*/
            return new HardwareSnapshot();
 	}

        private HardwareSnapshot windowsSystemInventory() throws JIException, UnknownHostException
        {
            JISession session = JISession.createSession("localhost","wmitest","");

            // TODO 
            JIComServer comServer = new JIComServer(JIProgId.valueOf("WMI.Application"),"127.0.0.1",session);

            JISession.destroySession(session);

            return null;

        }
}
