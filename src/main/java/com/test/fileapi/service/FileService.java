package com.test.fileapi.service;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class FileService {

    @Value("cyworld-bucket")
    private String bucketName;

    @Autowired(required = false)
    private Storage storage;

    public String uploadFile(MultipartFile file) {
        try {
            // 원본 파일명과 확장자 분리
            String originalFileName = file.getOriginalFilename();
            String fileExtension = "";

            if (originalFileName != null && originalFileName.contains(".")) {
                fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            }

            // UUID를 이용한 고유 파일명 생성
            String uniqueFileName = UUID.randomUUID().toString() + fileExtension;

            // BlobId 및 BlobInfo 생성
            BlobId blobId = BlobId.of(bucketName, uniqueFileName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

            // 파일 업로드
            storage.create(blobInfo, file.getBytes());

            // 업로드된 파일의 GCS URL 반환
            return String.format("https://storage.googleapis.com/%s/%s", bucketName, uniqueFileName);

        } catch (IOException e) {
            throw new RuntimeException("파일 업로드 실패", e);
        }
    }

}
