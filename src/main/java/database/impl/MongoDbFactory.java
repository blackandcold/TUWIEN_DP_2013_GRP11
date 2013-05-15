package database.impl;

import javax.persistence.EntityManager;

import com.mongodb.DB;

import datebase.IMongoDbDataLoader;
import datebase.IMongoDbQuery;

public class MongoDbFactory {

	public IMongoDbDataLoader createDataLoader(EntityManager em) 
	{
		//TODO
		return null;
	}

	public IMongoDbQuery createQuery(DB db) 
	{
		//TODO
		return null;
	}

}
