package com.garage.sql.h2sql.service;

import com.garage.sql.h2sql.dao.QuestionDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.IntStream;

@Service
public class DBFillService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DBFillService.class);

    private final QuestionDao questionDao;

    @Autowired
    public DBFillService(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillQuestionDBWithRandomDataPlusAlma() {
        LOGGER.info("DB filling started...");

        final int BATCH_COUNT = 500_000;
        IntStream.range(0, BATCH_COUNT)
                .forEach((i) -> {
                    String uuid = UUID.randomUUID().toString();
                    questionDao.insertAnswer(uuid, "pass");
                    questionDao.insertAnswerIntoSlow(uuid, "pass");
                });

        questionDao.insertAnswer("alma?", "alma!");
        questionDao.insertAnswerIntoSlow("alma?", "alma!");

        IntStream.range(0, BATCH_COUNT)
                .forEach((i) -> {
                    String uuid = UUID.randomUUID().toString();
                    questionDao.insertAnswer(uuid, "pass");
                    questionDao.insertAnswerIntoSlow(uuid, "pass");
                });

        LOGGER.info("DB filling done!");
    }
}
