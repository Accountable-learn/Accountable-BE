package com.accountable.configuration.security;

import com.auth0.jwk.JwkException;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.JwkProviderBuilder;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.RSAKeyProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;


// https://stackoverflow.com/questions/48356287/is-there-any-java-example-of-verification-of-jwt-for-aws-cognito-api
@Configuration
public class AwsCognitoRSAKeyProvider implements RSAKeyProvider {

    private final URL aws_kid_store_url;
    private final JwkProvider provider;

    @Value("${com.accountable.aws.cognitoRegion}")
    private String aws_cognito_region;

    @Value("${com.accountable.aws.userpoolId}")
    private String aws_user_pools_id;

    public AwsCognitoRSAKeyProvider() {
        // Json web Keys url to authenticate the token
        String url = String.format("https://cognito-idp.%s.amazonaws.com/%s/.well-known/jwks.json", aws_cognito_region, aws_user_pools_id);
        try {
            aws_kid_store_url = new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(String.format("Invalid URL provided, URL=%s", url));
        }
        provider = new JwkProviderBuilder(aws_kid_store_url).build();
    }


    @Override
    public RSAPublicKey getPublicKeyById(String kid) {
        try {
            return (RSAPublicKey) provider.get(kid).getPublicKey();
        } catch (JwkException e) {
            throw new RuntimeException(String.format("Failed to get JWT kid=%s from aws_kid_store_url=%s", kid, aws_kid_store_url));
        }
    }

    @Override
    public RSAPrivateKey getPrivateKey() {
        return null;
    }

    @Override
    public String getPrivateKeyId() {
        return null;
    }

    public DecodedJWT verifyToken(String token){
        Algorithm algorithm = Algorithm.RSA256(this);
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        return jwtVerifier.verify(token);
    }
}