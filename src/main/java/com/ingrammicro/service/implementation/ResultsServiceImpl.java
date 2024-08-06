package com.ingrammicro.service.implementation;

import com.ingrammicro.dto.ResultDTO;
import com.ingrammicro.model.Language;
import com.ingrammicro.repository.LanguageRepository;
import com.ingrammicro.service.ResultsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the ResultsService interface for calculating voting results
 */
@Service
public class ResultsServiceImpl implements ResultsService {
    private final LanguageRepository languageRepository;

    public ResultsServiceImpl(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public List<ResultDTO> calculateResults() {
        List<Language> languages = languageRepository.findAll();
        int totalVotes = languages.stream().mapToInt(Language::getCounter).sum();

        return languages.stream()
                .map(language -> new ResultDTO(language.getName(),
                        calculatePercentage(language.getCounter(), totalVotes)))
                .collect(Collectors.toList());
    }

    private double calculatePercentage(int count, int total) {
        return total == 0 ? 0 : (double) count / total * 100;
    }
}
