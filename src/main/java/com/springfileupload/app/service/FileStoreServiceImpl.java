package com.springfileupload.app.service;

import com.springfileupload.app.model.FileStore;
import com.springfileupload.app.repo.FileStoreRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
public class FileStoreServiceImpl implements FileStoreService {

    @Value(value = "${com.fileupload.upload.folder.path}")
    private String folderPath;

    private final FileStoreRepository fileStoreRepository;

    public FileStoreServiceImpl(FileStoreRepository fileStoreRepository) {
        this.fileStoreRepository = fileStoreRepository;
    }

    @Override
    public boolean saveFileToLocal(MultipartFile file) {
        String fileName = getFileName(file);

        if (isValidFile(fileName, file)) {
            try {
                InputStream inputStream = file.getInputStream();
                Files.copy(inputStream, Paths.get(folderPath + fileName), REPLACE_EXISTING);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public FileStore saveFileToDB(MultipartFile file) {
        FileStore fileStore = new FileStore();
        String fileName = getFileName(file);

        if (isValidFile(fileName, file)) {
            try {
                fileStore.setFileName(fileName);
                fileStore.setFile(file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileStoreRepository.save(fileStore);
    }

    @Override
    public FileStore getFileStoreById(int id) {
        return fileStoreRepository.findById(id).get();

    }

    @Override
    public List<FileStore> getAllFiles() {
        return fileStoreRepository.findAll();
    }

    @Override
    public void deleteFileFromDB(int fileId) {
        fileStoreRepository.deleteById(fileId);
    }

    @Override
    public void deleteFileFromLocal(FileStore fileStore) {
        String fileName = fileStore.getFileName();

        try {
            Files.deleteIfExists(Path.of(folderPath+fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getFileName(MultipartFile file) {
        return StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
    }

    public boolean isValidFile(String fileName, MultipartFile file) {
        return !file.isEmpty() && !fileName.contains("..");
    }


}
