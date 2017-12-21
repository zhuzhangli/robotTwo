package org.xjtusicd3.portal.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {			
			
			HttpServletRequest request = (HttpServletRequest) servletRequest;
			HttpServletResponse response = (HttpServletResponse) servletResponse;
			
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			
			String url = request.getServletPath() + (request.getPathInfo() == null ? "" : request.getPathInfo());
			HttpSession session = request.getSession();
			Object object = session.getAttribute("nameOrEmail");
			
			//没有登录就不允许访问页面的的链接
			if (object==null&&(url.contains("index")||url.contains("eventPage")||url.contains("problemPage")||url.contains("configureSoftPage")||url.contains("configureEquipmentPage")||url.contains("configureBasicPage")||url.contains("changePage")||url.contains("faqPage")||url.contains("userPage")||url.contains("permissionPage")||url.contains("permissionAssignPage"))) {
				response.sendRedirect(request.getContextPath() + "/login.html");
				return;
			} 
			chain.doFilter(servletRequest, servletResponse);
			
	}

	public void init(FilterConfig arg0) throws ServletException {

	}
	

}
