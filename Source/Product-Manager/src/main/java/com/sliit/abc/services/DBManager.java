package com.sliit.abc.services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sliit.abc.model.ItemResponse;
import com.sliit.abc.model.ItemType;
import com.sliit.abc.util.Crypt;
import com.sliit.abc.util.DBConnection;
import com.sliit.abc.util.SQLQueryManager;

public class DBManager {

	private static SQLQueryManager sqlObject;
	private static DBManager dbObject;

	private DBManager() {
	}

	public static DBManager getInstance() {
		if (dbObject == null) {
			synchronized (DBManager.class) {
				if (dbObject == null) {
					dbObject = new DBManager();
					sqlObject = SQLQueryManager.getInstance();
					new Crypt();
				}
			}
		}
		return dbObject;
	}

//Item Management Class
	static class ItemClass {

		public static JSONObject registerItem(String name, String itemType, String description, String price) {
			JSONObject j = new JSONObject();
			Connection con = null;
			PreparedStatement ps;

			try {
				con = DBConnection.connect();
				ps = con.prepareStatement(sqlObject.getITEM_REGISTRATION());
				ps.setString(1, name);
				ps.setLong(2, Long.parseLong(itemType));
				ps.setString(3, description);
				ps.setString(4, price);
				int i = ps.executeUpdate();
				if (i > 0) {
					j.put("status", "success");
				} else {
					j.put("status", "error");
				}

			} catch (Exception e) {
				return j.put("status", e.getMessage());
			}

			return j;
		}

		public static JSONObject updateItemDetails(String id, String name, String itemType, String description,
				String price) {
			JSONObject j = new JSONObject();

			Connection con = null;
			PreparedStatement ps = null;

			try {

				con = DBConnection.connect();
				ps = con.prepareStatement(sqlObject.getITEM_UPDATEITEMDETAILS());

				ps.setString(1, name);
				ps.setLong(2, Long.parseLong(itemType));
				ps.setString(3, description);
				ps.setString(4, price);
				ps.setLong(5, Long.parseLong(id));
				int i = ps.executeUpdate();
				if (i > 0) {
					j.put("status", "success");
				} else {
					j.put("status", "error");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			return j;
		}

		public static JSONArray getAllItems() {
			JSONArray j = null;

			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			ArrayList<ItemResponse> itemList = new ArrayList<ItemResponse>();

			try {
				con = DBConnection.connect();
				ps = con.prepareStatement(sqlObject.getITEM_GETALLITEMS());
				rs = ps.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						ItemResponse i = new ItemResponse();
						i.setId(rs.getLong(1));
						i.setName(rs.getString(2));
						i.setDescription(rs.getString(3));
						i.setItemType(rs.getString(4));
						i.setPrice(rs.getString(5));
						itemList.add(i);
					}
					j = new JSONArray(itemList);
				} else {
					j = null;
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (Exception e) {
						/* ignored */}
				}
				if (ps != null) {
					try {
						ps.close();
					} catch (Exception e) {
						/* ignored */}
				}
			}
			return j;
		}

		public static ItemResponse getItemById(String id) {

			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			ItemResponse i = null;

			try {
				con = DBConnection.connect();
				ps = con.prepareStatement(sqlObject.getITEM_GETITEMBYID());
				ps.setLong(1, Long.parseLong(id));
				rs = ps.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						i = new ItemResponse();
						i.setId(rs.getLong(1));
						i.setName(rs.getString(2));
						i.setDescription(rs.getString(3));
						i.setItemType(rs.getString(4));
						i.setPrice(rs.getString(5));
					}
				} else {
					return null;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return i;
		}

		public static JSONObject deleteItem(String id) {
			JSONObject j = new JSONObject();

			Connection con = null;
			PreparedStatement ps = null;

			try {

				con = DBConnection.connect();
				ps = con.prepareStatement(sqlObject.getITEM_DELETEITEM());
				ps.setLong(1, Long.parseLong(id));
				int i = ps.executeUpdate();
				if (i > 0) {
					j.put("status", "success");
				} else {
					j.put("status", "error");
				}

			} catch (Exception e) {
				e.printStackTrace();
				j.put("status", e.getMessage());
			}

			return j;
		}

		private static ItemType getItemTypeById(String id) {

			ItemType itype = null;

			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;

			try {
				con = DBConnection.connect();
				ps = con.prepareStatement(sqlObject.getITEMTYPE_GETITEMTYPEBYID());
				ps.setLong(1, Long.parseLong(id));
				rs = ps.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						itype = new ItemType(Long.parseLong(rs.getString(1)), rs.getString(2));
					}
				} else {
					return null;
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (Exception e) {
						/* ignored */}
				}
				if (ps != null) {
					try {
						ps.close();
					} catch (Exception e) {
						/* ignored */}
				}
			}
			return itype;

		}
	}
}
