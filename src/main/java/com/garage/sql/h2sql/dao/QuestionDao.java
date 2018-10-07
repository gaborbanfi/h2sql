package com.garage.sql.h2sql.dao;

import com.garage.sql.h2sql.entities.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class QuestionDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public QuestionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Answer getTheOnlyAnswer(String question, boolean indexed) {
        final String QUERY = "select * from " + (indexed ? "answers" : "answersSlow") + " where question = ?;";
        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement ps = connection.prepareStatement(QUERY);
            ps.setString(1, question);
            return ps;
        };
        List<Answer> answers = jdbcTemplate.query(preparedStatementCreator,
            (resultSet, i) -> {
                long id = resultSet.getLong("id");
                String ans = resultSet.getString("answer");
                return new Answer(id, ans);
        });

        return answers.get(0);
    }

    public void insertAnswer(String question, String answer) {
        jdbcTemplate.update("insert into answers(question, answer) values (?, ?)", question, answer);
    }

    public void insertAnswerIntoSlow(String question, String answer) {
        jdbcTemplate.update("insert into answersSlow(question, answer) values (?, ?)", question, answer);
    }
}
