package com.telusko.quiz_service.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Quiz {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String title;

        @ElementCollection
        private List<Integer> questions;
    }

