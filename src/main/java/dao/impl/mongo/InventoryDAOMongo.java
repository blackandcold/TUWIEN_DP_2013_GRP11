package dao.impl.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import dao.DAOException;
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


	public Inventory getLatestInventory() {
		DBCursor cursor = null;
		try {
			openConnection();
			DBCollection coll = this.getCollection();
			cursor = coll.find().sort(new BasicDBObject("timestamp", -1)).limit(1);
			if(cursor.count() >= 1) { // cursor.count() should be 0 or 1, but we are encountering a MondoDB limit() bug here
				return this.toModel(cursor.next());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new DAOException("Reading latest inventory failed.", ex);
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			closeConnection();
		}
		throw new DAOException("No inventory found");
	}

}
