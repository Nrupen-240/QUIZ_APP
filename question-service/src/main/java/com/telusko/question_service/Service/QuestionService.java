package com.telusko.question_service.Service;


import com.telusko.question_service.DAO.QuestionRepo;
import com.telusko.question_service.Model.Question;
import com.telusko.question_service.Model.QuestionWrapper;
import com.telusko.question_service.Model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepo questionRepo;
    public ResponseEntity<List<Question>> getAll() {
        try {
            return new ResponseEntity<>(questionRepo.findAll(), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuesstionByCategory(String category) {
        try {
            return new ResponseEntity<>(questionRepo.findBycategory(category), HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> getQuestionById(int id) {
        try {
            Optional<Question> que = questionRepo.findById(id);
            if(que.isPresent()) {
                return new ResponseEntity<>(que.get(), HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("Question Not Found", HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("An Error Occured Internally", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> addQuestion(Question question) {
       Question que =  questionRepo.save(question);
       int id = que.getId();
       try {
           if (id > 0) {
               return new ResponseEntity<>("Question Added", HttpStatus.CREATED);
           } else {
               return new ResponseEntity<>("Question Not Added", HttpStatus.BAD_REQUEST);
           }
       }
       catch (Exception e){
           e.printStackTrace();
       }
       return new ResponseEntity<>("ERROR", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> deleteQuestionById(int id) {
        try{
            if(questionRepo.existsById(id)){
                questionRepo.deleteById(id);
                if(!questionRepo.existsById(id)){
                    return new ResponseEntity<>("Question Deleted Successfully", HttpStatus.OK);
                }
                else {
                    return new ResponseEntity<>("Failed To delete Question", HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Question Not Found",HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz( String category, Integer numQuestions) {
        List<Integer> questions = questionRepo.findRandomQuestionsByCategory(category, numQuestions);

        return new ResponseEntity<>(questions, HttpStatus.OK);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
        List<QuestionWrapper> wrappers = new ArrayList<>();
        List<Question> questions = new ArrayList<>();

        for(Integer id : questionIds){
            questions.add(questionRepo.findById(id).get());
        }
        for(Question question : questions){
            QuestionWrapper wrapper = new QuestionWrapper();
            wrapper.setId(question.getId());
            wrapper.setQuestionTitle(question.getQuestionTitle());
            wrapper.setOption1(question.getOption1());
            wrapper.setOption2(question.getOption2());
            wrapper.setOption3(question.getOption3());
            wrapper.setOption4(question.getOption4());
            wrappers.add(wrapper);
        }
        return new ResponseEntity<>(wrappers, HttpStatus.OK );
    }

    public ResponseEntity<Integer> calculate(List<Response> responses) {

        int correct = 0;
        for(Response response : responses){
            Question question = questionRepo.findById(response.getId()).get();
            if(response.getResponse().equals(question.getRightAnswer())){
                correct++;
            }
        }
        return new ResponseEntity<>(correct, HttpStatus.OK);
    }
}
