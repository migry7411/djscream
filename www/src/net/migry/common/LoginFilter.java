package net.migry.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.migry.dto.Member;

public class LoginFilter implements Filter {
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		if(req instanceof HttpServletRequest) {
			HttpServletRequest request = (HttpServletRequest) req;
			HttpSession session = request.getSession();
			String url = request.getRequestURI();
			String root = request.getContextPath();
			url = url.substring(root.length());
			Member loginMember = (Member)(session.getAttribute("login"));
			
			//System.out.println(url);
			
			if (url.startsWith("/admin")) {
				if (loginMember == null || !loginMember.getUserid().equals("administrator")) {
					String p = "/index.jsp";
					RequestDispatcher rd = req.getRequestDispatcher(p);
					rd.forward(req, res);
					return;
				}
			}
			
			chain.doFilter(req, res);
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		
	}
}
