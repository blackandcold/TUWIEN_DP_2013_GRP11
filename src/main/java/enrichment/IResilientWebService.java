package enrichment;

import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;

import model.HardwareSnapshot;
import model.OperatingSystemSnapshot;
import enrichment.dto.SystemChangeSynopsis;

/**
 * This interface describes which methods a resilient web service has to expose
 * @author Stefan Weghofer
 */
public interface IResilientWebService {

	/**
	 * Identifies the web service 
	 * @return a small message identifying the web service
	 * @throws EnrichmentFailedException
	 */
	@WebMethod
	public String identifyYourself() throws EnrichmentFailedException;

	/**
	 * Shows the software environment of the web service
	 * @return the software environment
	 * @throws EnrichmentFailedException
	 */
	@WebMethod
	public OperatingSystemSnapshot identifySWEnvironment() throws EnrichmentFailedException;

	/**
	 * Shows the hardware environment of the web service
	 * @return hardware environment
	 * @throws EnrichmentFailedException
	 */
	@WebMethod
	public HardwareSnapshot identifyHWEnvironment() throws EnrichmentFailedException;

	/**
	 * Shows the changes to the systems software, hardware and directories
	 * @param since the date since the changes occured
	 * @return a list of changes
	 * @throws EnrichmentFailedException
	 */
	@WebMethod
	public List<SystemChangeSynopsis> serviceChangesSince(Date since) throws EnrichmentFailedException;

	/**
	 * Shows the changes to the systems software
	 * @param since the date since the changes occured
	 * @return a list of changes
	 * @throws EnrichmentFailedException
	 */
	@WebMethod
	public List<SystemChangeSynopsis> swEnvironmentChangesSince(Date since) throws EnrichmentFailedException;

	/**
	 * Show the changes to the systems hardware
	 * @param since the date since the changes occured
	 * @return a list of changes
	 * @throws EnrichmentFailedException
	 */
	@WebMethod
	public List<SystemChangeSynopsis> hwEnvironmentChangesSince(Date since) throws EnrichmentFailedException;

}
