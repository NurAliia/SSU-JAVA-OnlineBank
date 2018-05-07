package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by NurAliya on 29.04.2018.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByName(String name);
}
