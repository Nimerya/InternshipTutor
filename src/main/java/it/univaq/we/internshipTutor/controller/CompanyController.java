package it.univaq.we.internshipTutor.controller;


import it.univaq.we.internshipTutor.model.PageWrapper;
import it.univaq.we.internshipTutor.model.Company;
import it.univaq.we.internshipTutor.model.Popup;
import it.univaq.we.internshipTutor.service.CompanyService;
import it.univaq.we.internshipTutor.service.FileUploadService;
import it.univaq.we.internshipTutor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import static it.univaq.we.internshipTutor.model.Popup.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
public class CompanyController {

    @Autowired
    UserService userService;

    @Autowired
    CompanyService companyService;

    @Autowired
    FileUploadService fileUploadService;


    @RequestMapping(value = {"/admin/create/company"}, method = RequestMethod.POST)
    public String doCreate(@Valid @ModelAttribute("company") Company company,
                           BindingResult result,
                           RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.company", result);
            redirectAttributes.addFlashAttribute("company", company);
            return "redirect:/admin/create/company";
        }
        try{
            // else perform the insertion
            company.setActive(Boolean.FALSE);
            companyService.save(company);
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN_SAVE));
            return "redirect:/admin/create/company";
        }

        // add success message in the model
        redirectAttributes.addFlashAttribute("popup", new Popup());

        return "redirect:/admin/create/company";
    }

    @RequestMapping(value = {"/admin/update/company"}, method = RequestMethod.POST)
    public String doUpdate(@Valid @ModelAttribute("company") Company company,
                           BindingResult result,
                           RedirectAttributes redirectAttributes,
                           @RequestParam("agreementFile") MultipartFile agreementFile) {

        if (result.hasErrors()) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.company", result);
            redirectAttributes.addFlashAttribute("company", company);
            return "redirect:/admin/update/company/" + company.getId();
        }

        if(agreementFile != null && !agreementFile.isEmpty()){
            try{
                company.setAgreement(fileUploadService.uploadPdf(agreementFile, "agreement_"+company.getName()+'_'));
                company.setActive(Boolean.TRUE);
            }catch (Exception e){
                e.printStackTrace();
                redirectAttributes.addFlashAttribute("popup", new Popup("warning", "Something Went Wrong! Check the file you have uploaded"));
                return "redirect:/admin/update/company/" + company.getId();
            }
        }else{
            String oldAgreement = companyService.findCompanyById(company.getId()).getAgreement();
            company.setAgreement(oldAgreement);
        }

        if(company.getAgreement() == null){
            company.setActive(Boolean.FALSE);
        }

        try{
            companyService.save(company);
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN_SAVE));
            return "redirect:/admin/update/company/" + company.getId();
        }

        redirectAttributes.addFlashAttribute("popup", new Popup());
        return "redirect:/admin/update/company/" + company.getId();
    }

    @RequestMapping(value = {"/admin/delete/company/{id}"}, method = RequestMethod.POST)
    public String doDelete(ModelMap model, @PathVariable(value = "id") Long id, RedirectAttributes redirectAttributes) {

        if (id == null || id < 0) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            // add error message in the model
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            return "redirect:/admin/update/company/" + id;
        }

        try{
            // else perform the insertion
            companyService.deleteCompanyById(id);
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN_DEL));
            redirectAttributes.addFlashAttribute("company", companyService.findCompanyById(id));
            return "redirect:/admin/update/company/" + id;
        }

        // add success message in the model
        redirectAttributes.addFlashAttribute("popup", new Popup());
        return "redirect:/admin/create/company";
    }


    @RequestMapping(value = {"/admin/create/company"}, method = RequestMethod.GET)
    public String renderCreate(ModelMap model, Pageable pageable) {

        if (!model.containsAttribute("company")) {
            model.addAttribute("company", new Company(UUID.randomUUID()));
        }

        Page<Company> companies = companyService.findAll(pageable);
        PageWrapper<Company> page = new PageWrapper<>(companies, "/admin/create/company");
        model.addAttribute("companies", page.getContent());
        model.addAttribute("page", page);

        return "company_create";
    }

    @RequestMapping(value = {"/admin/update/company/{id}"}, method = RequestMethod.GET)
    public String renderUpdate(ModelMap model, Pageable pageable, @PathVariable(value = "id") Long id) {


        if (!model.containsAttribute("company")) {
            Company c = companyService.findCompanyById(id);
            model.addAttribute("company", c);
        }
        Page<Company> companies = companyService.findAll(pageable);
        PageWrapper<Company> page = new PageWrapper<>(companies, "/admin/update/company/"+id);
        model.addAttribute("companies", page.getContent());
        model.addAttribute("page", page);

        /*List<User> users = userService.findAll();
        model.addAttribute("users", users);*/


        return "company_update";
    }


    @RequestMapping(value = {"/admin/report/companies"}, method = RequestMethod.GET)
    public String renderReport(ModelMap model, Pageable pageable,
                               @RequestParam(value = "awaiting", required = false) Boolean awaiting) {

        Page<Company> companies;
        PageWrapper<Company> page;

        if (awaiting != null && awaiting){
            companies = companyService.findCompaniesByActiveFalse(pageable);
            page = new PageWrapper<>(companies, "/admin/report/companies?awaiting=true");
        }else{
            companies = companyService.findAll(pageable);
            page = new PageWrapper<>(companies, "/admin/report/companies");
        }
        model.addAttribute("collection", page.getContent());
        model.addAttribute("page", page);
        model.addAttribute("nameS", "company");
        model.addAttribute("nameP", "Companies");
        model.addAttribute("fileType", "Agreement");

        return "report_companies";
    }

}
