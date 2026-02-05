package com.iss.securitydb.repos;

import com.iss.securitydb.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<Users,Integer> {

    Users findByUsername(String username);

}

