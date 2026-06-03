package com.example.demo.security;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String path = request.getRequestURI();

		if (path.startsWith("/api/notification")) {
			filterChain.doFilter(request, response);
			return;
		}

		if (isPublicPath(path)) {
			filterChain.doFilter(request, response);
			return;
		}

		String authHeader = request.getHeader("Authorization");

		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}

		String token = authHeader.substring(7);

		try {
			Claims claims = jwtUtil.parseToken(token);

			String username = claims.getSubject();
			List<String> roles = claims.get("roles", List.class);
			if (roles == null)
				roles = List.of();

			var authorities = roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role)).toList();

			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null,
					authorities);

			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

			SecurityContextHolder.getContext().setAuthentication(authentication);

		} catch (Exception e) {
			SecurityContextHolder.clearContext();
			System.out.println("JWT invalid: " + e.getMessage());
		}

		filterChain.doFilter(request, response);
	}

	private boolean isPublicPath(String path) {
		return path.startsWith("/account") || path.startsWith("/auth") || path.startsWith("/login")
				|| path.startsWith("/register") || path.startsWith("/css") || path.startsWith("/js")
				|| path.startsWith("/images") || path.startsWith("/assets") || path.startsWith("/ws");
	}
}
