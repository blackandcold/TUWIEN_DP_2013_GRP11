package dao.impl.mongo;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.code.morphia.Morphia;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

import dao.DAOException;
import dao.DbSettings;

/**
 * This class provides some helper methods for any concrete DAO
 * @author Stefan Weghofer
 * @param <T>
 */
public abstract class AbstractDAOMongo<T> {

    private DbSettings settings;
    private Mongo connection;
    private Morphia morphia;

    public AbstractDAOMongo(DbSettings settings) {
        this.settings = settings;
        this.morphia = new Morphia();
        this.morphia.mapPackage("model");
    }
    
    //
    // Overriding these in the sub classes will enable advanced DB methods
    //
    protected abstract String getCollectionName();
    protected abstract DBObject getFilter(T item);
    protected abstract Class<T> getModelClass();

    protected void openConnection() throws DAOException {
        try {
            connection = new Mongo(this.settings.getServerName(), 
                                   this.settings.getServerPort());
        } catch (UnknownHostException ex) {
            Logger.getLogger(AbstractDAOMongo.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException("Couldn't establish database connection to the specified host.", ex);
        }
    }
    
    protected void closeConnection() {
    	if(this.connection != null) {
    		this.connection.close();
    	}
    }

    protected DBCollection getCollection() {
        String collection = this.getCollectionName();
        return connection.getDB(this.settings.getDbName()).getCollection(collection);
    }

    protected Mongo getConnection() {
        return this.connection;
    }
       
    protected T toModel(DBObject obj) {
        return this.morphia.fromDBObject(this.getModelClass(), obj);
    }
    
    protected DBObject toDBObject(T item) {
        return this.morphia.toDBObject(item);
    }

    public long count() throws DAOException {
        try {
            openConnection();
            DBCollection coll = this.getCollection();
            long count = coll.count();
            closeConnection();
            return count;
        } catch (Exception ex) {
            throw new DAOException("Counting inventories failed.", ex);
        } finally {
            closeConnection();
        }
    }

    public List<T> readAll() throws DAOException {
        DBCursor cursor = null;
        ArrayList<T> items = new ArrayList<T>();
        try {
            openConnection();
            DBCollection coll = this.getCollection();
            cursor = coll.find();
            while (cursor.hasNext()) {
                DBObject o = cursor.next();
                T item = this.toModel(o);
                items.add(item);
            }
            return items;
        } catch (Exception ex) {
            throw new DAOException("Reading all inventories failed.", ex);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            closeConnection();
        }
    }

    public void add(T item) throws DAOException {
        try {
            openConnection();
            DBCollection coll = this.getCollection();
            DBObject obj = this.toDBObject(item);
            coll.save(obj);
        } catch (Exception ex) {
            throw new DAOException("Adding inventory failed.", ex);
        } finally {
            closeConnection();
        }
    }

    public void delete(T item) throws DAOException {
        try {
            openConnection();
            DBCollection coll = getCollection();
            DBObject filter = this.getFilter(item);
            coll.remove(filter);
        } catch (Exception ex) {
            throw new DAOException("Deleting inventories failed.", ex);
        } finally {
            closeConnection();
        }
    }

    public void update(T item) throws DAOException {
        try {
            openConnection();
            DBCollection coll = this.getCollection();
            DBObject filter = this.getFilter(item);
            coll.update(filter, this.toDBObject(item));
        } catch (Exception ex) {
            throw new DAOException("Deleting inventory failed.", ex);
        } finally {
            closeConnection();
        }
    }
}