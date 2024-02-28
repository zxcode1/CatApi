package org.example.test.apihandlers.inner.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.test.entity.Article;
import org.example.test.entity.Cat;
import org.example.test.entity.CatDTO;
import org.example.test.repository.ArticleRepository;
import org.example.test.repository.CatRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CRUDService {
    private final CatRepository catRepository;
    private final ArticleRepository articleRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Value("${key.for.register}")
    private String secretKey;

    public ResponseEntity<CatDTO> getCatByIdOld(Long id){
        Optional<Cat> catOptional = catRepository.findById(id);
        return catOptional.map(cat -> {
            CatDTO dtoCat = modelMapper.map(cat, CatDTO.class);
            return ResponseEntity.ok(dtoCat); // Возвращаем пользователя и статус 200 OK
        }).orElseGet(() -> ResponseEntity.notFound().build()); // Если пользователя не найдено, возвращаем статус 404 Not Found
    }

//    @Transactional
//    public void register(Map<String, String> request) {
//        String username = request.get("username");
//        String password = request.get("password");
//        String secretKeyReq = request.get("secretKey");
//
//        if (!secretKey.equals(secretKeyReq)) {
//            throw new IllegalArgumentException("Неправильный секретный ключ");
//        }
//
//        if (catRepository.findByUsername(username).getUsername().isBlank()) {
//            throw new IllegalArgumentException("Пользователь с именем " + username + " уже существует");
//        }
//        String encodedPassword = passwordEncoder.encode(password);
//
//        Cat cat = new Cat();
//        cat.setUsername(username);
//        cat.setPassword(encodedPassword);
//        catRepository.save(cat);
//    }

    @Transactional
    public void updateLoginOld(Long id, Map<String, String> request) {
        String oldUsername = request.get("oldUsername");
        String newUsername = request.get("newUsername");
        String password = request.get("password");

        Optional<Cat> catOptional = catRepository.findById(id);
        catOptional.ifPresent(cat -> {
            if (Objects.equals(oldUsername, cat.getUsername()) && Objects.equals(password, cat.getPassword())) {
                cat.setUsername(newUsername);
                catRepository.save(cat);
            } else {
                throw new EntityNotFoundException("User with id " + id + " not found");
            }
        });
    }

    @Transactional
    public void updatePasswordOld(Long id, Map<String, String> request) {
        String username = request.get("username");
        String oldPassword = request.get("oldPassword");
        String newPassword = request.get("newPassword");
        String secretKeyReq = request.get("secretKey");

        if (!secretKey.equals(secretKeyReq)) {
            throw new IllegalArgumentException("Неправильный секретный ключ");
        }
        catRepository.findById(id)
                .filter(cat -> cat.getUsername().equals(username))
                .filter(cat -> passwordEncoder.matches(oldPassword, cat.getPassword()))
                .ifPresentOrElse(cat -> {
                    cat.setPassword(passwordEncoder.encode(newPassword));
                    catRepository.save(cat);
                }, () -> {
                    throw new IllegalArgumentException("Нет такого пользователя с id = " + id);
                });
    }

    @Transactional
    public void deleteOld(Long id) {
        catRepository.findById(id).ifPresentOrElse(
            catRepository::delete,
            () -> {
                throw new EntityNotFoundException("Пользователь с идентификатором " + id + " не найден");
            }
        );
    }

    @Transactional
    public void delete(Long id) {
        catRepository.findById(id).ifPresentOrElse(
                catRepository::delete,
                () -> {
                    throw new EntityNotFoundException("Пользователь с идентификатором " + id + " не найден");
                }
        );
    }








    public void addArticle(Map<String, String> request){
        String field1 = request.get("field1");
        String field2 = request.get("field2");

        Article article = new Article();
        article.setField1(field1);
        article.setField2(field2);
        articleRepository.save(article);
    }

    public ResponseEntity<Article> getArticleById(Long id){
        Optional<Article> articleOptional = articleRepository.findById(id);
        return articleOptional.map(article -> {
            Article article1 = modelMapper.map(article, Article.class);
            return ResponseEntity.ok(article1); // Возвращаем пользователя и статус 200 OK
        }).orElseGet(() -> ResponseEntity.notFound().build()); // Если пользователя не найдено, возвращаем статус 404 Not Found
    }
}
