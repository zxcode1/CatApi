package org.example.test.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String field1;

    private String field2;
}
