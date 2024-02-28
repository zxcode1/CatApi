//package org.example;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//
//import java.util.Date;
//
//@SpringBootApplication
//public class Main {
//    public static void main(String[] args) {
//        SpringApplication.run(Main.class, args);
//    }
//
//    public String testExampleGenerateToken() {
//        Date now = new Date();
//        String pass = "zxczxczxczxczxczxczxczxczxczxczxczxczxczxczxczxczxczxczxczxczxc";
//        return Jwts.builder()
//                .subject("dan")
//                .issuer("serv")
//                .issuedAt(now)
//                .expiration(new Date(now.getTime() + 3600000)) //1 hour
//                .claim("role", "user")
//                .signWith(SignatureAlgorithm.HS256, pass)
//                .compact();
//    }
//
//    @Bean
//    ApplicationRunner applicationRunner() {
//        return args -> {
//            Main main = new Main();
//            String token = main.testExampleGenerateToken();
//            System.out.println(token);
//        };
//    }
//
//}
