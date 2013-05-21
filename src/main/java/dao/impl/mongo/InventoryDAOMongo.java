package dao.impl.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import dao.DbSettings;
import dao.IInventoryDAO;
import model.Inventory;

/**
 * The DAO for the inventory model
 * @author Stefan Weghofer
 */
public class InventoryDAOMongo 
	extends AbstractDAOMongo<Inventory> 
	implements IInventoryDAO {

	public InventoryDAOMongo(DbSettings settings) {
		super(settings);
	}

	@Override
	protected String getCollectionName() {
		return "inventory";
	}

	@Override
	protected DBObject getFilter(Inventory item) {
		BasicDBObject filter = new BasicDBObject();
		filter.append("id", item.getId());
		return filter;
	}

	@Override
	protected Class<Inventory> getModelClass() {
		return Inventory.class;
	}

}
