package dao;

import dao.impl.mongo.InventoryDAOMongo;

/**
 * A factory for creating the MongoDB DAOs
 * @author Stefan Weghofer
 */
public class DAOFactory {

	public static IInventoryDAO createInventoryDAO(DbSettings settings){
		return new InventoryDAOMongo(settings);
	}
}
