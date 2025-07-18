package com.gurucharan.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gurucharan.dao.TransferDao;

@WebServlet("/transfer")
public class TransferServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String mobile1 = (String) session.getAttribute("mobile");
		String mobile2 = request.getParameter("mobile");
		String upi = request.getParameter("upi");
		String amount = request.getParameter("amount");
		
		TransferDao td = new TransferDao();
		
		String status = td.transfer(mobile1, mobile2, upi, amount);
		
		session.setAttribute("status3", status);
		response.sendRedirect("transfer.jsp");
	}

}
