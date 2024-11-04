package com.telusko.quiz_service.Service;

import com.telusko.quiz_service.DAO.QuizDao;
import com.telusko.quiz_service.Model.QuestionWrapper;
import com.telusko.quiz_service.Model.Quiz;
import com.telusko.quiz_service.Model.Response;
import com.telusko.quiz_service.fiegn.QuizInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class QuizService {

    @Autowired
    private QuizDao quizDao;

    @Autowired
    private QuizInterface quizInterface;



    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

       List<Integer> questions = quizInterface.getQuestionsForQuiz(category,numQ).getBody();
       Quiz quiz = new Quiz();
       quiz.setTitle(title);
       quiz.setQuestions(questions);
       quizDao.save(quiz);
       return new ResponseEntity<>("CREATED", HttpStatus.CREATED);
    }
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
        Quiz quiz = quizDao.findById(id).get();
        List<Integer> questions = quiz.getQuestions();
        return quizInterface.getQuestions(questions);
    }


    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        return quizInterface.calculate(responses);
    }
}
