package com.gurucharan.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebFilter("/history")
public class THUpiFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req= (HttpServletRequest)request;
		String upi = request.getParameter("upi");
		HttpSession session = req.getSession();
		HttpServletResponse res = (HttpServletResponse)response;
		if(upi.length() == 4 && !upi.matches(".*[a-zA-Z].*")) {
			chain.doFilter(request, response);
		}
		else {
			session.setAttribute("error13", "invalidUpi");
			res.sendRedirect("transactionHistory.jsp");
		}
	}
}
