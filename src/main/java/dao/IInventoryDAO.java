package dao;

import java.util.List;

import model.Inventory;

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
	 * Adds a new item to the database
	 * @param item to be added
	 * @throws DAOException
	 */
	void add(Inventory item) throws DAOException;
	
}