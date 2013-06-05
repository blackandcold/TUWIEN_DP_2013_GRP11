package enrichment;

import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;

import model.HardwareSnapshot;
import model.OperatingSystemSnapshot;
import enrichment.dto.SystemChangeSynopsis;

/**
 * This interface describes which methods a resilient webservice has to expose
 * @author Stefan Weghofer
 */
public interface IResilientWebService {

	@WebMethod
	public String identifyYourself() throws EnrichmentFailedException;

	@WebMethod
	public OperatingSystemSnapshot identifySWEnvironment() throws EnrichmentFailedException;

	@WebMethod
	public HardwareSnapshot identifyHWEnvironment() throws EnrichmentFailedException;

	@WebMethod
	public List<SystemChangeSynopsis> serviceChangesSince(Date since) throws EnrichmentFailedException;

	@WebMethod
	public List<SystemChangeSynopsis> swEnvironmentChangesSince(Date since) throws EnrichmentFailedException;

	@WebMethod
	public List<SystemChangeSynopsis> hwEnvironmentChangesSince(Date since) throws EnrichmentFailedException;

}
