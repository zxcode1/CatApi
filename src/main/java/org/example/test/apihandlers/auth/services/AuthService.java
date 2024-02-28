package org.example.test.apihandlers.auth.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.example.test.CatUserDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final CatUserDetailsService catUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Value("${secret.key}")
    private String secretKey;

    public String authenticate(Map<String, String> authRequest) {
        String username = authRequest.get("username");
        String password = authRequest.get("password");
        UserDetails userDetails = catUserDetailsService.loadUserByUsername(username);

        if (userDetails == null) {
            return "Пользователь с именем " + username + " не существует";
        }

        boolean passwordCorrect = checkPass(userDetails, password);
        if (!passwordCorrect) {
            return "Неправильный пароль для пользователя " + username;
        }

        Map<String, Object> extraClaims = Collections.singletonMap("customClaim", "customValue");
        return generateToken(username, extraClaims);
    }

    private boolean checkPass(UserDetails userDetails, String password) {
        return passwordEncoder.matches(password, userDetails.getPassword());
    }

    public String generateToken(String username, Map<String, Object> extraClaims) {
        Date now = new Date();
        return Jwts.builder()
                .subject(username)
                .issuer("serv")
                .issuedAt(now)
                .expiration(new Date(now.getTime() + 3600000)) //1 hour
                .claim("role", "user")
                .claims(extraClaims)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
