package com.telusko.quiz_service.Model;

import lombok.Data;

@Data
public class QuizDto {
    private int numQ;
    private String category;
    private String title;
}
