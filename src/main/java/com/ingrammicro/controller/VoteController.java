package com.ingrammicro.controller;

import com.ingrammicro.model.Language;
import com.ingrammicro.model.VoteRequest;
import com.ingrammicro.service.VoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vote")
public class VoteController {
    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping
    public ResponseEntity<Language> voteForLanguage(@RequestBody VoteRequest voteRequest) {
        Language updatedLanguage = voteService.voteForLanguage(voteRequest.getName());
        return new ResponseEntity<>(updatedLanguage, HttpStatus.OK);
    }
}
