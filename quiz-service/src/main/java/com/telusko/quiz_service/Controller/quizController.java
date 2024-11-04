package com.telusko.quiz_service.Controller;


import com.telusko.quiz_service.Model.QuestionWrapper;
import com.telusko.quiz_service.Model.QuizDto;
import com.telusko.quiz_service.Model.Response;
import com.telusko.quiz_service.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class quizController {

    @Autowired
    private QuizService quizservice;

    @PostMapping("create")
    public ResponseEntity<String> CreateQuiz(@RequestBody QuizDto quizDto){
        return quizservice.createQuiz(quizDto.getCategory(), quizDto.getNumQ(), quizDto.getTitle());
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable int id){
        System.out.println("ABCD");
       return quizservice.getQuizQuestions(id);
    }
//
    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> calculateResult(@PathVariable Integer id, @RequestBody List<Response> responses){
        return quizservice.calculateResult(id, responses);
    }

    @GetMapping("hello")
    public String hello(){
        return "HELLO";
    }
}
