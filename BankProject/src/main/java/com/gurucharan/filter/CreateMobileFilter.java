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
public class CreateMobileFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String mobile = req.getParameter("mobile");
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		
		if(mobile.length() == 10) {
			chain.doFilter(request, response);
		}
		else {
			session.setAttribute("error1", "invalidMobile");
			res.sendRedirect("create.jsp");
		}
	}

}
