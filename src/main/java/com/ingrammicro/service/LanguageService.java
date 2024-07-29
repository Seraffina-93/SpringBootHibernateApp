package com.ingrammicro.service;

import com.ingrammicro.model.Language;

import java.util.List;

public interface LanguageService {
    Language saveLanguage(Language language);
    List<Language> getAllLanguages();
    Language getLanguageById(long id);
    Language updateLanguage(Language language, long id);
    void deleteLanguage(long id);
}
