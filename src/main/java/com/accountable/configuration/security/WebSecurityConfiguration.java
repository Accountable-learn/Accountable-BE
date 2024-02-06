package com.accountable.configuration.security;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Configuration
public class WebSecurityConfiguration {

  @Value("#{'${com.accountable.cors.allowedOrigins}'.split(',')}")
  private List<String> allowOrigins;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    // https://stackoverflow.com/a/74688849
    http.cors(Customizer.withDefaults())
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated());
    http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    return http.build();
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

  /****
   * In the context of Spring Security, which you've enabled with @EnableWebSecurity,
   * the CORS configuration is automatically integrated into the security filter chain.
   * This integration means that when the Spring Security filter chain is processing requests,
   * it will consult the CorsConfigurationSource bean (provided by your corsConfigurationSource method)
   * to determine how to handle CORS preflight requests and actual requests from allowed origins.
   *
   * You don't need to explicitly tell Spring Security to use this source; it will automatically detect and
   * use it as part of its CORS processing because of the @Bean annotation.
   * This seamless integration simplifies enabling and configuring CORS in a Spring Security-enabled application.
   *
   * **/
}
