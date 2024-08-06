package com.ingrammicro.service.implementation;

import com.ingrammicro.model.Language;
import com.ingrammicro.repository.LanguageRepository;
import com.ingrammicro.service.VoteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of the VoteService interface for handling votes for languages
 */
@Service
@Transactional
public class VoteServiceImpl implements VoteService {
    private final LanguageRepository languageRepository;

    public VoteServiceImpl(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public Language voteForLanguage(String name) {
        Language language = languageRepository.findByName(name).orElseGet(() -> {
            Language newLanguage = new Language();
            newLanguage.setName(name);
            newLanguage.setCounter(0);
            return newLanguage;
        });
        language.setCounter(language.getCounter() + 1);
        return languageRepository.save(language);
    }

}
