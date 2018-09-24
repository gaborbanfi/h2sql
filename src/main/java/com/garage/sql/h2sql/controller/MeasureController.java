package com.garage.sql.h2sql.controller;

import com.garage.sql.h2sql.service.MeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeasureController {
    private final MeasureService measureService;

    @Autowired
    public MeasureController(MeasureService measureService) {
        this.measureService = measureService;
    }

    @GetMapping("/measure")
    public long answerMe() {
        return measureService.measureIndexedQuery();
    }

    @GetMapping("/measure/slow")
    public long answerMeSlowly() {
        return measureService.measureNonIndexedQuery();
    }
}
