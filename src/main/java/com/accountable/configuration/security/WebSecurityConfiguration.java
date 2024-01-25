package com.accountable.configuration.security;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@RequiredArgsConstructor
@Configuration
public class WebSecurityConfiguration {

    @Value("#{'${com.accountable.cors.allowedOrigins}'.split(',')}")
    private List<String> allowOrigins;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        // https://stackoverflow.com/a/74688849
        http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth
                    -> auth
                        .anyRequest()
                        .permitAll());
//            .oauth2ResourceServer(oauth2
//                    -> oauth2
//                        .jwt(jwt -> jwt.jwtAuthenticationConverter(this::convertJwtToEnhancedAuthentication)));
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @SneakyThrows
    private AbstractAuthenticationToken convertJwtToEnhancedAuthentication(Jwt jwt) {
        UUID userUuid = UUID.fromString(jwt.getSubject());
        return null;
    }

    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter() {
        return new JWTAuthenticationFilter();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(allowOrigins);
        configuration.setAllowedMethods(
                Arrays.asList("GET", "POST", "DELETE", "OPTIONS", "PUT", "HEAD", "PATCH"));
        configuration.setAllowedHeaders(
                Arrays.asList(
                        "Authorization",
                        "Cache-Control",
                        "Content-Type",
                        "Accept",
                        "Access-Control-Allow-Origin"));
        configuration.addExposedHeader("Access-Control-Allow-Origin");
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}

