package com.garage.sql.h2sql.service;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeasureService {
    private final QuestionService questionService;
    private final Timer answerQuery;

    @Autowired
    public MeasureService(QuestionService questionService, MeterRegistry meterRegistry) {
        this.questionService = questionService;
        answerQuery = meterRegistry.timer("ANSWER_QUERY");
    }

    public void measureNonIndexedQuery() {
        answerQuery.record(() -> questionService.getTheOnlyAnswer("2*2", false));
    }

    public void measureIndexedQuery() {
        answerQuery.record(() -> questionService.getTheOnlyAnswer("2*2", true));
    }
}
