package com.test.fileapi.dto;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadRequest {
    private MultipartFile file;

    // getter & setter
    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
