package it.univaq.we.internshipTutor.service;

import org.springframework.web.multipart.MultipartFile;

public interface IFileUploadService {
    String uploadImage(MultipartFile image, String fileName) throws Exception;
}
