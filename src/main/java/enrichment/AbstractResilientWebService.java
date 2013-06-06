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
import enrichment.dto.SystemChangeSynopsis;

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
		return this.inventoryDAO.getLatestInventory().getOperatingSystemSnapshot();
	}

	@WebMethod
	public HardwareSnapshot identifyHWEnvironment() throws EnrichmentFailedException  {
		return this.inventoryDAO.getLatestInventory().getHardwareSnapshot();
	}

	@WebMethod
	public List<SystemChangeSynopsis> serviceChangesSince(Date since) throws EnrichmentFailedException  {
		List<SystemChangeSynopsis> result = new ArrayList<SystemChangeSynopsis>();
		List<Inventory> timeline = this.inventoryDAO.getInventoryTimeline(since);
		List<SnapshotDifference> differences;
		Inventory predecessor, successor;
		for(int i=0;(i+1)<timeline.size();i++){
			predecessor = timeline.get(i);
			successor = timeline.get(i+1);
			if(predecessor != null && successor != null) {
				differences = predecessor.getDifference(successor);
				if(differences.size() > 0) {
					result.add(SystemChangeSynopsis.create(successor.getTimestamp(), differences));
				}
			}
		}
		return result;
	}

	@WebMethod
	public List<SystemChangeSynopsis> swEnvironmentChangesSince(Date since) throws EnrichmentFailedException  {
		List<SystemChangeSynopsis> result = new ArrayList<SystemChangeSynopsis>();
		List<Inventory> timeline = this.inventoryDAO.getInventoryTimeline(since);
		List<SnapshotDifference> differences;
		OperatingSystemSnapshot predecessor, successor;
		for(int i=0;(i+1)<timeline.size();i++){
			predecessor = timeline.get(i).getOperatingSystemSnapshot();
			successor = timeline.get(i+1).getOperatingSystemSnapshot();
			if(predecessor != null && successor != null) {
				differences = predecessor.getDifference(successor);
				if(differences.size() > 0) {
					result.add(SystemChangeSynopsis.create(timeline.get(i+1).getTimestamp(), differences));
				}
			}
		}
		return result;
	}

	@WebMethod
	public List<SystemChangeSynopsis> hwEnvironmentChangesSince(Date since) throws EnrichmentFailedException  {
		List<SystemChangeSynopsis> result = new ArrayList<SystemChangeSynopsis>();
		List<Inventory> timeline = this.inventoryDAO.getInventoryTimeline(since);
		List<SnapshotDifference> differences;
		HardwareSnapshot predecessor, successor;
		for(int i=0;(i+1)<timeline.size();i++){
			predecessor = timeline.get(i).getHardwareSnapshot();
			successor = timeline.get(i+1).getHardwareSnapshot();
			if(predecessor != null && successor != null) {
				differences = predecessor.getDifference(successor);
				if(differences.size() > 0) {
					result.add(SystemChangeSynopsis.create(timeline.get(i+1).getTimestamp(), differences));
				}
			}
		}
		return result;
	}

}
