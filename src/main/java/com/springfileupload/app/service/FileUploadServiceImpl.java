package com.springfileupload.app.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Value(value = "${com.fileupload.upload.folder.path}")
    private String folderPath;

    @Override
    public boolean saveFile(MultipartFile file) {

        String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        if (!file.isEmpty() && !filename.contains("..")) {
            try {
                InputStream inputStream = file.getInputStream();
                Files.copy(inputStream, Paths.get(folderPath + filename), REPLACE_EXISTING);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}
