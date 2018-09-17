package com.garage.sql.h2sql.dao;

import com.garage.sql.h2sql.entities.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public QuestionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Answer getTheOnlyAnswer(String question) {
        List<Answer> answers = jdbcTemplate.query("select top 1 ans from answers",
            (resultSet, i) -> {
                String ans = resultSet.getString("ans");
                return new Answer(-1, ans);
        });

        return answers.get(0);
    }
}
