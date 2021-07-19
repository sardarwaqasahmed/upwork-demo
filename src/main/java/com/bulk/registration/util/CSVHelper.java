package com.bulk.registration.util;

import com.bulk.registration.model.Sim;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class CSVHelper {

    @Value("${date.format}")
    private String DATE_FORMAT;

    public List<Sim> readRecordsFromFile(final String path) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

        try {
            File file = new File(path);
            InputStream inputStream = new FileInputStream(file);
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

            CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

            List<Sim> data = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Sim.SimBuilder simBuilder = Sim.builder();

                if (StringUtils.isNoneBlank(csvRecord.get("MSISDN")))
                    simBuilder.phoneNumber(csvRecord.get("MSISDN"));
                if (StringUtils.isNoneBlank(csvRecord.get("SIM_TYPE")))
                    simBuilder.simType(csvRecord.get("SIM_TYPE"));
                if (StringUtils.isNoneBlank(csvRecord.get("NAME")))
                    simBuilder.name(csvRecord.get("NAME"));
                if (StringUtils.isNoneBlank(csvRecord.get("DATE_OF_BIRTH")))
                    simBuilder.dateOfBirth(LocalDate.parse(csvRecord.get("DATE_OF_BIRTH"), formatter));
                if (StringUtils.isNoneBlank(csvRecord.get("GENDER")))
                    simBuilder.gender(csvRecord.get("GENDER"));
                if (StringUtils.isNoneBlank(csvRecord.get("ADDRESS")))
                    simBuilder.address(csvRecord.get("ADDRESS"));
                if (StringUtils.isNoneBlank(csvRecord.get("ID_NUMBER")))
                    simBuilder.idNumber(Long.valueOf(csvRecord.get("ID_NUMBER")));

                data.add(simBuilder.build());
            }

            return data;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

}