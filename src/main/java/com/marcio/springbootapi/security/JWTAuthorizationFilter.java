package com.marcio.springbootapi.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private UserDetailsService userDetailService;
	private JWTUtil jwtUtil;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtutil,
			UserDetailsService userdetailService) {
		super(authenticationManager);
		this.jwtUtil = jwtutil;
		this.userDetailService = userdetailService;
	}

	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		String header = req.getHeader("Authorization");
		if (header != null & header.startsWith("Bearer ")) {
			UsernamePasswordAuthenticationToken auth = getAuthentication( header.substring(7));
			if (auth != null) {
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}
		chain.doFilter(req, res);
	}

	private UsernamePasswordAuthenticationToken getAuthentication( String token) {
		if(jwtUtil.validToken(token)) {
			String username = jwtUtil.getUserName(token);
			UserDetails user = userDetailService.loadUserByUsername(username);
			return new UsernamePasswordAuthenticationToken(user,null, user.getAuthorities());
		}
		return null;
	}

}
