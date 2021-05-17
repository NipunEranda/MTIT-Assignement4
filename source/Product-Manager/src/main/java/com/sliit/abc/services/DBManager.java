package com.sliit.abc.services;

import com.sliit.abc.util.Crypt;
import com.sliit.abc.util.SQLQueryManager;

public class DBManager {
	
	private static SQLQueryManager sqlObject;
	private static DBManager dbObject;
	private static Crypt crypt;
	
	private DBManager() {
	}

	public static DBManager getInstance() {
		if (dbObject == null) {
			synchronized (DBManager.class) {
				if (dbObject == null) {
					dbObject = new DBManager();
					sqlObject = SQLQueryManager.getInstance();
					crypt = new Crypt();
				}
			}
		}
		return dbObject;
	}

}
