package com.ingrammicro.service;

import com.ingrammicro.model.Language;

/**
 * Service interface for handling votes for languages
 */
public interface VoteService {
    Language voteForLanguage(String name);
}
