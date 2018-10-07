package com.garage.sql.h2sql.controller;

import com.garage.sql.h2sql.service.MeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeasureController {
    private final MeasureService measureService;

    @Autowired
    public MeasureController(MeasureService measureService) {
        this.measureService = measureService;
    }

    @GetMapping("/measure/{question}")
    public ResponseEntity answerMe(String question) {
        try {
            measureService.measureIndexedQuery(question);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/slowmeasure/{question}")
    public ResponseEntity answerMeSlowly(String question) {
        try {
            measureService.measureNonIndexedQuery(question);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
