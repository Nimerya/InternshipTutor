package it.univaq.we.internshipTutor.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@Service
public class FileUploadService implements IFileUploadService {


    @Value("${spring.file.path.images}")
    private String imagesPath;

    @Value("${spring.file.path.docs}")
    private String docsPath;


    @Override
    public String uploadImage(MultipartFile image, String fileName) throws Exception{

        if (image != null && image.getSize() > 0) {

            InputStream is = image.getInputStream();
            // image format check
            if (ImageIO.read(is) != null) {
                is.close();

                InputStream is2 = image.getInputStream();
                File savedImage = File.createTempFile(fileName, "", new File(imagesPath));
                Files.copy(is2, savedImage.toPath(), StandardCopyOption.REPLACE_EXISTING);

                return savedImage.getName();
            } else {
                throw new Exception("Uploaded file is not an image");
            }
        } else {
            return "default.jpg";
        }
    }
}
