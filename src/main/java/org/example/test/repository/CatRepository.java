package org.example.test.repository;

import org.example.test.entity.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {

    Cat findByUsername(String username);
}
