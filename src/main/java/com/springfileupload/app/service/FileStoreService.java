package com.springfileupload.app.service;

import com.springfileupload.app.model.FileStore;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileStoreService {
    boolean saveFileToLocal(MultipartFile multipartFile);

    FileStore saveFileToDB(MultipartFile multipartFile);

    FileStore getFileStoreById(int fileId);

    List<FileStore> getAllFiles();

    void deleteFileFromDB(int fileId);

    void deleteFileFromLocal(FileStore fileStore);
}
