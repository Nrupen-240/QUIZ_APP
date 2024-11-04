package com.telusko.question_service.DAO;


import com.telusko.question_service.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer> {
    List<Question> findBycategory(String category);

    @Query(value = "SELECT q.id FROM question q where q.category=:category  ORDER BY RANDOM() LIMIT :numQuestions", nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(String category, Integer numQuestions);
}
