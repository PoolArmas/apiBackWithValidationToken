package com.spring.security;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Class InterceptorToken and Validation Date
 * 
 * @author 56949
 *
 */
public class JWTAuthorizationFilter extends OncePerRequestFilter {

	private static final Logger LOGGER = LogManager.getLogger(JWTAuthorizationFilter.class.getName());
	private static final String HEADER = "Authorization";
	private static final String PREFIX = "Bearer ";

	/**
	 *
	 * @param request - obj
	 * @param response - obj
	 * @param chain - obj
	 * @throws ServletException Error
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		if (existJWTToken(request, response)) {
			if(validateToken(request)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
						new UsernamePasswordAuthenticationToken("", null, null);
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				// After setting the Authentication in the context, we specify
				// that the current user is authenticated. So it passes the
				// Spring Security Configurations successfully.
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				LOGGER.info("Success Token");
			} else {
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				(response).sendError(HttpServletResponse.SC_FORBIDDEN);
				return;
			}
		} else {
			SecurityContextHolder.clearContext();
		}
		chain.doFilter(request, response);
	}

	/**
	 * Only Validation Date Token
	 * @ HttpServletRequest - request
	 * @ return Obj
	 */
	private boolean validateToken(HttpServletRequest request) {
		
		String jwtToken = request.getHeader(HEADER).replace(PREFIX, "");
		String[] chunks = jwtToken.split("\\.");
		String payload = new String(Base64.getUrlDecoder().decode(chunks[1]));
		
		 try {
			  if(LocalDate.now().toString().equals(new ObjectMapper()
					 .readValue(payload, Map.class).get("fechaToken"))) {
				  request.setAttribute("token", jwtToken);
				  return true;
			 }else {
				 return false;
			 }

		} catch (JsonProcessingException e) {
			LOGGER.error(e.toString());
		} 
		return false;		
	}

	/**
	 *
	 * @param request - obj
	 * @param res - obj
	 * @return boolean data
	 */
	private boolean existJWTToken(HttpServletRequest request, HttpServletResponse res) {
		String authenticationHeader = request.getHeader(HEADER);
		return (authenticationHeader == null || authenticationHeader.startsWith(PREFIX));
	}

}
