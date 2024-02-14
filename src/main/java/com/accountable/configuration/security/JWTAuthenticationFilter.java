package com.accountable.configuration.security;

import com.accountable.entity.User;
import com.accountable.repository.UserRepository;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.jwk.source.RemoteJWKSet;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

// https://stackoverflow.com/questions/48356287/is-there-any-java-example-of-verification-of-jwt-for-aws-cognito-api

@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {
  @Value("${com.accountable.aws.pubKey}")
  private String JWKS_URL;

  private final UserRepository userRepo;

  @Override
  @SneakyThrows
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String header = request.getHeader("Authorization");

    if (header != null && header.startsWith("Bearer ")) {
      String token = header.substring(7); // Extract the token
      try {
        JWKSource<SecurityContext> keySource = new RemoteJWKSet<>(new URL(JWKS_URL));
        ConfigurableJWTProcessor<SecurityContext> processor = new DefaultJWTProcessor<>();
        JWSVerificationKeySelector<SecurityContext> keySelector =
            new JWSVerificationKeySelector<>(JWSAlgorithm.RS256, keySource);
        processor.setJWSKeySelector(keySelector);

        SecurityContext ctx = null; // Context not needed here
        JWTClaimsSet claimsSet = processor.process(token, ctx);
        convertJwtToAuthToken(claimsSet);
        filterChain.doFilter(request, response);
      } catch (Exception e) {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
      }
    } else {
      response.sendError(
          HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: No Bearer token present");
    }
  }

  private void convertJwtToAuthToken(JWTClaimsSet claimsSet) throws ParseException {
    UUID userId = UUID.fromString(claimsSet.getSubject());
    User user = userRepo.findUserByUserIdAndIsActiveTrue(userId);
    Authentication auth = new UsernamePasswordAuthenticationToken(user, null);
    SecurityContextHolder.getContext().setAuthentication(auth);
  }
}
