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

	// Item Management
	private final String ITEM_REGISTRATION = "INSERT into item(name,itemType,description,price) VALUES(?,?,?,?)";
	private final String ITEM_UPDATEITEMDETAILS = "UPDATE item SET name = ?, itemType = ?, description = ?, price= ? WHERE id = ?";
	private final String ITEM_DELETEITEM = " DELETE FROM item  WHERE id = ?";
	private final String ITEM_GETALLITEMS = "SELECT * FROM item i";
	private final String ITEM_GETITEMBYID = "SELECT * FROM item i WHERE i.id = ? ";
	private final String ITEMTYPE_GETITEMTYPEBYID = "SELECT * FROM itemType WHERE id = ?";

	public String getITEM_REGISTRATION() {
		return ITEM_REGISTRATION;
	}

	public String getITEM_UPDATEITEMDETAILS() {
		return ITEM_UPDATEITEMDETAILS;
	}

	public String getITEM_DELETEITEM() {
		return ITEM_DELETEITEM;
	}

	public String getITEM_GETALLITEMS() {
		return ITEM_GETALLITEMS;
	}

	public String getITEM_GETITEMBYID() {
		return ITEM_GETITEMBYID;
	}

	public String getITEMTYPE_GETITEMTYPEBYID() {
		return ITEMTYPE_GETITEMTYPEBYID;
	}

}
