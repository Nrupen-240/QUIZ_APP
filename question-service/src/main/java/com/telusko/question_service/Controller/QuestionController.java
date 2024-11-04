package com.telusko.question_service.Controller;


import com.netflix.discovery.converters.Auto;
import com.telusko.question_service.Model.QuestionWrapper;
import com.telusko.question_service.Model.Response;
import com.telusko.question_service.Service.QuestionService;
import com.telusko.question_service.Model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.event.InternalFrameEvent;
import java.util.List;
import java.util.Properties;

@RestController
@RequestMapping("questions")
public class QuestionController {
    @Autowired
    QuestionService questionService;


    @Autowired
    Environment environment;

    @GetMapping("AllQuestions")
    public ResponseEntity<List<Question>> getAll(){
        return (questionService.getAll());
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){

            return questionService.getQuesstionByCategory(category);

    }

    @GetMapping("ID/{id}")
    public ResponseEntity<?> getQuestionById(@PathVariable int id){
        return questionService.getQuestionById(id);
    }

    @PostMapping("AddQuestion")
    public ResponseEntity<?> addQuestion(@RequestBody Question question)
    {
//        System.out.println("In AddQuestion");
       return questionService.addQuestion(question);
    }

    @DeleteMapping("ID/{id}")
    public ResponseEntity<?> deleteQuestionById(@PathVariable int id)
    {
       return questionService.deleteQuestionById(id);
    }

    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz( @RequestParam String category, @RequestParam Integer numQuestions){
        return questionService.getQuestionsForQuiz(category, numQuestions);
    }

    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestions(@RequestBody List<Integer> questionIds){
        System.out.println(environment.getProperty("local.server.port"));
        return questionService.getQuestionsFromId(questionIds);
    }

    @PostMapping("submit")
    public ResponseEntity<Integer> calculate(@RequestBody List<Response> responses){
        return questionService.calculate(responses);
    }


}
