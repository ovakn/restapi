package com.ovakn.restapi.repository;

import com.ovakn.restapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRep extends JpaRepository<User, Integer> {
    User findByName(String name);
    User findByEmail(String email);
}