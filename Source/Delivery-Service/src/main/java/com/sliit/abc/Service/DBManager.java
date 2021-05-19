package com.sliit.abc.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.JSONObject;

import com.sliit.abc.util.DBConnection;

public class DBManager {

	public static JSONObject addDelivery(String deliveryId, String orderId, long customerId, String deliveryDate,
			String deliveryDescription) {

		JSONObject j = null;
		Connection con = null;
		PreparedStatement ps;

		try {
			j = new JSONObject();
			con = DBConnection.connect();
			System.out.println(deliveryId + ", " + orderId + ", " + customerId + ", " + deliveryDate + ", " + deliveryDescription);
			ps = con.prepareStatement("INSERT INTO delivery VALUES('" + deliveryId + "', '" + orderId + "', "
					+ customerId + ", '" + deliveryDate + "', '" + deliveryDescription + "')");
			int i = ps.executeUpdate();
			if (i > 0) {
				j.put("status", "success");
			} else {
				j.put("status", "error");
			}

		} catch (Exception e) {
			System.out.println("QUery Error");
			j.put("status", "error");
			j.put("reason", e.getMessage());
		}
		return j;

	}

	public static JSONObject getDelivery(String id) {
		JSONObject j = null;
		Connection con = null;
		PreparedStatement ps;
		ResultSet rs;
		try {
			j = new JSONObject();
			con = DBConnection.connect();
			ps = con.prepareStatement("SELECT * FROM delivery WHERE id = '" + id + "'");
			rs = ps.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					j.put("id", id);
					j.put("orderId", rs.getString(2));
					j.put("customerId", rs.getLong(3));
					j.put("deliveryDate", rs.getString(4));
					j.put("deliveryDescription", rs.getString(5));
				}
			}
			return j;
		} catch (Exception e) {
			j.put("status", "error");
			j.put("reason", e.getMessage());
		}
		return j;
	}

}
