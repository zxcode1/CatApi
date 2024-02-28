package org.example;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class CatMultiply {

    private Long id;

    //Для входа в аккаунт
    private String username;
    private String password;

    private String name;

    //owner
    private String ownersFirstname;
    private String ownersLastName;
    private String ownersEmail;
    private String ownersNumber;

    private String country;
    private String role;

    //Дата и время создания записи кота
    private LocalDateTime createdAt;
    //Дата и время последнего обновления записи кота
    private LocalDateTime updatedAt;

    private String age;

    private String gender;

    //Основной цвет кота
    private String color;

    //Порода кота
    private String breed;

    //Вес кота
    private double weight;

    private double size;

    //Флаг, указывающий на то, был ли кот кастрирован
    private boolean isNeutered;

    //Флаг, указывающий на то, был ли кот привит
    private boolean isVaccinated;

    private String description;

    private ArrayList<String> imageUrl;

    //Флаг, указывающий на активность кота (тип boolean), который определяет, активен ли кот в системе или нет.
    private boolean isActive;

    private Date dateOfBirth;

    //Характер или личность кота (тип String), описывающий поведение и характер кота.
    private String personality;

//    private Owner owner;

    private ArrayList<String> habits;

    private String medicalHistory;
    private String medicalConditions;

    private String favoriteToys;

    private String diet;

    private String microchipId;

    private String registrationNumber;

//    private Veterinarian veterinarian;

    //Потребности в уходе за шерстью и кожей (тип String или перечисление), указывающие на требования к уходу за шерстью и кожей кота.
    private String groomingNeeds;
    //Уровень активности (тип String или перечисление), описывающий активность кота, например, высокий, средний или низкий.
    private String activityLevel;

    //Среда обитания (тип String или перечисление), указывающая на предпочтения в условиях проживания кота, например, живет в квартире или на улице.
    private String livingEnvironment;

    //Продолжительность жизни (тип Integer или другой числовой тип), указывающая на ожидаемую продолжительность жизни кота в годах.
    private String lifeExpectancy;
    //Признак приученности к лотку (тип boolean), указывающий, приучен ли кот к использованию лотка для туалета.
    private String litterTrained;
    //Особые потребности (тип String), указывающие на особые медицинские или другие потребности кота.
    private String specialNeeds;


}
