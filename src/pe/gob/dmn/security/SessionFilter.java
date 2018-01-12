package pe.gob.dmn.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet Filter implementation class SessionFilter
 */
public class SessionFilter implements Filter {

	private static final Logger logger = Logger.getLogger( SessionFilter.class );
	
	private ArrayList<String> urlList;
	
    /**
     * Default constructor. 
     */
    public SessionFilter() {
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
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		 HttpServletRequest request = (HttpServletRequest) req;
	        HttpServletResponse response = (HttpServletResponse) res;
	        String url = request.getServletPath();
	        boolean allowedRequest = false;
	         
	       // logger.debug( "URL solicitada = "+url );
	        
	        if(urlList.contains(url)) {
	        	//logger.debug( "URL permitida sin usrLogin = "+url );
	            allowedRequest = true;
	        }
	             
	        if (!allowedRequest) {
	        	//logger.debug("==URL RESTRINGIDA==");
	        	if( request.getSession().getAttribute("usrLogin") == null){
	        		logger.debug("==NO HAY USUARIO, MATO SESSION== " + url);
	        		request.getSession(false);
	        		//logger.debug("==REDIRECT.JSP==");
	 	            response.sendRedirect("redirect.jsp");
	 	            return;
	        	}
	        }
	        chain.doFilter(req, res);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		 String urls = fConfig.getInitParameter("avoid-urls");
        StringTokenizer token = new StringTokenizer(urls, ",");
 
        urlList = new ArrayList<String>();
 
        while (token.hasMoreTokens()) {
            urlList.add(token.nextToken());
 
        }
	}

}
