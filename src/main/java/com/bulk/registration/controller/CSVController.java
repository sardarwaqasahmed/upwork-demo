package com.bulk.registration.controller;

import com.bulk.registration.service.CSVService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CSVController {

    private final CSVService csvService;

    @GetMapping("/csv")
    ResponseEntity<String> processCsvFile() {
        csvService.processRequest();

        return new ResponseEntity<>("File processed successfully", HttpStatus.OK);
    }
}