package com.test.fileapi.controller;

import com.test.fileapi.dto.FileUploadRequest;
import com.test.fileapi.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping("/uploadView")
    public String showUploadForm() {
        return "uploadView"; // => /WEB-INF/views/uploadView.jsp
    }

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@ModelAttribute FileUploadRequest fileUploadRequest) {
        try {
            String fileUrl = fileService.uploadFile(fileUploadRequest.getFile());
            System.out.println(fileUrl);
            return ResponseEntity.ok("성공");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body("<UNK>");
        }
    }
}
