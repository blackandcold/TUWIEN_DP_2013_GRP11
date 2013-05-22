package enrichment;

import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;

import enrichment.dto.ServiceChangeTimeline;
import enrichment.dto.SystemChange;

import model.HardwareSnapshot;
import model.OperatingSystemSnapshot;

/**
 * This interface describes which methods a resilient webservice has to expose
 * @author Stefan Weghofer
 */
public interface IResilientWebService {

	@WebMethod
	public String identifyYourself();

	@WebMethod
	public OperatingSystemSnapshot identifySWEnvironment();

	@WebMethod
	public HardwareSnapshot identifyHWEnvironment();

	@WebMethod
	public ServiceChangeTimeline serviceChangesSince(Date since);

	@WebMethod
	public List<SystemChange> swEnvironmentChangesSince(Date since);

	@WebMethod
	public List<SystemChange> hwEnvironmentChangesSince(Date since);

}
