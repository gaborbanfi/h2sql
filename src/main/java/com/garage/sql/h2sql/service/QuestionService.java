package com.garage.sql.h2sql.service;

import com.garage.sql.h2sql.dao.QuestionDao;
import com.garage.sql.h2sql.entities.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
    private final QuestionDao questionDao;

    @Autowired
    public QuestionService(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    public Answer getTheOnlyAnswer(String question) {
        return questionDao.getTheOnlyAnswer(question, true);
    }

    public Answer getTheOnlyAnswer(String question, boolean indexed) {
        return questionDao.getTheOnlyAnswer(question, indexed);
    }
}
