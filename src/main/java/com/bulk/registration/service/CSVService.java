package com.bulk.registration.service;

import com.bulk.registration.model.Sim;
import com.bulk.registration.util.CSVHelper;
import com.bulk.registration.validator.SimRecordValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class CSVService implements ICSVService {

    private final CSVHelper csvHelper;
    private final SimRecordValidator simRecordValidator;
    private final FileService fileService;

    @Override
    public void processRequest() {
        List<Sim> records = csvHelper.readRecordsFromFile("records.csv");

        records
                .stream()
                .map(simRecordValidator::validateRecord)
                .filter(Objects::nonNull)
                .forEach(fileService::saveFile)
        ;
    }

}