package ma.xproce.bshop.service;


import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadManager {
    String uploadFile(MultipartFile file);
    Resource loadFileAsResource(String fileName);
}