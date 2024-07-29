package com.ingrammicro.controller;

import com.ingrammicro.dto.ResultDTO;
import com.ingrammicro.service.ResultsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/results")
public class ResultsController {
    private final ResultsService resultsService;

    public ResultsController(ResultsService resultsService) {
        this.resultsService = resultsService;
    }

    @GetMapping
    public ResponseEntity<List<ResultDTO>> getResults() {
        List<ResultDTO> results = resultsService.calculateResults();
        return ResponseEntity.ok(results);
    }

}
