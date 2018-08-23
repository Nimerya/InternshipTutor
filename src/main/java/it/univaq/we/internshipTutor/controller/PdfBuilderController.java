package it.univaq.we.internshipTutor.controller;

import it.univaq.we.internshipTutor.model.Company;
import it.univaq.we.internshipTutor.model.Popup;
import it.univaq.we.internshipTutor.service.CompanyService;
import it.univaq.we.internshipTutor.service.FileDownloadService;
import it.univaq.we.internshipTutor.service.PdfBuilderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;

@Controller
public class PdfBuilderController {

    @Autowired
    PdfBuilderService pdfBuilderService;

    @Autowired
    CompanyService companyService;

    @Autowired
    FileDownloadService fileDownloadService;

    @RequestMapping(value = "/admin/build/agreement/{companyId}", method = RequestMethod.GET)
    public String buildAgreement(HttpServletResponse response, @PathVariable(value = "companyId") Long companyId, RedirectAttributes redirectAttributes){
        try{
            Company company = companyService.findCompanyById(companyId);
            String fileName = pdfBuilderService.builtAgreement(company);
            fileDownloadService.download(response,fileName);
            pdfBuilderService.clean(fileName);
        } catch(Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", Popup.WAR_MSG_EN));
        }
        return "redirect:/admin/update/company/" + companyId;

    }
}
