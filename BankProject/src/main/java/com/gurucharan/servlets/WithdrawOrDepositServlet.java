package com.gurucharan.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gurucharan.dao.WithdrawOrDepositDao;

@WebServlet("/withdrawOrDeposit")
public class WithdrawOrDepositServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String upi = request.getParameter("upi");
		String option = request.getParameter("option");
		String amount = request.getParameter("amount");
		HttpSession session = request.getSession();
		String mobile = (String) session.getAttribute("mobile");
		
		WithdrawOrDepositDao wodd = new WithdrawOrDepositDao(upi, mobile, amount);
		String flag;
		if("Deposit".equals(option))
			flag = wodd.deposit();
		else
			flag = wodd.withdraw();
		
		if("False".equals(flag))
			session.setAttribute("status2", "invalidUpi");
		else if("True".equals(flag))
			session.setAttribute("status2", option);
		else
			session.setAttribute("status2", "lessBalance");
		response.sendRedirect("withdrawOrDeposit.jsp");
	}
}
