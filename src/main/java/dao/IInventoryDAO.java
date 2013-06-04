package dao;

import java.util.Date;
import java.util.List;

import model.Inventory;

import org.bson.types.ObjectId;

/**
 * This interface specifies database operations possible on the inventory store
 * @author Stefan Weghofer
 */
public interface IInventoryDAO {
	
	/**
	 * Reads all items of this type from the database
	 * @return a list of all items
	 * @throws DAOException
	 */
	List<Inventory> readAll() throws DAOException;

	/**
	 * Reads one Inventory object by id
	 * @return the inventory with the given id
	 * @throws DAOException
	 */
	Inventory readByID(ObjectId id) throws DAOException;

	/**
	 * Adds a new item to the database
	 * @param item to be added
	 * @throws DAOException
	 */
	void add(Inventory item) throws DAOException;
	
	/**
	 * Gets the latest inventory item from the database
	 * @return the latest inventory item
	 */
	Inventory getLatestInventory();

	/**
	 * Retrieves the latest inventory before the since date 
	 * (if none exists, the oldest inventory in general) and 
	 * all distinct inventories until today
	 * @return a list of distinct inventories
	 */
	List<Inventory> getInventoryTimeline(Date since);
	
}