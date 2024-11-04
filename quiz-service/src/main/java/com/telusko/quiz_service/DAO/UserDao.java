package com.telusko.quiz_service.DAO;

import com.telusko.quiz_service.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
