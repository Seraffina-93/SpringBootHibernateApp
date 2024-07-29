package com.ingrammicro.service.implementation;

import com.ingrammicro.exception.ResourceNotFoundException;
import com.ingrammicro.model.Language;
import com.ingrammicro.repository.LanguageRepository;
import com.ingrammicro.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository languageRepository;

    public LanguageServiceImpl(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public Language saveLanguage(Language language) {

        return languageRepository.save(language);
    }

    @Override
    public List<Language> getAllLanguages() {

        return languageRepository.findAll();
    }

    @Override
    public Language getLanguageById(long id) {
        return languageRepository.findById(id).orElseThrow( () ->
                new ResourceNotFoundException("Language", "id", id));
    }

    @Override
    public Language updateLanguage(Language language, long id) {
        Language existingLanguage = languageRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Language", "id", id));

        existingLanguage.setName(language.getName());
        existingLanguage.setCounter(language.getCounter());
        languageRepository.save(existingLanguage);
        return existingLanguage;
    }

    @Override
    public void deleteLanguage(long id) {
        if (!languageRepository.existsById(id)) {
            throw new ResourceNotFoundException("Language", "id", id);
        }
        languageRepository.deleteById(id);
    }

}
