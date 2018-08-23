package it.univaq.we.internshipTutor.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class DownloadController {

    @Value("${spring.file.path.images}")
    private String imagesPath;

    @Value("${spring.file.path.docs}")
    private String docsPath;

    private static final String APPLICATION_PDF = "application/pdf";


    public @ResponseBody void download(HttpServletResponse response, String fileName) throws IOException {

        File file = getFile(fileName);
        InputStream in = new FileInputStream(file);

        response.setContentType(APPLICATION_PDF);
        response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
        response.setHeader("Content-Length", String.valueOf(file.length()));
        FileCopyUtils.copy(in, response.getOutputStream());
    }

    @RequestMapping(value = "/download/agreement/{fileName}", method = RequestMethod.GET, produces = APPLICATION_PDF)
    public @ResponseBody void downloadAgreement(HttpServletResponse response, @PathVariable(value = "fileName") String fileName) throws IOException {
        //TODO check permissions (only admin and the company that is logged and owns this agreement file)
        download(response, fileName);
    }

    @RequestMapping(value = "/download/report/{fileName}", method = RequestMethod.GET, produces = APPLICATION_PDF)
    public @ResponseBody void downloadReport(HttpServletResponse response, @PathVariable(value = "fileName") String fileName) throws IOException {
        //TODO check permissions (admin, company that provides the internship and student that have done the internship)
        download(response, fileName);
    }

    @RequestMapping(value = "/download/project/{fileName}", method = RequestMethod.GET, produces = APPLICATION_PDF)
    public @ResponseBody void downloadProject(HttpServletResponse response, @PathVariable(value = "fileName") String fileName) throws IOException {
        //TODO check permissions (admin, company that provides the internship and student that have done the internship)
        download(response, fileName);
    }

    private File getFile(String fileName) throws FileNotFoundException {
        File file = new File(docsPath + fileName);
        if (!file.exists()){
            throw new FileNotFoundException("file with path: " + docsPath + " was not found.");
        }
        return file;
    }

}
