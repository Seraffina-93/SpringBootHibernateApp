package com.ingrammicro.controller;

import com.ingrammicro.model.Language;
import com.ingrammicro.service.LanguageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LanguageControllerTest {
    @Mock
    private LanguageService languageService;

    @InjectMocks
    private LanguageController languageController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveLanguage() {
        Language language = new Language();
        language.setName("Java");
        when(languageService.saveLanguage(language)).thenReturn(language);

        ResponseEntity<Language> response = languageController.saveLanguage(language);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(language, response.getBody());
        verify(languageService, times(1)).saveLanguage(language);
    }

    @Test
    void getAllLanguages() {
        Language java = new Language();
        java.setName("Java");
        Language python = new Language();
        python.setName("Python");

        when(languageService.getAllLanguages()).thenReturn(Arrays.asList(java, python));

        List<Language> languages = languageController.getAllLanguages();

        assertEquals(2, languages.size());
        verify(languageService, times(1)).getAllLanguages();
    }

    @Test
    void getLanguageById() {
        Language language = new Language();
        language.setName("Java");
        when(languageService.getLanguageById(1L)).thenReturn(language);

        ResponseEntity<Language> response = languageController.getLanguageById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(language, response.getBody());
        verify(languageService, times(1)).getLanguageById(1L);
    }

    @Test
    void updateLanguage() {
        Language language = new Language();
        language.setName("Java");
        when(languageService.updateLanguage(language, 1L)).thenReturn(language);

        ResponseEntity<Language> response = languageController.updateLanguage(1L, language);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(language, response.getBody());
        verify(languageService, times(1)).updateLanguage(language, 1L);
    }

    @Test
    void deleteLanguage() {
        doNothing().when(languageService).deleteLanguage(1L);

        ResponseEntity<String> response = languageController.deleteLanguage(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Language deleted successfully!.", response.getBody());
        verify(languageService, times(1)).deleteLanguage(1L);
    }

}
