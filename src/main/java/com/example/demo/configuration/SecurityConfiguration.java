package com.example.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.security.JwtAuthenticationFilter;
import com.example.demo.service.AccountService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
				.cors(org.springframework.security.config.Customizer.withDefaults())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth.requestMatchers("/api/auth/**").permitAll()
						.requestMatchers("/api/seller/create").permitAll().requestMatchers("/api/seller/**").permitAll()
						.requestMatchers("/api/seller/profile/**").permitAll().requestMatchers("/api/seller/review/**")
						.permitAll().requestMatchers("/api/account/all/**").permitAll()
						.requestMatchers("/api/account/ad/**").permitAll().requestMatchers("/api/shipper/create")
						.permitAll().requestMatchers("/api/shipper/find-all").permitAll()
						.requestMatchers("/api/shipper/profile/**").permitAll()
						.requestMatchers("/api/shipper/review/**").permitAll()
						.requestMatchers("/api/notification/all/**").permitAll()
						.requestMatchers("/api/notification/create").permitAll().requestMatchers("/api/product/all/**")
						.permitAll().requestMatchers("/api/promotion/all/**").permitAll()
						.requestMatchers("/api/category/all/**").permitAll().requestMatchers("/api/product/**")
						.permitAll().requestMatchers("/api/product/review/**").permitAll()
						.requestMatchers("/api/product/ad/**").permitAll().requestMatchers("/api/product/as/**")
						.permitAll().requestMatchers("/api/category/ad/**").permitAll()
						.requestMatchers("/api/promotion/ad/**").permitAll().requestMatchers("/api/promotion/au/**")
						.permitAll().requestMatchers("/api/promotion/user/**").permitAll()
						.requestMatchers("/api/promotion-product/ad/**").permitAll()
						.requestMatchers("/api/promotion-category/ad/**").permitAll()
						.requestMatchers("/api/promotion-account/ad/**").permitAll()
						.requestMatchers("/api/promotion-condition/ad/**").permitAll()
						.requestMatchers("/api/product-images/**").permitAll().requestMatchers("/api/cart/**")
						.permitAll().requestMatchers("/api/order/**").permitAll()
						.requestMatchers("/api/payment/paypal/**").permitAll()
						.requestMatchers("/ws/**").permitAll()

						.anyRequest().authenticated())
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public org.springframework.web.cors.CorsConfigurationSource corsConfigurationSource() {
		org.springframework.web.cors.CorsConfiguration configuration = new org.springframework.web.cors.CorsConfiguration();
		configuration.setAllowedOrigins(java.util.List.of("http://localhost:9595"));
		configuration.setAllowedMethods(java.util.List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		configuration.setAllowedHeaders(java.util.List.of("Authorization", "Content-Type", "x-auth-token"));
		configuration.setExposedHeaders(java.util.List.of("x-auth-token"));
		configuration.setAllowCredentials(true);
		org.springframework.web.cors.UrlBasedCorsConfigurationSource source = new org.springframework.web.cors.UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}
