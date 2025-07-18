package com.gurucharan.dao;

import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;
import com.gurucharan.connect.Connect;

public class TransferDao {
	
	Connect cn;
	private String accountnumber1;
	private String accountnumber2;
	final String firstPartOfAccountNumber = "231004";
	private String selectBalanceQuery = "SELECT balance, upi_pin FROM balances WHERE account_number = ?";
	private String updateBalanceQuery = "UPDATE balances SET balance = ? WHERE account_number = ?";
	private String insertHistoryQuery = "INSERT INTO histories (account_number, description, credit, debit)"
			+ "VALUES (?, ?, ?, ?)";
	
	public TransferDao() {
		cn = new Connect();
	}
	
	public String transfer(String mobile1, String mobile2, String upiPin, String amount) {
		accountnumber1 = firstPartOfAccountNumber+mobile1;
		accountnumber2 = firstPartOfAccountNumber+mobile2;
		double amt = Double.parseDouble(amount);
		double bal1 = -1, bal2 = -1;
		try {
			cn.pst = cn.conn.prepareStatement(selectBalanceQuery);
			cn.pst.setString(1, accountnumber2);
			
			cn.rs = cn.pst.executeQuery();
			
			if(cn.rs.next())
				bal2 = cn.rs.getDouble("balance");
			else 
				return "no user";
			
			cn.pst = cn.conn.prepareStatement(selectBalanceQuery);
			cn.pst.setString(1, accountnumber1);
			
			cn.rs = cn.pst.executeQuery();
			
			if(cn.rs.next())
				bal1 = cn.rs.getDouble("balance");
			
			boolean matched = BCrypt.checkpw(upiPin, cn.rs.getString("upi_pin"));
			
			if(matched && bal1>=amt) {
				cn.pst = cn.conn.prepareStatement(updateBalanceQuery);
				cn.pst.setDouble(1, bal1-amt);
				cn.pst.setString(2, accountnumber1);
				
				int r = cn.pst.executeUpdate();
				
				cn.pst = cn.conn.prepareStatement(updateBalanceQuery);
				cn.pst.setDouble(1, bal2+amt);
				cn.pst.setString(2, accountnumber2);
				
				r = cn.pst.executeUpdate();
				
				cn.pst = cn.conn.prepareStatement(insertHistoryQuery);
				cn.pst.setString(1, accountnumber1);
				cn.pst.setString(2, "Money Sent To " + accountnumber2);
				cn.pst.setNull(3, java.sql.Types.DOUBLE);
				cn.pst.setDouble(4,  amt);
				
				r = cn.pst.executeUpdate();
				
				cn.pst = cn.conn.prepareStatement(insertHistoryQuery);
				cn.pst.setString(1, accountnumber2);
				cn.pst.setString(2, "Money Recieved From " + accountnumber1);
				cn.pst.setDouble(3,  amt);
				cn.pst.setNull(4, java.sql.Types.DOUBLE);
				
				r = cn.pst.executeUpdate();
			}
			else if(!matched)
				return "wrong upi";
			else
				return "less balance";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "success";
		
	}

}
