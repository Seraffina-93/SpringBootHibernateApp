package com.ingrammicro.controller;

import com.ingrammicro.model.Language;
import com.ingrammicro.service.LanguageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/language")
public class LanguageController {

    private final LanguageService languageService;

    public LanguageController(LanguageService languageService) {
        super();
        this.languageService = languageService;
    }

    @PostMapping
    public ResponseEntity<Language> saveLanguage(@RequestBody Language language) {
        return new ResponseEntity<>(languageService.saveLanguage(language), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Language> getAllLanguages(){
        return languageService.getAllLanguages();
    }

    @GetMapping("{id}")
    public ResponseEntity<Language> getLanguageById(@PathVariable("id") long id) {
        return new ResponseEntity<>(languageService.getLanguageById(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Language> updateLanguage(@PathVariable("id") long id, @RequestBody Language language){
        return new ResponseEntity<>(languageService.updateLanguage(language, id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteLanguage(@PathVariable("id") long id){
        languageService.deleteLanguage(id);
        return new ResponseEntity<>("Language deleted successfully!.", HttpStatus.OK);
    }
}
