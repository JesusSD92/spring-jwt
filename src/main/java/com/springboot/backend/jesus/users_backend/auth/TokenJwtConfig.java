package com.springboot.backend.jesus.users_backend.auth;

import io.jsonwebtoken.Jwts;
import javax.crypto.SecretKey;

public class TokenJwtConfig {

    public static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();

    public static final String AUTHORIZATION = "Authorization";

    public static final String BEARER = "Bearer ";

    public static final String JSON = "application/json";
}
