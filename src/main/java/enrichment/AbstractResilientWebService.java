package enrichment;

import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;

import dao.DAOFactory;
import dao.DbSettings;
import dao.IInventoryDAO;

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

	private IInventoryDAO inventoryDAO;

	public AbstractResilientWebService(DbSettings settings){
		this.inventoryDAO = DAOFactory.createInventoryDAO(settings);
	}


	/* *******************************************************
	 * Abstract Methods
	 * *******************************************************/

	/**
	 * Has to be overridden is sub classes to get the database connection information
	 * @return a dbsettings object specifying the connection to the database
	 */
	//protected abstract DbSettings getDbSettings();
	
	

	/* *******************************************************
	 * Resilient WebService Methods
	 * *******************************************************/

	@Override
	@WebMethod
	public OperatingSystemSnapshot identifySWEnvironment() throws EnrichmentFailedException  {
		System.out.println(this.inventoryDAO.getLatestInventory().getTimestamp());
		return this.inventoryDAO.getLatestInventory().getOperatingSystemSnapshot();
	}

	@Override
	@WebMethod
	public HardwareSnapshot identifyHWEnvironment() throws EnrichmentFailedException  {
		return this.inventoryDAO.getLatestInventory().getHardwareSnapshot();
	}

	@Override
	@WebMethod
	public ServiceChangeTimeline serviceChangesSince(Date since) throws EnrichmentFailedException  {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@WebMethod
	public List<SystemChange> swEnvironmentChangesSince(Date since) throws EnrichmentFailedException  {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@WebMethod
	public List<SystemChange> hwEnvironmentChangesSince(Date since) throws EnrichmentFailedException  {
		// TODO Auto-generated method stub
		return null;
	}



}
