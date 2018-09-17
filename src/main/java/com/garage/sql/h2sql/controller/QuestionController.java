package com.garage.sql.h2sql.controller;

import com.garage.sql.h2sql.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {
    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/question/{question}")
    public String answerMe(@PathVariable String question) {
        return questionService.getTheOnlyAnswer(question).getAnswer();
    }
}
