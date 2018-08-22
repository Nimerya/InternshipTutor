package it.univaq.we.internshipTutor.service;

import org.springframework.web.multipart.MultipartFile;

public interface IFileUploadService {

    String uploadImage(MultipartFile image, String fileName) throws Exception;

    String uploadPdf(MultipartFile pdf, String fileName) throws Exception;

    Boolean isPdf(MultipartFile file);

}
