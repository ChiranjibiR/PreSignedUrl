package org.projectdev.presigned_url.controller;

import org.projectdev.presigned_url.service.S3Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@RequestMapping("/s3")
public class S3Controller {

    private final S3Service s3Service;

    public S3Controller(S3Service s3Service){
        this.s3Service=s3Service;
    }

    @GetMapping("/generate-url")
    public ResponseEntity<String> generateDownloadUrl(@RequestParam String fileName,
                                                      @RequestParam(defaultValue = "10") long duration){
        String url = s3Service.generatePresignedDownloadUrl(fileName, Duration.ofMinutes(duration));
        return ResponseEntity.ok(url);
    }

    @GetMapping("/upload-url")
    public ResponseEntity<String> generateUploadUrl(@RequestParam String fileName,
                                                    @RequestParam(defaultValue = "10") long duration) {
        String url = s3Service.generatePresignedUploadUrl(fileName, Duration.ofMinutes(duration));
        return ResponseEntity.ok(url);
    }
}
