package org.example.test.entity;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;
}
