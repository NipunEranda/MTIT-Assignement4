package com.sliit.abc.service;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.json.JSONObject;

import com.sliit.abc.util.DBConnection;

public class DBManager {

	public static JSONObject createCart(String cartId, long customer, long product, double price, int quantity) {
		JSONObject j = null;
		Connection con = null;
		PreparedStatement ps1, ps2;

		System.out.println("Price : " + price);
		System.out.println("Quantity : " + quantity);
		System.out.println("Total : " + price * Double.parseDouble(String.valueOf(quantity)));
		
		try {
			j = new JSONObject();
			con = DBConnection.connect();
			ps1 = con.prepareStatement("INSERT INTO cart VALUES('" + cartId + "', " + customer + ", " + price * quantity + ")");
			ps2 = con.prepareStatement("INSERT INTO item_cart VALUES('" + cartId + "', " + product + ", " + quantity + ")");
			int i = ps1.executeUpdate();
			int k = ps2.executeUpdate();
			if (i > 0) {
				if (k > 0) {
					j.put("status", "success");
				} else {
					j.put("status", "error");
				}
			} else {
				j.put("status", "error");
			}

		} catch (Exception e) {
			j.put("status", "error");
			j.put("reason", e.getMessage());
		}
		return j;
	}
}
