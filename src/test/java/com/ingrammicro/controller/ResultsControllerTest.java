package com.ingrammicro.controller;

import com.ingrammicro.dto.ResultDTO;
import com.ingrammicro.service.ResultsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ResultsControllerTest {

    @Mock
    private ResultsService resultsService;

    @InjectMocks
    private ResultsController resultsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getResults() {
        ResultDTO java = new ResultDTO("Java", 66.67);
        ResultDTO python = new ResultDTO("Python", 33.33);

        when(resultsService.calculateResults()).thenReturn(Arrays.asList(java, python));

        ResponseEntity<List<ResultDTO>> response = resultsController.getResults();

        assertEquals(2, response.getBody().size());
        assertEquals(66.67, response.getBody().get(0).getPercentage(), 0.01);
        assertEquals(33.33, response.getBody().get(1).getPercentage(), 0.01);
        verify(resultsService, times(1)).calculateResults();
    }
}
