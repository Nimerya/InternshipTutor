package it.univaq.we.internshipTutor.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileOutputStream;
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

    /**
     * converts a multipart file to file and checks if it is a pdf file
     * @param file multipart input file to check
     * @return true or false
     */
    @Override
    public Boolean isPdf(MultipartFile file){
        try{
            File convFile = new File(file.getOriginalFilename());
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();
            PDDocument.load(convFile);
            Files.deleteIfExists(convFile.toPath());
        }catch (Exception e){
            return false;
        }
        return true;
    }

}
