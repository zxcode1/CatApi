package org.example.test.apihandlers.auth.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.test.entity.Cat;
import org.example.test.repository.CatRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class RegistService {
    private final CatRepository catRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${key.for.register}")
    private String secretKey;

    @Transactional
    public void register(Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");
        String secretKeyReq = request.get("secretKey");

        if (!secretKey.equals(secretKeyReq)) {
            throw new IllegalArgumentException("Неправильный секретный ключ");
        }

        if (catRepository.findByUsername(username).getUsername().isBlank()) {
            throw new IllegalArgumentException("Пользователь с именем " + username + " уже существует");
        }
        String encodedPassword = passwordEncoder.encode(password);

        Cat cat = new Cat();
        cat.setUsername(username);
        cat.setPassword(encodedPassword);
        catRepository.save(cat);
    }
}
