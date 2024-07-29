package com.ingrammicro.service.implementation;

import com.ingrammicro.exception.ResourceNotFoundException;
import com.ingrammicro.model.Language;
import com.ingrammicro.repository.LanguageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.*;

class LanguageServiceImplTest {

    @Mock
    private LanguageRepository languageRepository;

    @InjectMocks
    private LanguageServiceImpl languageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveLanguage() {
        Language language = new Language();
        language.setName("Java");
        language.setCounter(1);

        when(languageRepository.save(language)).thenReturn(language);

        Language savedLanguage = languageService.saveLanguage(language);

        assertNotNull(savedLanguage);
        assertEquals("Java", savedLanguage.getName());
        assertEquals(1, savedLanguage.getCounter());
    }

    @Test
    void testGetAllLanguages() {
        Language language1 = new Language();
        language1.setName("Java");
        language1.setCounter(1);

        Language language2 = new Language();
        language2.setName("Python");
        language2.setCounter(2);

        when(languageRepository.findAll()).thenReturn(Arrays.asList(language1, language2));

        List<Language> languages = languageService.getAllLanguages();

        assertEquals(2, languages.size());
        assertEquals("Java", languages.get(0).getName());
        assertEquals("Python", languages.get(1).getName());
    }

    @Test
    void testGetLanguageById() {
        Language language = new Language();
        language.setId(1L);
        language.setName("Java");
        language.setCounter(1);

        when(languageRepository.findById(1L)).thenReturn(Optional.of(language));

        Language foundLanguage = languageService.getLanguageById(1L);

        assertNotNull(foundLanguage);
        assertEquals("Java", foundLanguage.getName());
    }

    @Test
    void testGetLanguageByIdNotFound() {
        when(languageRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            languageService.getLanguageById(1L);
        });

        String expectedMessage = "Language not found with id : '1'";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testUpdateLanguage() {
        Language existingLanguage = new Language();
        existingLanguage.setId(1L);
        existingLanguage.setName("Java");
        existingLanguage.setCounter(1);

        Language updatedLanguage = new Language();
        updatedLanguage.setName("Python");
        updatedLanguage.setCounter(2);

        when(languageRepository.findById(1L)).thenReturn(Optional.of(existingLanguage));
        when(languageRepository.save(existingLanguage)).thenReturn(existingLanguage);

        Language result = languageService.updateLanguage(updatedLanguage, 1L);

        assertNotNull(result);
        assertEquals("Python", result.getName());
        assertEquals(2, result.getCounter());
    }

    @Test
    void testDeleteLanguage() {
        when(languageRepository.existsById(1L)).thenReturn(true);

        languageService.deleteLanguage(1L);

        verify(languageRepository, times(1)).existsById(1L);
        verify(languageRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteLanguageNotFound() {
        when(languageRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            languageService.deleteLanguage(1L);
        });

        String expectedMessage = "Language not found with id : '1'";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testUpdateLanguageThrowsExceptionWhenLanguageNotFound() {
        long id = 1L;
        Language language = new Language();
        language.setName("Java");
        language.setCounter(10);

        // Mocking the repository to throw ResourceNotFoundException
        Mockito.when(languageRepository.findById(id)).thenReturn(java.util.Optional.empty());

        // Asserting that ResourceNotFoundException is thrown
        assertThrows(ResourceNotFoundException.class, () -> {
            languageService.updateLanguage(language, id);
        });
    }

}
