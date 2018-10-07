package com.garage.sql.h2sql.service;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeasureService {
    private final QuestionService questionService;
    private final Timer answerTimer;
    private final Timer answerTimerSlow;

    @Autowired
    public MeasureService(QuestionService questionService, MeterRegistry meterRegistry) {
        this.questionService = questionService;
        answerTimer = meterRegistry.timer("ANSWER_QUERY");
        answerTimerSlow = meterRegistry.timer("ANSWER_SLOW_QUERY");
    }

    public void measureNonIndexedQuery(String question) {
        answerTimerSlow.record(() -> questionService.getTheOnlyAnswer(question, false));
    }

    public void measureIndexedQuery(String question) {
        answerTimer.record(() -> questionService.getTheOnlyAnswer(question, true));
    }
}
