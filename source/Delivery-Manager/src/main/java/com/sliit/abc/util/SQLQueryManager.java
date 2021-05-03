package com.sliit.abc.util;

import java.sql.Connection;

public class SQLQueryManager {
	
	public static void setInstance(SQLQueryManager instance) {
		SQLQueryManager.instance = instance;
	}

	private static SQLQueryManager instance;

	private SQLQueryManager() {
	}

	synchronized public static SQLQueryManager getInstance() {
		if (instance == null) {
			// synchronized block to remove overhead
			synchronized (Connection.class) {
				if (instance == null) {
					instance = new SQLQueryManager();
				}
			}
		}
		return instance;
	}


}
