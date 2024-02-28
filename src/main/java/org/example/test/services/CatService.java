package org.example.test.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.example.test.entity.Cat;
import org.example.test.entity.CatDTO;
import org.example.test.repository.CatRepository;
import org.example.test.CatUserDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CatService {
    private final CatUserDetailsService catUserDetailsService;
    private final CatRepository catRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Value("${secret.key}")
    private String secretKey;

    public void register(String username, String password) {
        String encodedPassword = passwordEncoder.encode(password);

//        List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
//        cat.setAuthorities(authorities);

        Cat cat = new Cat();
        cat.setUsername(username);
        cat.setPassword(encodedPassword);
        catRepository.save(cat);
    }

    public String authenticate(String username, String password) {
        UserDetails userDetails = catUserDetailsService.loadUserByUsername(username);

        if (userDetails != null && checkPass(userDetails, password)) {
            Map<String, Object> extraClaims = Collections.singletonMap("customClaim", "customValue");
            return generateToken(username, extraClaims);
        } else if (userDetails == null) {
            return "Пользователь с именем " + username + " не существует";
        } else {
            return "Неправильный пароль для пользователя " + username;
        }
    }

    private boolean checkPass(UserDetails userDetails, String password) {
        return passwordEncoder.matches(password, userDetails.getPassword());
    }

    public String generateToken(String username, Map<String, Object> extraClaims) {
        Date now = new Date();
        return Jwts.builder()
                .claims(extraClaims)
                .subject(username)
                .issuer("serv")
                .issuedAt(now)
                .expiration(new Date(now.getTime() + 3600000)) //1 hour
                .claim("role", "user")
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

//    public boolean isTokenValid(String token, UserDetails userDetails) {
//        final String username = extractUsername(token);
//        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
//    }
//
//    public String extractUsername(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    private boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//
//    private Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//
//    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }

    public boolean isTokenExpired(String token) {
        Claims claims = extractAllClaims(token);
        Instant expirationTime = claims.getExpiration().toInstant();
        Instant now = Instant.now();
        return now.isAfter(expirationTime);
    }

    public Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }





    public ResponseEntity<CatDTO> getById(Long id) {
        Optional<Cat> catOptional = catRepository.findById(id);
        return catOptional.map(cat -> {
            CatDTO dtoCat = modelMapper.map(cat, CatDTO.class);
            return ResponseEntity.ok(dtoCat); // Возвращаем пользователя и статус 200 OK
        }).orElseGet(() -> ResponseEntity.notFound().build()); // Если пользователя не найдено, возвращаем статус 404 Not Found
    }
}
