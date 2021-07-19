package com.bulk.registration.service;

import com.bulk.registration.model.Sim;
import org.springframework.stereotype.Service;

import java.io.FileWriter;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public void saveFile(final Sim simRecord) {
        initializeDirectory();
        String fileName = FILE_LOCATION.concat(simRecord.getPhoneNumber()).concat(".txt");

        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(simRecord.toString());
            fileWriter.close();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}