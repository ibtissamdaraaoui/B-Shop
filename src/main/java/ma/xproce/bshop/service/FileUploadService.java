package ma.xproce.bshop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service


public class FileUploadService implements FileUploadManager {

        private final Path uploadDir;
        private static final Logger logger = LoggerFactory.getLogger(FileUploadService.class);

        public FileUploadService(@Value("${file.upload-dir:C:\\Users\\HP\\Desktop\\pdf}") String uploadDir) {
            this.uploadDir = Paths.get(uploadDir).toAbsolutePath().normalize();
            try {
                Files.createDirectories(this.uploadDir);
            } catch (IOException e) {
                throw new RuntimeException("Could not create upload directory!", e);
            }
        }

        @Override
        public String uploadFile(MultipartFile file) {
            String fileName = StringUtils.cleanPath(UUID.randomUUID().toString() + "-" + file.getOriginalFilename());
            try {
                if (fileName.contains("..")) {
                    throw new RuntimeException("Invalid path sequence in file name: " + fileName);
                }
                Path filePath = this.uploadDir.resolve(fileName);
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                return fileName;
            } catch (IOException e) {
                logger.error("Error uploading file", e);
                throw new RuntimeException("Failed to upload file: " + fileName, e);
            }
        }

        @Override
        public Resource loadFileAsResource(String fileName) {
            try {
                Path filePath = this.uploadDir.resolve(fileName).normalize();
                Resource resource = new UrlResource(filePath.toUri());
                if (resource.exists() && resource.isReadable()) {
                    return resource;
                } else {
                    throw new FileNotFoundException("File not found or not readable: " + fileName);
                }
            } catch (MalformedURLException | FileNotFoundException ex) {
                throw new RuntimeException("File not found or invalid path: " + fileName, ex);
            }
        }
}
