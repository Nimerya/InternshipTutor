package it.univaq.we.internshipTutor.service;

import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface IFileDownloadService {

    @ResponseBody void download(HttpServletResponse response, String fileName) throws IOException;

    File getFile(String fileName) throws FileNotFoundException;


}
