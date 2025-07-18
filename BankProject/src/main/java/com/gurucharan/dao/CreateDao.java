package com.gurucharan.dao;

import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;
import com.gurucharan.connect.Connect;

public class CreateDao {
	Connect cn = null;
	private String insertCustomerQuery = "INSERT INTO accounts (account_number, ifsc_code, name, email, mobile, account_type, mpin) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?)";
	private String insertBalanceQuery = "INSERT INTO balances (account_number, upi_pin, balance) "
			+ "VALUES (?, ?, ?)";
	private String insertHistoryQuery = "INSERT INTO histories (account_number, description, credit, debit)"
			+ "VALUES (?, ?, ?, ?)";
	private String selectCustomerQuery = "SELECT name FROM accounts WHERE mobile=?";
	private String accountnumber;
	final String firstPartOfAccountNumber = "231004";
	private String ifsc = "IFSC231004";
	
	public CreateDao() {
		cn = new Connect();
	}
	
	public String createAccount(String name, String mobile, String email, String acctype, String mpin, double balance, String upiPin) {
		boolean verification = verify(mobile);
		
		if(verification) {
			int rows1 = 0, rows2 = 0, rows3 = 0;
			
			accountnumber = firstPartOfAccountNumber + mobile;
			String hashedMpin = BCrypt.hashpw(mpin, BCrypt.gensalt());
			String hashedUpiPin = BCrypt.hashpw(upiPin, BCrypt.gensalt());
			
			try {
				cn.pst = cn.conn.prepareStatement(insertCustomerQuery);
				cn.pst.setString(1, accountnumber);
				cn.pst.setString(2, ifsc);
				cn.pst.setString(3, name);
				cn.pst.setString(4, email);
				cn.pst.setString(5, mobile);
				cn.pst.setString(6, acctype);
				cn.pst.setString(7, hashedMpin);			
				rows1 = cn.pst.executeUpdate();
				
				cn.pst = cn.conn.prepareStatement(insertBalanceQuery);
				cn.pst.setString(1, accountnumber);
				cn.pst.setString(2, hashedUpiPin);
				cn.pst.setDouble(3, balance);
				
				rows2 = cn.pst.executeUpdate();
				
				cn.pst = cn.conn.prepareStatement(insertHistoryQuery);
				cn.pst.setString(1, accountnumber);
				cn.pst.setString(2, "Primary Deposit");
				cn.pst.setDouble(3, balance);
				cn.pst.setNull(4, java.sql.Types.DOUBLE);
				
				rows3 = cn.pst.executeUpdate();
				
				cn.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return (rows1 > 0 && rows2 > 0 && rows3 > 0) ? "True" : "False";
		}
		else 
			return "exist";
	}
	
	public boolean verify(String mob) {
		try {
			cn.pst = cn.conn.prepareStatement(selectCustomerQuery);
			cn.pst.setString(1, mob);
			
			cn.rs = cn.pst.executeQuery();
			
			if(cn.rs.next())
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}

}
