package com.telusko.question_service.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class QuestionWrapper {
    private int id;
    private String QuestionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
}
