package com.gurucharan.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.gurucharan.dao.CreateDao;

@WebServlet("/create")
public class CreateServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("acchold");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String acctype = request.getParameter("acctype");
		String mpin = request.getParameter("mpin");
		String upi = request.getParameter("upi");
		HttpSession session = request.getSession();
		
		CreateDao cd = new CreateDao();
		String flag;
		if(acctype.equals("Savings"))
			flag = cd.createAccount(name, mobile, email, acctype, mpin, 1000.0, upi);
		else
			flag = cd.createAccount(name, mobile, email, acctype, mpin, 10000.0, upi);
		
		if("True".equals(flag)) {
			session.setAttribute("status1", "success");
			response.sendRedirect("login.jsp");
		}
		else {
			session.setAttribute("status1", "exist");
			response.sendRedirect("create.jsp");
		}
	}

}
