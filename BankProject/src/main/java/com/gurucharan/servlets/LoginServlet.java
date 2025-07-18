package com.gurucharan.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gurucharan.dao.LoginDao;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mobile = request.getParameter("mobile");
		String mpin = request.getParameter("mpin");
		HttpSession session = request.getSession();
		
		LoginDao ld = new LoginDao();
		String name = ld.verifyAccount(mobile, mpin);
		String accountVerify = "No Account with " + mobile;
		
		if(!"".equals(name) && !accountVerify.equals(name)) {
			session.setAttribute("name", name);
			session.setAttribute("mobile", mobile);
			response.sendRedirect("dashboard.jsp");	
		}
		else if(!"".equals(name)) {
			session.setAttribute("error7", "No Account");
			response.sendRedirect("create.jsp");
		}
		else {
			session.setAttribute("error8", "invalidMPin");
			response.sendRedirect("login.jsp");
		}
	}

}
