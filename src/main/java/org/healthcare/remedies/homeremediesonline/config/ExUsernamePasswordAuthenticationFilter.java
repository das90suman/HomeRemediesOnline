package org.healthcare.remedies.homeremediesonline.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.microsoft.aad.adal4j.AuthenticationException;


public class ExUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	 AuthenticationManager authenticationManager;
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    	System.out.println("request"+request);
    	final String captcha = request.getParameter("captcha");
        System.out.println("captcha in ExUsernamePasswordAuthenticationFilter"+captcha);
        request.getSession().setAttribute("captcha", captcha);
        
        return super.attemptAuthentication(request, response); 
    	
    	
    } 
    public ExUsernamePasswordAuthenticationFilter() {
    	new ExUsernamePasswordAuthenticationFilter(authenticationManager);
        this.setFilterProcessesUrl("/j_spring_security_check");
    }
    public ExUsernamePasswordAuthenticationFilter( AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
        super.setAuthenticationManager(authenticationManager);
     }
    
 
  
}