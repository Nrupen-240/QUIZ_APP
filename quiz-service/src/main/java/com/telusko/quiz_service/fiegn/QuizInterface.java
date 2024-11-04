package com.telusko.quiz_service.fiegn;

import com.telusko.quiz_service.Model.QuestionWrapper;
import com.telusko.quiz_service.Model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

    @GetMapping("questions/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category, @RequestParam Integer numQuestions);

    @PostMapping("questions/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestions(@RequestBody List<Integer> questionIds);
//    @GetMapping("ID/{id}")
//    public ResponseEntity<?> getQuestionById(@PathVariable int id)

    @PostMapping("questions/submit")
    public ResponseEntity<Integer> calculate(@RequestBody List<Response> responses);
}
