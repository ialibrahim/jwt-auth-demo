package org.ialibrahim.jwtauthdemo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("demo.jwt")
@Data
public class JwtConfiguration {

    private String secretKey;
    private String issuer;
    private Integer accessTokenValidity;
    private String accessTokenHeader;
    private String accessTokenPrefix;

    @Bean
    public Algorithm algorithm() {

        return Algorithm.HMAC256(secretKey);
    }

    @Bean
    public JWTVerifier jwtVerifier(Algorithm algorithm) {

        return JWT.require(algorithm).withIssuer(issuer).build();
    }
}
