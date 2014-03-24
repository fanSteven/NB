package org.nb.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class MyFilter
 */
@WebFilter("/EnCodeFilter")
public class EnCodeFilter implements Filter {

	String encodename = "utf-8";

	public EnCodeFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
//		HttpServletRequest httprequest = (HttpServletRequest)request;
//		HttpServletResponse httpreponse = (HttpServletResponse)response;
		request.setCharacterEncoding(this.encodename);
		response.setCharacterEncoding("UTF-8");
		//httpreponse.addHeader("Access-Control-Allow-Origin", "*");
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		String initParameter = fConfig.getInitParameter("EncodeCoding");
		if (initParameter != null)
			this.encodename = initParameter;
	}

}
