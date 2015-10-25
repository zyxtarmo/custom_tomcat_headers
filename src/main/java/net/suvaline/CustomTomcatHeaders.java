package net.suvaline;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class CustomTomcatHeaders implements Filter {

	HashMap<String, String> xHeaders = new HashMap<String, String>();
	
	public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws IOException, ServletException {
 
		HttpServletResponse resp = (HttpServletResponse) res;
		for (String key : xHeaders.keySet()) {
        		resp.addHeader(key, xHeaders.get(key));
		}
        	chain.doFilter(req, res);
    }
	
    public void init(FilterConfig config) throws ServletException {
       	
		String parVal;
		
		System.out.println("Custom Tomcat headers: init");
		
		for (Enumeration<String> e = config.getInitParameterNames() ; e.hasMoreElements() ;) {
			
			String parName = e.nextElement().toString();
			try {
				parVal = config.getInitParameter(parName);
				
				if ( parVal != null ) {

					xHeaders.put(parName, parVal);
				}
			} catch (Exception w) {
				w.printStackTrace();
				System.out.println("getInitParameter error with name: " + parName);
			}
		}
		
		for (String key : xHeaders.keySet()) {
			
				System.out.println("Add x-header: " + key + " value: " + xHeaders.get(key));
		}

		parVal = null;
    }
    
    public void destroy() {
    		// none
    }

}
