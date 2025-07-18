package com.gurucharan.dao;

import com.gurucharan.connect.Connect;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

public class LoginDao {
	private String selectCustomerQuery = "SELECT name, mpin FROM accounts WHERE mobile = ?";
	
	Connect cn;
	public LoginDao() {
		cn = new Connect();
	}
	
	public String verifyAccount(String mobile, String mpin) {
		boolean matched=false;
		String finalstatus="";
		try {
			cn.pst = cn.conn.prepareStatement(selectCustomerQuery);
			cn.pst.setString(1, mobile);
			
			cn.rs = cn.pst.executeQuery();
			if(cn.rs.next()) {
				matched = BCrypt.checkpw(mpin, cn.rs.getString("mpin"));
			}
			else {
				finalstatus="No Account with " + mobile;
			}
			
			if(matched)
				finalstatus=cn.rs.getString("name");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return finalstatus;
	}

}
