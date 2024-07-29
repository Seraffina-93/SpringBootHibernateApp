package com.ingrammicro.service.implementation;

import com.ingrammicro.model.Language;
import com.ingrammicro.repository.LanguageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

public class VoteServiceImplTest {
    @Mock
    private LanguageRepository languageRepository;

    @InjectMocks
    private VoteServiceImpl voteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void voteForExistingLanguage() {
        Language language = new Language();
        language.setName("Java");
        language.setCounter(1);

        when(languageRepository.findByName("Java")).thenReturn(Optional.of(language));
        when(languageRepository.save(language)).thenReturn(language);

        Language updatedLanguage = voteService.voteForLanguage("Java");

        assertEquals(2, updatedLanguage.getCounter());
        verify(languageRepository, times(1)).findByName("Java");
        verify(languageRepository, times(1)).save(language);
    }

    @Test
    void voteForNewLanguage() {
        when(languageRepository.findByName("Go")).thenReturn(Optional.empty());

        Language savedLanguage = new Language();
        savedLanguage.setName("Go");
        savedLanguage.setCounter(1);

        when(languageRepository.save(any(Language.class))).thenReturn(savedLanguage);

        Language newLanguage = voteService.voteForLanguage("Go");

        assertEquals(1, newLanguage.getCounter());
        assertEquals("Go", newLanguage.getName());
        verify(languageRepository, times(1)).findByName("Go");
        verify(languageRepository, times(1)).save(any(Language.class));
    }
}
