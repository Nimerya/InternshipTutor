package it.univaq.we.internshipTutor.controller;

import it.univaq.we.internshipTutor.model.Company;
import it.univaq.we.internshipTutor.model.Popup;
import it.univaq.we.internshipTutor.model.StudentInternship;
import it.univaq.we.internshipTutor.service.CompanyService;
import it.univaq.we.internshipTutor.service.FileDownloadService;
import it.univaq.we.internshipTutor.service.PdfBuilderService;
import it.univaq.we.internshipTutor.service.StudentInternshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
public class PdfBuilderController {

    @Autowired
    PdfBuilderService pdfBuilderService;

    @Autowired
    CompanyService companyService;

    @Autowired
    StudentInternshipService studentInternshipService;

    @Autowired
    FileDownloadService fileDownloadService;

    @RequestMapping(value = "/admin/build/agreement/{companyId}", method = RequestMethod.GET)
    public void buildAgreement(HttpServletResponse response,
                               @PathVariable(value = "companyId") Long companyId,
                               RedirectAttributes redirectAttributes){
        try{
            Company company = companyService.findCompanyById(companyId);
            String fileName = pdfBuilderService.buildAgreement(company);
            fileDownloadService.download(response,fileName);
            pdfBuilderService.clean(fileName);
        } catch(Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", Popup.WAR_MSG_EN));
        }
    }


    @RequestMapping(value = "/company/build/trainingproject/{studentInternshipId}", method = RequestMethod.GET)
    public void buildTrainingProject(HttpServletResponse response,
                                       @PathVariable(value = "studentInternshipId") Long studentInternshipId,
                                       RedirectAttributes redirectAttributes){
        try{
            StudentInternship studentInternship = studentInternshipService.findStudentInternshipById(studentInternshipId);
            String fileName = pdfBuilderService.buildTrainingProject(studentInternship);
            fileDownloadService.download(response,fileName);
            pdfBuilderService.clean(fileName);
        } catch(Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", Popup.WAR_MSG_EN));
        }
    }

    @RequestMapping(value = "/company/build/finalreport/{studentInternshipId}", method = RequestMethod.GET)
    public void buildFinalReport(HttpServletResponse response,
                                     @PathVariable(value = "studentInternshipId") Long studentInternshipId,
                                     RedirectAttributes redirectAttributes){
        try{
            StudentInternship studentInternship = studentInternshipService.findStudentInternshipById(studentInternshipId);
            String fileName = pdfBuilderService.buildFinalReport(studentInternship);
            fileDownloadService.download(response,fileName);
            pdfBuilderService.clean(fileName);
        } catch(Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", Popup.WAR_MSG_EN));
        }
    }

}
