package enrichment;

import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;

import model.HardwareSnapshot;
import model.OperatingSystemSnapshot;
import enrichment.dto.ServiceChangeTimeline;
import enrichment.dto.SystemChange;

/**
 * This class defines a resilient webservice and can be used as base class to 
 * apply the behavior of resilient webservices to custom webservices 
 * @author Stefan Weghofer
 */
public abstract class AbstractResilientWebService
	implements IResilientWebService {

	@Override
	@WebMethod
	public String identifyYourself() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@WebMethod
	public OperatingSystemSnapshot identifySWEnvironment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@WebMethod
	public HardwareSnapshot identifyHWEnvironment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@WebMethod
	public ServiceChangeTimeline serviceChangesSince(Date since) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@WebMethod
	public List<SystemChange> swEnvironmentChangesSince(Date since) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@WebMethod
	public List<SystemChange> hwEnvironmentChangesSince(Date since) {
		// TODO Auto-generated method stub
		return null;
	}


}
