package com.garage.sql.h2sql.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;

@Service
public class MeasureService {
    private final QuestionService questionService;

    @Autowired
    public MeasureService(QuestionService questionService) {
        this.questionService = questionService;
    }

    public long measureNonIndexedQuery() {
        return measure(false);
    }

    public long measureIndexedQuery() {
        return measure(true);
    }

    private long measure(boolean indexed) {
        final String[] questions = { "why", "2*2", "the meaning of life" };
        ExecutorService executorService = new ForkJoinPool();
        List<Callable<Object>> callableList = Arrays.asList(
                () -> questionService.getTheOnlyAnswer(questions[0], indexed),
                () -> questionService.getTheOnlyAnswer(questions[1], indexed),
                () -> questionService.getTheOnlyAnswer(questions[2], indexed)
        );

        long startTime = System.nanoTime();
        long stopTime;
        try {
            for (int i = 0; i < 1000; i++) {
                executorService.invokeAll(callableList);
            }
            stopTime = System.nanoTime();
        } catch (InterruptedException e) {
            return -1L;
        }

        return stopTime - startTime;
    }
}
