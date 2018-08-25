package it.univaq.we.internshipTutor.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Service
public class FileDownloadService implements IFileDownloadService{

    @Value("${spring.file.path.docs}")
    private String docsPath;

    @Override
    public void download(HttpServletResponse response, String fileName) throws IOException {
        File file = getFile(fileName);
        InputStream in = new FileInputStream(file);
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
        response.setHeader("Content-Length", String.valueOf(file.length()));
        FileCopyUtils.copy(in, response.getOutputStream());
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }

    @Override
    public File getFile(String fileName) throws FileNotFoundException {
        File file = new File(docsPath + fileName);
        if (!file.exists()){
            throw new FileNotFoundException("getFile() - file with path: " + file.getAbsolutePath() + " was not found.");
        }
        return file;
    }
}
