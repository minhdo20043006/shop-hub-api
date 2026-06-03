package com.example.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic", "/queue");
        config.setApplicationDestinationPrefixes("/app");
        config.setUserDestinationPrefix("/user");
    }

    @Autowired
    private com.example.demo.security.JwtUtil jwtUtil;

    @Autowired
    private org.springframework.security.core.userdetails.UserDetailsService userDetailsService;

    @Override
    public void configureClientInboundChannel(
            org.springframework.messaging.simp.config.ChannelRegistration registration) {
        registration.interceptors(new org.springframework.messaging.support.ChannelInterceptor() {
            @Override
            public org.springframework.messaging.Message<?> preSend(org.springframework.messaging.Message<?> message,
                    org.springframework.messaging.MessageChannel channel) {
                org.springframework.messaging.simp.stomp.StompHeaderAccessor accessor = org.springframework.messaging.support.MessageHeaderAccessor
                        .getAccessor(message, org.springframework.messaging.simp.stomp.StompHeaderAccessor.class);

                if (org.springframework.messaging.simp.stomp.StompCommand.CONNECT.equals(accessor.getCommand())) {
                    String authorizationHeader = accessor.getFirstNativeHeader("Authorization");
                    System.out.println(">>> WS_CONFIG: CONNECT command received. Auth Header: "
                            + (authorizationHeader != null ? "Present" : "MISSING"));
                    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                        String token = authorizationHeader.substring(7);
                        try {
                            String username = jwtUtil.extractUsername(token);
                            System.out.println(">>> WS_CONFIG: Token extracted for: " + username);

                            if (username != null && org.springframework.security.core.context.SecurityContextHolder
                                    .getContext().getAuthentication() == null) {
                                org.springframework.security.core.userdetails.UserDetails userDetails = userDetailsService
                                        .loadUserByUsername(username);
                                if (jwtUtil.validateToken(token, userDetails)) {
                                    org.springframework.security.authentication.UsernamePasswordAuthenticationToken authToken = new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
                                            userDetails, null, userDetails.getAuthorities());
                                    accessor.setUser(authToken);
                                    System.out.println(">>> WS_CONFIG: USER AUTHENTICATED SUCCESSFULLY");
                                } else {
                                    System.out.println(">>> WS_CONFIG: Token validation FAILED");
                                }
                            }
                        } catch (Exception e) {
                            System.out.println(">>> WS_CONFIG: Error during authentication: " + e.getMessage());
                        }
                    } else {
                        System.out.println(">>> WS_CONFIG: No valid Bearer token found");
                    }
                }
                return message;
            }
        });
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("http://localhost:9595")
                .withSockJS();
    }
}
