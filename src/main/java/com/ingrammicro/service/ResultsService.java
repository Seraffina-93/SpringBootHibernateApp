package com.ingrammicro.service;

import com.ingrammicro.dto.ResultDTO;

import java.util.List;

/**
 * Service interface for calculating and retrieving voting results
 */
public interface ResultsService {
    List<ResultDTO> calculateResults();
}
