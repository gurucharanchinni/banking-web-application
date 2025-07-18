package com.gurucharan.dao;

import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;
import com.gurucharan.connect.Connect;

public class TransactionHistoryDao {
	
	Connect cn;
	final String firstPartOfAccountNumber = "231004";
	private String accountnumber;
	private String selectBalanceQuery = "SELECT upi_pin FROM balances WHERE account_number=?";
	
	public TransactionHistoryDao() {
		cn = new Connect();
	}
	
	public String verify(String upiPin, String mobile) {
		accountnumber = firstPartOfAccountNumber+mobile;
		try {
			cn.pst = cn.conn.prepareStatement(selectBalanceQuery);
			cn.pst.setString(1, accountnumber);
			
			cn.rs = cn.pst.executeQuery();
			if(cn.rs.next())
				if(BCrypt.checkpw(upiPin, cn.rs.getString("upi_pin")))
					return "right"; 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "wrong";
	}

}
