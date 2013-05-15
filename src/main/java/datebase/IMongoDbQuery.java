package datebase;

import java.util.List;

import com.mongodb.DBObject;

public interface IMongoDbQuery {

	// TODO http://docs.mongodb.org/ecosystem/tutorial/use-java-dbobject-to-perform-saves/
	public List<DBObject> getHWObjects();

}
