package com.ingrammicro.controller;

import com.ingrammicro.model.Language;
import com.ingrammicro.model.VoteRequest;
import com.ingrammicro.service.VoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class VoteControllerTest {
    @Mock
    private VoteService voteService;

    @InjectMocks
    private VoteController voteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testVote() {
        VoteRequest voteRequest = new VoteRequest();
        voteRequest.setName("Java");

        Language language = new Language();
        language.setName("Java");
        language.setCounter(2);

        when(voteService.voteForLanguage("Java")).thenReturn(language);

        ResponseEntity<Language> response = voteController.voteForLanguage(voteRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(language, response.getBody());
    }

    @Test
    void testVoteForNewLanguage() {
        VoteRequest voteRequest = new VoteRequest();
        voteRequest.setName("NewLanguage");

        Language language = new Language();
        language.setName("NewLanguage");
        language.setCounter(1);

        when(voteService.voteForLanguage("NewLanguage")).thenReturn(language);

        ResponseEntity<Language> response = voteController.voteForLanguage(voteRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(language, response.getBody());
    }
}

