package com.ovakn.restapi.repository;

import com.ovakn.restapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRep extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}