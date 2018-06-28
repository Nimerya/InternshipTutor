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
public class FileUploadService {


    @Value("${spring.file.path.images}")
    private String imagesPath;

    @Value("${spring.file.path.docs}")
    private String docsPath;


    public String uploadImage(MultipartFile image, String fileName){

        System.out.println("kjgcjcgjc"+imagesPath);

        if(image != null && image.getSize() > 0){
            try {
                InputStream is = image.getInputStream();
                // image format check
                if (ImageIO.read(is) != null){
                    is.close();

                    InputStream is2 = image.getInputStream();
                    File savedImage = File.createTempFile(fileName, "", new File(imagesPath));
                    Files.copy(is2, savedImage.toPath(), StandardCopyOption.REPLACE_EXISTING);

                    return savedImage.getName();
                }else{
                    return "default.png";
                }
            } catch (IOException e) {
                e.printStackTrace();
                return "default.png";
            }
        }else{
            return "default.png";
        }

    }

}
