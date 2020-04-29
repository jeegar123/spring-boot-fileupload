package com.springfileupload.app.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    boolean saveFile(MultipartFile multipartFile);
}
