package com.sliit.abc.services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sliit.abc.model.Customer;
import com.sliit.abc.model.Role;
import com.sliit.abc.util.Crypt;
import com.sliit.abc.util.DBConnection;
import com.sliit.abc.util.RandomCode;
import com.sliit.abc.util.SQLQueryManager;
import com.sliit.abc.util.SendMail;
import com.sliit.abc.util.lambdaworks.crypto.SCryptUtil;

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
				}
			}
		}
		return dbObject;
	}

	// Role Management Class
	static class RoleClass {

		/*
		 * private static RoleClass dbObject;
		 * 
		 * private RoleClass() { }
		 * 
		 * public static RoleClass getInstance() { if (dbObject == null) { synchronized
		 * (RoleClass.class) { if (dbObject == null) { dbObject = new RoleClass(); } } }
		 * return dbObject; }
		 */

		// Role Management Section
		public static JSONObject insertRole(String roleName, String roleDesc) {
			JSONObject j = new JSONObject();

			Connection con = null;
			PreparedStatement ps_insertRole = null;

			try {

				con = DBConnection.connect();
				ps_insertRole = con.prepareStatement(sqlObject.getINSERT_ROLE());
				ps_insertRole.setString(1, roleName);
				ps_insertRole.setString(2, roleDesc);

				if (ps_insertRole.executeUpdate() < 0) {
					j.put("status", "error");
				} else {
					j.put("status", "success");
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (ps_insertRole != null) {
					try {
						ps_insertRole.close();
					} catch (Exception e) {
						/* ignored */}
				}
			}

			return j;
		}

		public static JSONObject updateRole(String roleId, String roleName, String roleDesc, String isActive) {
			JSONObject j = new JSONObject();

			Connection con = null;
			PreparedStatement ps_updateRole = null;

			try {

				con = DBConnection.connect();
				ps_updateRole = con.prepareStatement(sqlObject.getUPDATE_ROLE());
				ps_updateRole.setString(1, roleName);
				ps_updateRole.setString(2, roleDesc);
				ps_updateRole.setInt(3, Integer.parseInt(isActive));
				ps_updateRole.setLong(4, Long.parseLong(roleId));

				if (ps_updateRole.executeUpdate() < 0) {
					j.put("status", "error");
				} else {
					j.put("status", "success");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			return j;
		}

		public static JSONObject deleteRole(String roleId) {
			JSONObject j = new JSONObject();

			Connection con = null;
			PreparedStatement ps_deleteRole = null;

			try {

				con = DBConnection.connect();
				ps_deleteRole = con.prepareStatement(sqlObject.getDELETE_ROLE());
				ps_deleteRole.setLong(1, Long.parseLong(roleId));

				if (ps_deleteRole.executeUpdate() < 0) {
					j.put("status", "error");
				} else {
					j.put("status", "success");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			return j;
		}

		public static List<String> getRoleList() {
			List<String> roleList = new ArrayList<>();

			PreparedStatement ps_getRoleList = null;
			ResultSet rs_getRoleList = null;
			Connection con = null;

			try {

				con = DBConnection.connect();
				ps_getRoleList = con.prepareStatement(sqlObject.getROLE_LIST());

				rs_getRoleList = ps_getRoleList.executeQuery();

				if (rs_getRoleList != null) {
					while (rs_getRoleList.next()) {
						Role r = new Role(rs_getRoleList.getLong(1), rs_getRoleList.getString(2),
								rs_getRoleList.getString(3));
						roleList.add(r.createRoleJsonObject().toString());
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			return roleList;
		}

	}

	// User Management Class
	static class UserClass {

		public static JSONObject checkEmailAvailability(String email) {
			JSONObject j = new JSONObject();
			PreparedStatement ps = null;
			ResultSet rs = null;
			Connection con = null;

			try {
				con = DBConnection.connect();
				ps = con.prepareStatement(sqlObject.getUSER_CHECKEMAILAVAILABILITY());
				ps.setString(1, email);
				rs = ps.executeQuery();
				if (rs != null) {
					if (rs.first()) {
						j.put("status", "error");
						j.put("reason", "email already exist!");
					} else {
						j.put("status", "success");
					}
				}

			} catch (Exception e) {
				j.put("status", "error");
			}
			return j;
		}

		public static JSONObject updateUserDetails(String uid, String firstName, String lastName, String initials,
				String dob, String phoneNo, String gender, String address) {
			JSONObject j = new JSONObject();

			Connection con = null;
			CallableStatement stm = null;

			try {

				con = DBConnection.connect();
				stm = con.prepareCall(sqlObject.getUPDATE_USER_DETAILS());
				stm.setString(1, firstName);
				stm.setString(2, lastName);
				stm.setString(3, initials);
				stm.setString(4, dob);
				stm.setString(5, phoneNo);
				stm.setString(6, gender);
				stm.setString(7, address);
				stm.setLong(8, Long.parseLong(uid));
				stm.registerOutParameter(9, Types.INTEGER);
				stm.execute();
				if (stm.getInt(9) == 1) {
					j.put("status", "success");
				} else {
					j.put("status", "error");
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (stm != null) {
					try {
						stm.close();
					} catch (Exception e) {
					}
				}
			}

			return j;
		}

		public static JSONObject isActive(String id) {
			JSONObject j = new JSONObject();

			PreparedStatement ps = null;
			ResultSet rs = null;
			Connection con = null;

			try {
				con = DBConnection.connect();
				ps = con.prepareStatement(sqlObject.getUSER_ISACTIVE());
				ps.setLong(1, Long.parseLong(id));
				rs = ps.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						j.put("isActive", rs.getInt(1));
					}
				}

			} catch (Exception e) {
				j.put("status", "error");
			}

			return j;
		}

	}

	// Login Management Class
	static class LoginClass {

		public static JSONObject login(String email, String password) {

			JSONObject j = new JSONObject();

			PreparedStatement ps_verifyLogin = null;
			ResultSet rs_verifyLogin = null;
			Connection con = null;
			CallableStatement stm = null;

			try {
				con = DBConnection.connect();
				ps_verifyLogin = con.prepareStatement(sqlObject.getLOGIN_VERIFY());
				ps_verifyLogin.setString(1, email);

				rs_verifyLogin = ps_verifyLogin.executeQuery();
				if (rs_verifyLogin.first() != false && SCryptUtil.check(password, rs_verifyLogin.getString(5))) {
					if (rs_verifyLogin.getInt(6) == 1) {

						Crypt ed = new Crypt(rs_verifyLogin.getString(7), rs_verifyLogin.getLong(3),
								rs_verifyLogin.getLong(2), email);
						String authString = ed.encode();

						j.put("status", "success");
						j.put("loginId", rs_verifyLogin.getLong(1));
						j.put("loginRole", rs_verifyLogin.getLong(2));
						j.put("userId", rs_verifyLogin.getLong(3));
						j.put("firstName", rs_verifyLogin.getString(4));
						j.put("loginCount", rs_verifyLogin.getInt(8) + 1);
						j.put("authString", authString);

						stm = con.prepareCall(sqlObject.getINCREASE_LOGIN_COUNT());
						stm.setInt(1, rs_verifyLogin.getInt(1));
						stm.registerOutParameter(2, Types.INTEGER);
						stm.execute();

					} else {
						j.put("status", "error");
						j.put("reason", "Email Confirmation is not validated");
					}
				} else {
					j.put("status", "error");
					j.put("userId", 0);
					j.put("authString", "");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (rs_verifyLogin != null) {
					try {
						rs_verifyLogin.close();
					} catch (Exception e) {
						/* ignored */}
				}
				if (ps_verifyLogin != null) {
					try {
						ps_verifyLogin.close();
					} catch (Exception e) {
						/* ignored */}
				}
			}

			return j;
		}

		public static JSONObject resetPassword(String email, String currentPassword, String newPassword) {
			JSONObject j = new JSONObject();

			Connection con = null;
			CallableStatement stm = null;
			try {
				con = DBConnection.connect();
				JSONObject passwordVerification = verifyPassword(email, currentPassword);

				if (passwordVerification.getString("status").equalsIgnoreCase("success")) {

					stm = con.prepareCall(sqlObject.getCHANGE_PASSWORD());
					stm.setString(1, SCryptUtil.scrypt(newPassword, 16, 16, 16));
					stm.setString(2, email);
					stm.registerOutParameter(3, Types.INTEGER);
					stm.execute();
					if (stm.getInt(3) == 1) {
						j.put("status", "success");
					} else {
						j.put("status", "error");
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
				j.put("status", e.getMessage());
			} finally {
				if (stm != null) {
					try {
						stm.close();
					} catch (Exception e) {
					}
				}
			}

			return j;
		}

		public static JSONObject forgotPassword(String email) {
			String code = RandomCode.GenCode(8);
			JSONObject j = new JSONObject();
			Connection con = null;
			PreparedStatement ps_getIdFromEmail = null;
			CallableStatement stm = null;

			ResultSet rs_getIdFromEmail = null;

			try {
				con = DBConnection.connect();
				ps_getIdFromEmail = con.prepareStatement(sqlObject.getID_FROM_EMAIL());
				ps_getIdFromEmail.setString(1, email);
				rs_getIdFromEmail = ps_getIdFromEmail.executeQuery();
				if (rs_getIdFromEmail.first() != false) {
					stm = con.prepareCall(sqlObject.getCHANGE_PASSWORD());
					stm.setString(1, SCryptUtil.scrypt(code, 16, 16, 16));
					stm.setString(2, email);
					stm.registerOutParameter(3, Types.INTEGER);
					stm.execute();
					if (stm.getInt(3) == 1) {
						j.put("status", "success");
						SendMail.sendNewPassword(email, code, "New Password for the User");
					} else {
						j.put("status", "error");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				j.put("status", e.getMessage());
			} finally {
				if (stm != null) {
					try {
						stm.close();
					} catch (Exception e) {
						j.put("status", e.getMessage());
					}
				}
			}
			return j;
		}

		public static JSONObject verifyPassword(String email, String currentPassword) {

			JSONObject j = new JSONObject();

			Connection con = null;
			PreparedStatement ps_verifyPassword = null;

			try {

				con = DBConnection.connect();
				ps_verifyPassword = con.prepareStatement(sqlObject.getVERIFY_PASSWORD());
				ps_verifyPassword.setString(1, email);

				ResultSet rs_verifyPassword = ps_verifyPassword.executeQuery();

				if (rs_verifyPassword.first() != false
						&& SCryptUtil.check(currentPassword, rs_verifyPassword.getString(1))) {
					j.put("status", "success");
				} else {
					j.put("status", "error");
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (ps_verifyPassword != null) {
					try {
						ps_verifyPassword.close();
					} catch (Exception e) {
						/* ignored */}
				}
			}

			return j;
		}

		public static JSONObject getRoleName(String roleId) {

			JSONObject j = new JSONObject();

			Connection con = null;
			PreparedStatement ps_getroleName = null;
			ResultSet rs_getroleName = null;

			try {
				con = DBConnection.connect();
				ps_getroleName = con.prepareStatement(sqlObject.getROLE_NAME());
				ps_getroleName.setLong(1, Long.parseLong(roleId));
				rs_getroleName = ps_getroleName.executeQuery();
				if (rs_getroleName != null) {
					while (rs_getroleName.next()) {
						j.put("status", "success");
						j.put("roleName", rs_getroleName.getString(1));
					}
				} else {
					j.put("status", "error");
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (rs_getroleName != null) {
					try {
						rs_getroleName.close();
					} catch (Exception e) {
						/* ignored */}
				}
				if (ps_getroleName != null) {
					try {
						ps_getroleName.close();
					} catch (Exception e) {
						/* ignored */}
				}
			}

			return j;
		}

		public static JSONObject emailConfirmation(String email, String confirmationCode) {
			JSONObject j = new JSONObject();

			Connection con = null;
			CallableStatement stm = null;

			try {

				con = DBConnection.connect();
				stm = con.prepareCall(sqlObject.getEMAIL_CONFIRMATION());
				stm.setString(1, email);
				stm.setString(2, confirmationCode);
				stm.registerOutParameter(3, Types.INTEGER);
				stm.execute();
				if (stm.getInt(3) == 1) {
					j.put("status", "success");
				} else {
					j.put("status", "error");
				}

			} catch (Exception e) {
				e.printStackTrace();
				j.put("status", e.getMessage());
			} finally {
				if (stm != null) {
					try {
						stm.close();
					} catch (Exception e) {
					}
				}
			}

			return j;
		}

		public static JSONObject requestConfirmationCode(String email) {
			JSONObject j = new JSONObject();

			Connection con = null;
			CallableStatement stm = null;

			try {
				String code = RandomCode.GenCode(8);
				con = DBConnection.connect();
				stm = con.prepareCall(sqlObject.getREQUEST_CODE());
				stm.setString(1, code);
				stm.setString(2, email);
				stm.registerOutParameter(3, Types.INTEGER);
				stm.execute();
				if (stm.getInt(3) == 1) {
					j.put("status", "success");
					SendMail.sendConfirmationCode(email, code, "Request Confirmation Code.");
				} else {
					j.put("status", "error");
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (stm != null) {
					try {
						stm.close();
					} catch (Exception e) {
						/* ignored */}
				}
			}
			return j;
		}

	}

	//Customer Management Class
	static class CustomerClass{
		
		public static JSONObject registerCustomer(String firstName, String lastName, String initials, String dob, String phoneNo,
				String gender, String address, String email, String password, String roleId, String companyName) {
			JSONObject j = new JSONObject();
			Connection con = null;
			CallableStatement stm;
			
			try {
				con = DBConnection.connect();
				stm = con.prepareCall(sqlObject.getCUSTOMER_REGISTRATION());
				String generatedSecuredPasswordHash = SCryptUtil.scrypt(password, 16, 16, 16);
				String code = RandomCode.GenCode(8);
				stm.setString(1, firstName);
				stm.setString(2, lastName);
				stm.setString(3, initials);
				stm.setString(4, dob);
				stm.setString(5, phoneNo);
				stm.setString(6, gender);
				stm.setString(7, address);
				stm.setString(8, email);
				stm.setString(9, generatedSecuredPasswordHash);
				stm.setLong(10, Long.parseLong(roleId));
				stm.setString(11, code);
				stm.setString(12, companyName);
				
				stm.registerOutParameter(13, Types.INTEGER);
				stm.execute();
				if (stm.getInt(13) == 1) {
					j.put("status", "success");
					SendMail.sendConfirmationCode(email, code, "Confirmation code for new account");
				} else {
					j.put("status", "error");
				}
				
			} catch (Exception e) {
				return j.put("status", e.getMessage());
			}

			return j;
		}
		
		public static JSONObject updateCustomerDetails(String companyName, String id) {
			JSONObject j = new JSONObject();

			Connection con = null;
			CallableStatement stm = null;

			try {

				con = DBConnection.connect();
				stm = con.prepareCall(sqlObject.getCUSTOMER_UPDATECUSTOMERDETAILS());
				stm.setString(1, companyName);
				stm.setLong(8, Long.parseLong(id));
				stm.registerOutParameter(9, Types.INTEGER);
				stm.execute();
				if (stm.getInt(9) == 1) {
					j.put("status", "success");
				} else {
					j.put("status", "error");
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (stm != null) {
					try {
						stm.close();
					} catch (Exception e) {
					}
				}
			}

			return j;
		}
		
		public static JSONArray getAllCustomers() {
			JSONArray j = null;

			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			ArrayList<Customer> customerList = new ArrayList<Customer>();

			try {
				con = DBConnection.connect();
				ps = con.prepareStatement(sqlObject.getCUSTOMER_GETALLCUSTOMERS());
				rs = ps.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						Customer c = new Customer(Long.parseLong(rs.getString(1)), rs.getString(2), rs.getString(3),
								rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7).toCharArray()[0],
								rs.getString(8));
						customerList.add(c);
					}
					j = new JSONArray(customerList);
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
		
		public static JSONObject getCustomerById(String id) {
			JSONObject j = new JSONObject();

			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;

			try {
				con = DBConnection.connect();
				ps = con.prepareStatement(sqlObject.getCUSTOMER_GETCUSTOMERBYID());
				ps.setLong(1, Long.parseLong(id));
				rs = ps.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						j.put("firstName", rs.getString(2));
						j.put("lastName", rs.getString(3));
						j.put("initials", rs.getString(4));
						j.put("dob", rs.getString(5));
						j.put("phoneNo", rs.getString(6));
						j.put("gender", rs.getString(7));
						j.put("address", rs.getString(8));
						j.put("companyName", rs.getString(10));
					}
				} else {
					j.put("status", "error");
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
	
		public static JSONObject deleteCustomer(String id) {
			JSONObject j = new JSONObject();

			Connection con = null;
			CallableStatement stm = null;

			try {

				con = DBConnection.connect();
				stm = con.prepareCall(sqlObject.getCUSTOMER_DELETECUSTOMER());
				stm.setLong(1, Long.parseLong(id));
				stm.registerOutParameter(2, Types.INTEGER);
				stm.execute();
				if (stm.getInt(2) == 1) {
					j.put("status", "success");
				} else {
					j.put("status", "error");
				}

			} catch (Exception e) {
				e.printStackTrace();
				j.put("status", e.getMessage());
			} finally {
				if (stm != null) {
					try {
						stm.close();
					} catch (Exception e) {
					}
				}
			}

			return j;
		}
	
	}
}
