package dao.impl.mongo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Inventory;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import dao.DAOException;
import dao.DbSettings;
import dao.IInventoryDAO;

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

	public List<Inventory> getInventoryTimeline(Date since) {
		DBCursor cursor = null;
		try {
			openConnection();
			DBCollection coll = this.getCollection();
			// only get "light" versions and details later
			BasicDBObject projection = new BasicDBObject(); 
			projection.put("_id", 1);
			projection.put("hash", 1);
			// retrieve inventory directly before since
			Inventory firstInventory;
			BasicDBObject query = new BasicDBObject();
			query.put("timestamp", new BasicDBObject("$lte", since));
			cursor = coll.find(query, projection).sort(new BasicDBObject("timestamp", -1)).limit(1);
			if(cursor.count() >= 1) { // cursor.count() should be 0 or 1, but we are encountering a MondoDB limit() bug here
				firstInventory = this.toModel(cursor.next());
				cursor.close();
			} else { // if no inventory was recorded before since, take the absolute first one
				cursor = coll.find(null, projection).sort(new BasicDBObject("timestamp", 1)).limit(1);
				if(cursor.count() >= 1){
					firstInventory = this.toModel(cursor.next());
					cursor.close();
				} else {
					return new ArrayList<Inventory>(); // no inventories in the database
				}
			}
			// query changed hash values since the first inventory
			// naive implementation with no performance considerations
			Inventory current = firstInventory;
			Inventory next;
			ArrayList<Inventory> overview = new ArrayList<Inventory>();
			overview.add(firstInventory);
			query = new BasicDBObject();
			query.put("timestamp", new BasicDBObject("$gt", since));
			cursor = coll.find(query, projection).sort(new BasicDBObject("timestamp", 1));
			while (cursor.hasNext()) {
				DBObject o = cursor.next();
				next = this.toModel(o);
				if(next.getHashValue() == null || !next.getHashValue().equals(current.getHashValue())){
					overview.add(next);
					current = next;
				}
			}
			ArrayList<Inventory> result = new ArrayList<Inventory>();
			for(Inventory inv : overview){
				result.add(this.readByID(inv.getId()));
			}
			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new DAOException("Reading inventory timeline failed.", ex);
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			closeConnection();
		}
	}

	public Inventory readByID(ObjectId id) throws DAOException {
		try {
			openConnection();
			DBCollection coll = this.getCollection();
			DBObject filter = new BasicDBObject();
			filter.put("_id", id);
			DBObject result = coll.findOne(filter);
			if(result != null) {
				return this.toModel(result);
			}
			throw new RuntimeException("No recording found");
		} catch (Exception ex) {
			throw new DAOException("Reading inventory by id failed.", ex);
		} finally {
			closeConnection();
		}
	}

}
