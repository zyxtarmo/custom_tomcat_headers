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
       	
		String tmpVal;
		for (Enumeration<String> e = config.getInitParameterNames() ; e.hasMoreElements() ;) {
			
			tmpVal = config.getInitParameter(e.nextElement());
			if ( tmpVal != null ) {
				xHeaders.put(e.nextElement().toString(), tmpVal);
			}
		}
		
		for (String key : xHeaders.keySet()) {
			
				System.out.println("Add x-header: " + key + " value: " + xHeaders.get(key));
		}

		tmpVal = null;
    }
    
    public void destroy() {
    		// none
    }
     
}
