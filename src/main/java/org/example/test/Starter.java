package org.example.test;

import lombok.AllArgsConstructor;
import org.example.test.entity.Cat;
import org.example.test.repository.CatRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
@AllArgsConstructor
public class Starter implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(Starter.class,args);
    }

    private final CatRepository catRepository;
    @Override
    public void run(ApplicationArguments args) {
        String defaultUsername = "name";
        String defaultPassword = "password";

        // Проверяем, есть ли пользователь с таким именем уже в базе данных
        if (catRepository.findByUsername(defaultUsername) == null) {
            // Создаем нового пользователя
            Cat cat = new Cat();
            cat.setUsername(defaultUsername);
            cat.setPassword(defaultPassword);

            // Сохраняем пользователя в базе данных
            catRepository.save(cat);

            System.out.println("Создан пользователь с именем " + defaultUsername + " и паролем " + defaultPassword);
        } else {
            System.out.println("Пользователь с именем " + defaultUsername + " уже существует в базе данных");
        }
    }
}
