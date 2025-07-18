package com.gurucharan.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.gurucharan.dao.TransactionHistoryDao;

@WebServlet("/history")
public class TransactionHistoryServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String upi = request.getParameter("upi");
		HttpSession session = request.getSession();
		String mobile = (String) session.getAttribute("mobile");
		
		TransactionHistoryDao thd = new TransactionHistoryDao();
		String status = thd.verify(upi, mobile);
		
		session.setAttribute("status4", status);
		response.sendRedirect("transactionHistory.jsp");
		
	}

}
