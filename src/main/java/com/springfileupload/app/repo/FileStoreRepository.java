package com.springfileupload.app.repo;

import com.springfileupload.app.model.FileStore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileStoreRepository extends JpaRepository<FileStore ,Integer> {
}
