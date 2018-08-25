package it.univaq.we.internshipTutor.controller;

import it.univaq.we.internshipTutor.service.FileDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class DownloadController {

    @Autowired
    FileDownloadService fileDownloadService;

    @RequestMapping(value = "/download/agreement/{fileName}", method = RequestMethod.GET, produces = "application/pdf")
    public void downloadAgreement(HttpServletResponse response, @PathVariable(value = "fileName") String fileName) throws IOException {
        //TODO check permissions (only admin and the company that is logged and owns this agreement file)
        fileDownloadService.download(response, fileName);
    }

    @RequestMapping(value = "/download/report/{fileName}", method = RequestMethod.GET, produces = "application/pdf")
    public void downloadReport(HttpServletResponse response, @PathVariable(value = "fileName") String fileName) throws IOException {
        //TODO check permissions (admin, company that provides the internship and student that have done the internship)
        fileDownloadService.download(response, fileName);
    }

    @RequestMapping(value = "/download/trainingproject/{fileName}", method = RequestMethod.GET, produces = "application/pdf")
    public void downloadProject(HttpServletResponse response, @PathVariable(value = "fileName") String fileName) throws IOException {
        //TODO check permissions (admin, company that provides the internship and student that have done the internship)
        fileDownloadService.download(response, fileName);
    }


}
