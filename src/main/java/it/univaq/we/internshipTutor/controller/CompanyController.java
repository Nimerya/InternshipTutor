package it.univaq.we.internshipTutor.controller;


import it.univaq.we.internshipTutor.model.PageWrapper;
import it.univaq.we.internshipTutor.model.User;
import it.univaq.we.internshipTutor.model.Company;
import it.univaq.we.internshipTutor.model.Popup;
import it.univaq.we.internshipTutor.service.CompanyService;
import it.univaq.we.internshipTutor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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


    @RequestMapping(value = {"/create/company"}, method = RequestMethod.POST)
    public String doCreate(@Valid @ModelAttribute("company") Company company,
                           BindingResult result,
                           RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.company", result);
            redirectAttributes.addFlashAttribute("company", company);
            return "redirect:/create/company";
        }
        try{
            // else perform the insertion
            companyService.save(company);
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN_SAVE));
            return "redirect:/create/company";
        }

        // add success message in the model
        redirectAttributes.addFlashAttribute("popup", new Popup("success", "Operation Completed Successfully!"));

        return "redirect:/create/company";
    }

    @RequestMapping(value = {"/update/company"}, method = RequestMethod.POST)
    public String doUpdate(@Valid @ModelAttribute("company") Company company,
                           BindingResult result,
                           RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.company", result);
            redirectAttributes.addFlashAttribute("company", company);
            return "redirect:/update/company/" + company.getId();
        }

        try{
            companyService.save(company);
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN_SAVE));
            return "redirect:/update/company/" + company.getId();
        }

        return "redirect:/update/company/" + company.getId();
    }

    @RequestMapping(value = {"/delete/company/{id}"}, method = RequestMethod.POST)
    public String doDelete(ModelMap model, @PathVariable(value = "id") Long id, RedirectAttributes redirectAttributes) {

        if (id == null || id < 0) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            // add error message in the model
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            return "redirect:/update/company/" + id;
        }

        try{
            // else perform the insertion
            companyService.deleteCompanyById(id);
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN_DEL));
            return "redirect:/create/company";
        }

        // add success message in the model
        redirectAttributes.addFlashAttribute("popup", new Popup());
        return "redirect:/create/company";
    }


    @RequestMapping(value = {"/create/company"}, method = RequestMethod.GET)
    public String renderCreate(ModelMap model, Pageable pageable) {

        if (!model.containsAttribute("company")) {
            model.addAttribute("company", new Company(UUID.randomUUID()));
        }

        Page<Company> companies = companyService.findAll(pageable);
        PageWrapper<Company> page = new PageWrapper<>(companies, "/create/company");
        model.addAttribute("companies", page.getContent());
        model.addAttribute("page", page);

        return "company_create";
    }

    @RequestMapping(value = {"/update/company/{id}"}, method = RequestMethod.GET)
    public String renderUpdate(ModelMap model, Pageable pageable, @PathVariable(value = "id") Long id) {


        if (!model.containsAttribute("company")) {
            Company c = companyService.findCompanyById(id);
            model.addAttribute("company", c);
        }
        Page<Company> companies = companyService.findAll(pageable);
        PageWrapper<Company> page = new PageWrapper<>(companies, "/update/company/"+id);
        model.addAttribute("companies", page.getContent());
        model.addAttribute("page", page);

        /*List<User> users = userService.findAll();
        model.addAttribute("users", users);*/


        return "company_update";
    }

}
