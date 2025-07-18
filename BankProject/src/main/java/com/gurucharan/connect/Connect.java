package com.gurucharan.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {
	String url = "jdbc:postgresql://localhost:5432/bankDB";
	String username = "postgres";
	String password = "123456789";
	public Connection conn = null;
	public PreparedStatement pst = null;
	public ResultSet rs = null;
	
	private String createCustomerQuery = "CREATE TABLE IF NOT EXISTS accounts ("
	        + "account_number VARCHAR(16) PRIMARY KEY, "
	        + "ifsc_code VARCHAR(10) NOT NULL, "
	        + "name TEXT NOT NULL, "
	        + "email TEXT NOT NULL, "
	        + "mobile VARCHAR(10) NOT NULL UNIQUE, "
	        + "account_type TEXT NOT NULL, "
	        + "mpin VARCHAR NOT NULL"
	        + ")";
	
	private String createBalanceQuery = "CREATE TABLE IF NOT EXISTS balances ("
			+ "account_number VARCHAR(16) PRIMARY KEY, "
			+ "upi_pin VARCHAR NOT NULL, "
			+ "balance DOUBLE PRECISION NOT NULL"
			+ ")";
	
	private String createHistoryQuery = "CREATE TABLE IF NOT EXISTS histories ("
		    + "account_number VARCHAR(16), "
		    + "description TEXT NOT NULL, "
		    + "credit DOUBLE PRECISION, "
		    + "debit DOUBLE PRECISION, "
		    + "timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
		    + ")";

	public Connect() {
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url,username,password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			Statement st = conn.createStatement();
			st.executeUpdate(createCustomerQuery);
			st.executeUpdate(createBalanceQuery);
			st.executeUpdate(createHistoryQuery);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
