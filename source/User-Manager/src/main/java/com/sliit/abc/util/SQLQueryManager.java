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

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// Role Management
	private final String INSERT_ROLE = "INSERT INTO usr_Role(id, roleName, roleDesc, isActive) VALUES(0, ?, ?, 1)";
	private final String UPDATE_ROLE = "UPDATE usr_Role SET roleName = ?, roleDesc = ?, isActive = ? WHERE id = ?";
	private final String DELETE_ROLE = "DELETE FROM usr_Role WHERE id = ?";
	private final String ROLE_LIST = "SELECT * FROM usr_Role";
	private final String ROLE_NAME = "SELECT roleName FROM usr_Role WHERE id = ?";

	public String getINSERT_ROLE() {
		return INSERT_ROLE;
	}

	public String getUPDATE_ROLE() {
		return UPDATE_ROLE;
	}

	public String getDELETE_ROLE() {
		return DELETE_ROLE;
	}

	public String getROLE_LIST() {
		return ROLE_LIST;
	}

	public String getROLE_NAME() {
		return ROLE_NAME;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// Login Management
	private final String LOGIN_VERIFY = "SELECT l.userId, l.roleId, u.id, u.fname, l.password, l.isConfirmed, l.confirmationCode, l.loginCount FROM usr_Login l, usr_User u WHERE l.email = ? and l.userId = u.id";
	private final String INCREASE_LOGIN_COUNT = "CALL login_increaseLoginCount(?, ?)";
	private final String CHANGE_PASSWORD = "CALL user_changePassword(?, ?, ?)";
	private final String VERIFY_PASSWORD = "SELECT password FROM usr_Login WHERE email = ?";
	private final String ID_FROM_EMAIL = "SELECT userId FROM usr_Login WHERE email = ?";

	public String getLOGIN_VERIFY() {
		return LOGIN_VERIFY;
	}

	public String getCHANGE_PASSWORD() {
		return CHANGE_PASSWORD;
	}

	public String getVERIFY_PASSWORD() {
		return VERIFY_PASSWORD;
	}

	public String getID_FROM_EMAIL() {
		return ID_FROM_EMAIL;
	}

	public String getINCREASE_LOGIN_COUNT() {
		return INCREASE_LOGIN_COUNT;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// User Management
	private final String EMAIL_CONFIRMATION = "CALL user_confirmEmailConfirmation(?, ?, ?)";
	private final String REQUEST_CODE = "CALL user_requestEmailConfirmationCode(?, ?, ?);";
	private final String CONFIRM_REQUEST_CODE = "SELECT * FROM usr_Login WHERE email = ? and confirmationCode = ?";
	private final String UPDATE_USER_DETAILS = "CALL user_updateUserDetails(?, ?, ?, ?, ?, ?, ?, ?, ?);";
	private final String USER_CHECKEMAILAVAILABILITY = "SELECT userId FROM usr_Login l WHERE l.email = ?";
	private final String USER_ISACTIVE = "SELECT isActive FROM usr_User WHERE id = ?";

	public String getEMAIL_CONFIRMATION() {
		return EMAIL_CONFIRMATION;
	}

	public String getREQUEST_CODE() {
		return REQUEST_CODE;
	}

	public String getCONFIRM_REQUEST_CODE() {
		return CONFIRM_REQUEST_CODE;
	}

	public String getUPDATE_USER_DETAILS() {
		return UPDATE_USER_DETAILS;
	}

	public String getUSER_CHECKEMAILAVAILABILITY() {
		return USER_CHECKEMAILAVAILABILITY;
	}

	public String getUSER_ISACTIVE() {
		return USER_ISACTIVE;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//Customer Management
	private final String CUSTOMER_REGISTRATION = "CALL customer_Registration(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private final String CUSTOMER_UPDATECUSTOMERDETAILS = "CALL customer_updateCustomerDetails(?, ?, ?)";
	private final String CUSTOMER_DELETECUSTOMER = "CALL customer_deleteCustomer(?, ?)";
	private final String CUSTOMER_GETALLCUSTOMERS = "SELECT * FROM usr_User uu, usr_dtl_Customer udc";
	private final String CUSTOMER_GETCUSTOMERBYID = "SELECT * FROM usr_User uu, usr_dtl_Customer udc WHERE udc.id = usr_User and usr_User = ?";

	public String getCUSTOMER_REGISTRATION() {
		return CUSTOMER_REGISTRATION;
	}

	public String getCUSTOMER_UPDATECUSTOMERDETAILS() {
		return CUSTOMER_UPDATECUSTOMERDETAILS;
	}

	public String getCUSTOMER_DELETECUSTOMER() {
		return CUSTOMER_DELETECUSTOMER;
	}

	public String getCUSTOMER_GETALLCUSTOMERS() {
		return CUSTOMER_GETALLCUSTOMERS;
	}

	public String getCUSTOMER_GETCUSTOMERBYID() {
		return CUSTOMER_GETCUSTOMERBYID;
	}
	
}
