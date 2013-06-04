package enrichment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;

import model.HardwareSnapshot;
import model.Inventory;
import model.OperatingSystemSnapshot;
import model.SnapshotDifference;
import dao.DAOFactory;
import dao.DbSettings;
import dao.IInventoryDAO;
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
	 * Resilient WebService Methods
	 * *******************************************************/

	@WebMethod
	public OperatingSystemSnapshot identifySWEnvironment() throws EnrichmentFailedException  {
		System.out.println(this.inventoryDAO.getLatestInventory().getTimestamp());
		return this.inventoryDAO.getLatestInventory().getOperatingSystemSnapshot();
	}

	@WebMethod
	public HardwareSnapshot identifyHWEnvironment() throws EnrichmentFailedException  {
		return this.inventoryDAO.getLatestInventory().getHardwareSnapshot();
	}

	@WebMethod
	public ServiceChangeTimeline serviceChangesSince(Date since) throws EnrichmentFailedException  {
		// TODO Auto-generated method stub
		return null;
	}

	@WebMethod
	public List<SystemChange> swEnvironmentChangesSince(Date since) throws EnrichmentFailedException  {
		List<Inventory> timeline = this.inventoryDAO.getInventoryTimeline(since);
		List<SnapshotDifference> differences = new ArrayList<SnapshotDifference>();
		Inventory predecessor;
		Inventory successor;
		for(int i=0;(i+1)<timeline.size();i++){
			predecessor = timeline.get(i);
			successor = timeline.get(i+1);
			differences.addAll(predecessor.getDifference(successor));
		}
		for(SnapshotDifference dif : differences){
			System.out.println(String.format("%s %s %s %s %s", dif.getScope().getName(), dif.getIdentifier(), dif.getDifferenceType(), dif.getOldValue(), dif.getNewValue()));
		}
		return null;
	}

	@WebMethod
	public List<SystemChange> hwEnvironmentChangesSince(Date since) throws EnrichmentFailedException  {
		// TODO Auto-generated method stub
		return null;
	}


}
