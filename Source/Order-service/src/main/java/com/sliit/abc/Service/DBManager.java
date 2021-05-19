package com.sliit.abc.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.JSONObject;

import com.mysql.cj.protocol.Resultset;
import com.sliit.abc.Dto.OrderResponse;
import com.sliit.abc.util.DBConnection;

public class DBManager {

	public static JSONObject createOrder(String orderId, long customer, long product, String paymentId, String cartId) {
		JSONObject j = null;
		Connection con = null;
		PreparedStatement ps;

		try {
			j = new JSONObject();
			con = DBConnection.connect();
			ps = con.prepareStatement("INSERT INTO orderI VALUES('" + orderId + "', " + customer + ", " + product
					+ ", '" + paymentId + "', '" + cartId + "')");
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

	public static JSONObject getOrderById(String id) {
		JSONObject j = null;
		Connection con = null;
		PreparedStatement ps;
		ResultSet rs;
		try {
			j = new JSONObject();
			con = DBConnection.connect();
			ps = con.prepareStatement("SELECT * FROM orderI WHERE id = '" + id + "'");
			rs = ps.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					j.put("customer", rs.getLong(2));
					j.put("product", rs.getLong(3));
					j.put("paymentId", rs.getString(4));
					j.put("cartId", rs.getString(5));
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
