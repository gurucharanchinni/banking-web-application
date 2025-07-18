package com.gurucharan.dao;

import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;
import com.gurucharan.connect.Connect;

public class WithdrawOrDepositDao {
	
	boolean matched = false;
	double amt;
	double bal = 0;
	Connect cn;
	final String firstPartOfAccountNumber = "231004";
	private String accountnumber;
	private String selectBalanceQuery = "SELECT * FROM balances WHERE account_number = ?";
	private String updateBalanceQuery = "UPDATE balances SET balance = ? WHERE account_number = ?";
	private String insertHistoryQuery = "INSERT INTO histories (account_number, description, credit, debit)"
			+ "VALUES (?, ?, ?, ?)";
	
	public WithdrawOrDepositDao(String upiPin, String mobile, String amount) {
		cn = new Connect();
		amt = Double.parseDouble(amount);
		accountnumber = firstPartOfAccountNumber+mobile;
		try {
			cn.pst = cn.conn.prepareStatement(selectBalanceQuery);
			cn.pst.setString(1, accountnumber);
			
			cn.rs = cn.pst.executeQuery();
			
			if(cn.rs.next()) {
				bal = cn.rs.getDouble("balance");
				matched = BCrypt.checkpw(upiPin, cn.rs.getString("upi_pin"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String deposit() {
		String finalStatus = "False";
		try {
			if(matched) {
				cn.pst = cn.conn.prepareStatement(updateBalanceQuery);
				cn.pst.setDouble(1, bal+amt);
				cn.pst.setString(2, accountnumber);
				
				int r = cn.pst.executeUpdate();
				
				cn.pst = cn.conn.prepareStatement(insertHistoryQuery);
				cn.pst.setString(1, accountnumber);
				cn.pst.setString(2, "Money Deposited By You");
				cn.pst.setDouble(3,  amt);
				cn.pst.setNull(4, java.sql.Types.DOUBLE);
				
				r = cn.pst.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(matched)
			finalStatus = "True";
		return finalStatus;
		
	}
	
	public String withdraw() {
		String finalStatus = "";
		try {
			if(matched & bal>=amt) {
				cn.pst = cn.conn.prepareStatement(updateBalanceQuery);
				cn.pst.setDouble(1, bal-amt);
				cn.pst.setString(2, accountnumber);
				
				int r = cn.pst.executeUpdate();
				
				cn.pst = cn.conn.prepareStatement(insertHistoryQuery);
				cn.pst.setString(1, accountnumber);
				cn.pst.setString(2, "Money Withdrawn By You");
				cn.pst.setNull(3, java.sql.Types.DOUBLE);
				cn.pst.setDouble(4,  amt);
				
				r = cn.pst.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(matched & bal>=amt)
			finalStatus = "True";
		else if(!matched)
			finalStatus = "False";
		return finalStatus;
		
	}

}
