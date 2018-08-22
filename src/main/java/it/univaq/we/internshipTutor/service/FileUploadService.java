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


    @Override
    public String uploadPdf(MultipartFile pdf, String fileName) throws Exception{

        if (pdf != null && pdf.getSize() > 0) {

            if (isPdf(pdf)) {

                InputStream is = pdf.getInputStream();
                File savedPdf = File.createTempFile(fileName, "", new File(docsPath));
                Files.copy(is, savedPdf.toPath(), StandardCopyOption.REPLACE_EXISTING);

                return savedPdf.getName();
            } else {
                throw new Exception("Uploaded file is not a pdf");
            }
        } else {
            throw new Exception("Uploaded file is empty");
        }
    }

    @Override
    public Boolean isPdf(MultipartFile file){
        byte[] data;

        try {
            data = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        if (data != null && data.length > 4 &&
                data[0] == 0x25 && // %
                data[1] == 0x50 && // P
                data[2] == 0x44 && // D
                data[3] == 0x46 && // F
                data[4] == 0x2D) { // -

            // version 1.3 file terminator
            if (data[5] == 0x31 && data[6] == 0x2E && data[7] == 0x33 &&
                    data[data.length - 7] == 0x25 && // %
                    data[data.length - 6] == 0x25 && // %
                    data[data.length - 5] == 0x45 && // E
                    data[data.length - 4] == 0x4F && // O
                    data[data.length - 3] == 0x46 && // F
                    data[data.length - 2] == 0x20 && // SPACE
                    data[data.length - 1] == 0x0A) { // EOL
                return true;
            }

            // version 1.3 file terminator
            if (data[5] == 0x31 && data[6] == 0x2E && data[7] == 0x34 &&
                    data[data.length - 6] == 0x25 && // %
                    data[data.length - 5] == 0x25 && // %
                    data[data.length - 4] == 0x45 && // E
                    data[data.length - 3] == 0x4F && // O
                    data[data.length - 2] == 0x46 && // F
                    data[data.length - 1] == 0x0A) { // EOL
                return true;
            }
        }
        return false;
    }

}
