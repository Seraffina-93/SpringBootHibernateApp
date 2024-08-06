package com.ingrammicro.repository;

import com.ingrammicro.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for accessing language data from the database
 */
public interface LanguageRepository extends JpaRepository<Language, Long> {
    Optional<Language> findByName(String name);
}
