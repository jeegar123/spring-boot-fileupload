package com.springfileupload.app.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "file_store", schema = "files")
public class FileStore {
    private int id;
    private String fileName;
    private byte[] file;
    private Timestamp updatedDate;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "file_name", nullable = false, length = 30)
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    @Basic
    @Column(name = "file", nullable = false)
    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    @Basic
    @Column(name = "updated_date", nullable = false)
    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileStore fileStore = (FileStore) o;
        return id == fileStore.id &&
                Objects.equals(fileName, fileStore.fileName) &&
                Arrays.equals(file, fileStore.file) &&
                Objects.equals(updatedDate, fileStore.updatedDate);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, fileName, updatedDate);
        result = 31 * result + Arrays.hashCode(file);
        return result;
    }

    @Override
    public String toString() {
        return "FileStore{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", file=" + Arrays.toString(file) +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
