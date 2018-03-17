package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pojo.UserPojo;

/**
 * Servlet Filter implementation class IsManageFilter
 */
@WebFilter("/IsManageFilter")
public class IsLoginFilter implements Filter {
    /**
     * Default constructor. 
     */
    public IsLoginFilter() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest rs=(HttpServletRequest)request;
		HttpServletResponse rp=(HttpServletResponse)response;
		HttpSession session=rs.getSession();	
		UserPojo newuser=(UserPojo)session.getAttribute("user");
		if(newuser!=null&&newuser.getState()==1)
			chain.doFilter(request, response);
		else
			rp.sendRedirect("/HomeworkUpload/index.jsp");
		
		// pass the request along the filter chain
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
