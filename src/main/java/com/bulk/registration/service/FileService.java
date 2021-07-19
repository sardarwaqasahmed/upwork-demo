package com.bulk.registration.service;

import com.bulk.registration.model.Sim;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface FileService {
    String FILE_LOCATION = "storage/";

    default void initializeDirectory() {

        File directory = new File(FILE_LOCATION);
        if (directory.mkdir()) System.out.println("directory created");
    }

    void saveFile(final Sim simRecord);
}