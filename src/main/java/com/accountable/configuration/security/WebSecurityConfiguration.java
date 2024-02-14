package com.accountable.configuration.security;

import com.accountable.repository.UserRepository;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfiguration {

  @Value("#{'${com.accountable.cors.allowedOrigins}'.split(',')}")
  private List<String> allowOrigins;

  private final UserRepository userRepo;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    // https://stackoverflow.com/a/74688849
    http.cors(Customizer.withDefaults())
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(authorizeRequests -> authorizeRequests.anyRequest().permitAll())
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    return http.build();
  }

  // TODO: Uncomment this to add the filter
  //  @Bean
  //  public JWTAuthenticationFilter jwtAuthenticationFilter() {
  //    return new JWTAuthenticationFilter(userRepo);
  //  }

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
