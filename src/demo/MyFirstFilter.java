package demo;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyFirstFilter extends HttpFilter {
	private FilterConfig config;
	
	public void destory() {

	}
	
	public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain) 
	throws IOException,ServletException{
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse res=(HttpServletResponse)response;
		System.out.println(req.getRequestURI());
		chain.doFilter(req, res);
	}
	
	 public void init(FilterConfig filterConfig) throws ServletException {
	        config = filterConfig;
	 }
}
