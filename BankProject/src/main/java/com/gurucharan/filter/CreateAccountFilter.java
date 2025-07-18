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

@WebFilter("/create")
public class CreateAccountFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		String acctype = req.getParameter("acctype");
		HttpServletResponse res = (HttpServletResponse)response;			
		HttpSession session = req.getSession();
		
		if(acctype.length() != 0) {
			chain.doFilter(request, response);
		}
		else {
			session.setAttribute("error2", "invalidAccount");
			res.sendRedirect("create.jsp");
		}
	}

}
