package it.univaq.we.internshipTutor.controller;

import it.univaq.we.internshipTutor.model.Role;
import it.univaq.we.internshipTutor.model.StudentInternship;
import it.univaq.we.internshipTutor.model.User;
import it.univaq.we.internshipTutor.service.FileDownloadService;
import it.univaq.we.internshipTutor.service.StudentInternshipService;
import it.univaq.we.internshipTutor.service.UserService;
import netscape.security.ForbiddenTargetException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.AccessDeniedException;
import java.security.Principal;
import java.util.List;

@Controller
public class DownloadController {

    @Autowired
    FileDownloadService fileDownloadService;

    @Autowired
    UserService userService;

    @Autowired
    StudentInternshipService studentInternshipService;

    @RequestMapping(value = "/download/agreement/{fileName}", method = RequestMethod.GET, produces = "application/pdf")
    public void downloadAgreement(HttpServletResponse response, @PathVariable(value = "fileName") String fileName, Principal principal) throws IOException {
        User user = userService.findUserByEmail(principal.getName());

        Role userRole = user.getRole();

        switch(userRole.getName()){
            case "ROLE_ADMIN":
                fileDownloadService.download(response, fileName);
                break;
            case "ROLE_COMPANY":
                String agreement = user.getCompany().getAgreement();
                if(agreement != null && agreement.equals(fileName)){
                    fileDownloadService.download(response, fileName);
                } else {
                    throw new org.springframework.security.access.AccessDeniedException("download 403");
                }
                break;
             default:
                 throw new org.springframework.security.access.AccessDeniedException("download 403");
        }

    }

    @RequestMapping(value = "/download/finalreport/{fileName}", method = RequestMethod.GET, produces = "application/pdf")
    public void downloadFinalReport(HttpServletResponse response, @PathVariable(value = "fileName") String fileName, Principal principal) throws IOException {

        User user = userService.findUserByEmail(principal.getName());
        Role userRole = user.getRole();
        List<StudentInternship> internships;
        boolean found;

        switch(userRole.getName()){
            case "ROLE_ADMIN":
                fileDownloadService.download(response, fileName);
                break;
            case "ROLE_COMPANY":
                internships = studentInternshipService.completedInternshipsByCompany(user.getCompany());
                found = false;
                for(StudentInternship internship : internships){
                    String finalReport = internship.getFinalReport();
                    if(finalReport != null && finalReport.equals(fileName)){
                        fileDownloadService.download(response, fileName);
                        found = true;
                        break;
                    }
                }

                if(!found){
                    throw new org.springframework.security.access.AccessDeniedException("download 403");
                }
                break;
            case "ROLE_STUDENT":
                internships = studentInternshipService.completedInternshipsByStudent(user.getStudent());
                found = false;
                for(StudentInternship internship : internships){
                    String finalReport = internship.getFinalReport();
                    if(finalReport != null && finalReport.equals(fileName)){
                        fileDownloadService.download(response, fileName);
                        found = true;
                        break;
                    }
                }

                if(!found){
                    throw new org.springframework.security.access.AccessDeniedException("download 403");
                }
                break;
            default:
                throw new org.springframework.security.access.AccessDeniedException("download 403");
        }
    }

    @RequestMapping(value = "/download/trainingproject/{fileName}", method = RequestMethod.GET, produces = "application/pdf")
    public void downloadTrainingProject(HttpServletResponse response, @PathVariable(value = "fileName") String fileName, Principal principal) throws IOException {

        User user = userService.findUserByEmail(principal.getName());
        Role userRole = user.getRole();
        List<StudentInternship> internships;
        boolean found;

        switch (userRole.getName()) {
            case "ROLE_ADMIN":
                fileDownloadService.download(response, fileName);
                break;
            case "ROLE_COMPANY":
                internships = studentInternshipService.ongoingInternshipsByCompany(user.getCompany());
                internships.addAll(studentInternshipService.completedInternshipsByCompany(user.getCompany()));

                found = false;
                for (StudentInternship internship : internships) {
                    String trainingProject = internship.getTrainingProject();
                    if (trainingProject != null && trainingProject.equals(fileName)) {
                        fileDownloadService.download(response, fileName);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    throw new org.springframework.security.access.AccessDeniedException("download 403");
                }
                break;
            case "ROLE_STUDENT":
                internships = studentInternshipService.ongoingInternshipsByStudent(user.getStudent());
                internships.addAll(studentInternshipService.completedInternshipsByStudent(user.getStudent()));

                found = false;
                for (StudentInternship internship : internships) {
                    String trainingProject = internship.getTrainingProject();
                    if (trainingProject != null && trainingProject.equals(fileName)) {
                        fileDownloadService.download(response, fileName);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    throw new org.springframework.security.access.AccessDeniedException("download 403");
                }
                break;
            default:
                throw new org.springframework.security.access.AccessDeniedException("download 403");
        }
    }

}
