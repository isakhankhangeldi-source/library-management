package com.example.library.controller;

import java.io.File;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/api/files")
public class IsakhanKhangeldiFileController {

    private static final Logger log = LoggerFactory.getLogger(IsakhanKhangeldiFileController.class);
    private final Path fileStorageLocation = Paths.get("uploads").toAbsolutePath().normalize();

    public IsakhanKhangeldiFileController() {
        try {
            Files.createDirectories(this.fileStorageLocation);
            log.info("Upload directory created: {}", this.fileStorageLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload directory");
        }
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            log.info("File uploaded: {}", fileName);
            return "File uploaded successfully: " + fileName;
        } catch (IOException e) {
            log.error("Failed to upload file", e);
            throw new RuntimeException("Could not upload file");
        }
    }

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists()) {
                throw new RuntimeException("File not found: " + fileName);
            }

            String contentType = Files.probeContentType(filePath);
            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            log.info("File downloaded: {}", fileName);
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (IOException e) {
            log.error("Failed to download file", e);
            throw new RuntimeException("Could not download file");
        }
    }

    @GetMapping("/list")
    public String[] listFiles() {
        File[] files = this.fileStorageLocation.toFile().listFiles();
        if (files == null) return new String[0];
        return Arrays.stream(files)
                .map(File::getName)
                .toArray(String[]::new);
    }
}