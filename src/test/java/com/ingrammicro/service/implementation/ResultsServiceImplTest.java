package com.ingrammicro.service.implementation;

import com.ingrammicro.dto.ResultDTO;
import com.ingrammicro.model.Language;
import com.ingrammicro.repository.LanguageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ResultsServiceImplTest {
    @Mock
    private LanguageRepository languageRepository;

    private ResultsServiceImpl resultsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        resultsService = new ResultsServiceImpl(languageRepository);
    }

    @Test
    void testCalculateResults() {
        Language java = new Language();
        java.setName("Java");
        java.setCounter(3);

        Language python = new Language();
        python.setName("Python");
        python.setCounter(2);

        when(languageRepository.findAll()).thenReturn(List.of(java, python));

        List<ResultDTO> results = resultsService.calculateResults();

        assertEquals(2, results.size());

        ResultDTO javaResult = results.get(0);
        assertEquals("Java", javaResult.getName());
        assertEquals(60.0, javaResult.getPercentage());

        ResultDTO pythonResult = results.get(1);
        assertEquals("Python", pythonResult.getName());
        assertEquals(40.0, pythonResult.getPercentage());

        verify(languageRepository, times(1)).findAll();
    }

    @Test
    void testCalculatePercentage() throws Exception {
        // Access the private method using reflection
        var method = ResultsServiceImpl.class.getDeclaredMethod("calculatePercentage", int.class, int.class);
        method.setAccessible(true);

        // Test cases
        assertEquals(0.0, method.invoke(resultsService, 0, 0));
        assertEquals(0.0, method.invoke(resultsService, 0, 100));
        assertEquals(50.0, method.invoke(resultsService, 50, 100));
        assertEquals(100.0, method.invoke(resultsService, 100, 100));
    }
}
