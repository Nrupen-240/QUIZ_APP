package com.telusko.quiz_service.DAO;


import com.telusko.quiz_service.Model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz, Integer> {
}
