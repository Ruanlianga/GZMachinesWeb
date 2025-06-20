package com.bonus.core.jwt;

import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

import com.bonus.core.StringHelper;

import io.jsonwebtoken.Claims;

import java.util.Date;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
  
public class JwtFilter extends BasicHttpAuthenticationFilter {  
	
	
  	@Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object mappedValue) {
  		
  		HttpServletResponse response = (HttpServletResponse) servletResponse;
  		response.setContentType("application/pdf");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Authorization");
        response.setHeader("Access-Control-Max-Age", "0");
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        // 自定义JWT验证逻辑
        String token = httpRequest.getHeader("Authorization");  
        
        if (StringHelper.isNotEmpty(token) && token.startsWith("Bearer ")) {  
        	 token = token.substring(7); // 去掉"Bearer "  
        	 
             JwtUtil jwtUtil = new JwtUtil();
             Claims claims = jwtUtil.parseToken(token);  
             if (claims == null || claims.getExpiration().before(new Date())) {  
                 throw new UnauthorizedException("Token已过期或无效");  
             }else{
            	return true;
             }
        }else{
        	return false;
        }
        // 如果验证通过，返回true；否则返回false
    }  
  
    @Override  
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {  
        // 验证失败处理逻辑  
        // ...  
        // 你可以在这里设置响应状态码、响应体等  
        return false;  
    }  
  
}
