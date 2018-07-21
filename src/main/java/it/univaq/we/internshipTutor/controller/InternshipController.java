package it.univaq.we.internshipTutor.controller;


import it.univaq.we.internshipTutor.model.*;
import it.univaq.we.internshipTutor.service.CompanyService;
import it.univaq.we.internshipTutor.service.InternshipService;
import it.univaq.we.internshipTutor.service.StudentInternshipService;
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
public class InternshipController {

    @Autowired
    InternshipService internshipService;

    @Autowired
    CompanyService companyService;

    @Autowired
    StudentInternshipService studentInternshipService;

    @RequestMapping(value={"/admin/create/internship"}, method = RequestMethod.POST)
    public String doCreate(@Valid @ModelAttribute("internship") Internship internship, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            // add error message in the model as flash attribute
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.internship", result);
            redirectAttributes.addFlashAttribute("internship", internship);

            return "redirect:/admin/create/internship";
        }

        try{
            internshipService.save(internship);
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN_SAVE));
            return "redirect:/admin/create/internship";
        }


        // add success message in the model as flash attribute
        redirectAttributes.addFlashAttribute("popup", new Popup());

        // render Create form
        return "redirect:/admin/create/internship";
    }



    @RequestMapping(value={"/admin/update/internship"}, method = RequestMethod.POST)
    public String doUpdate(@Valid @ModelAttribute("internship") Internship internship, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.internship", result);
            redirectAttributes.addFlashAttribute("internship", internship);
            return "redirect:/admin/update/internship/" + internship.getId();
        }

        try{
            internshipService.save(internship);
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN_SAVE));
            return "redirect:/admin/update/internship/" + internship.getId();
        }

        // add success message in the model
        redirectAttributes.addFlashAttribute("popup", new Popup());
        // render Update form
        return "redirect:/admin/update/internship/" + internship.getId();
    }



    @RequestMapping(value={"/admin/delete/internship/{id}"}, method = RequestMethod.POST)
    public String doDelete(@PathVariable(value = "id") Long id, RedirectAttributes redirectAttributes) {
        if (id == null || id < 0) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            // add error message in the model
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            return "redirect:/admin/update/internship/" + id;
        }

        try{
            internshipService.deleteInternshipById(id);
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN_DEL));
            redirectAttributes.addFlashAttribute("internship", internshipService.findInternshipById(id));
            return "redirect:/admin/update/internship/" + id;
        }

        // add success message in the model
        redirectAttributes.addFlashAttribute("popup", new Popup());
        return "redirect:/admin/create/internship";
    }

    @RequestMapping(value={"/company/delete/internship/{internshipId}"}, method = RequestMethod.POST)
    public String doDeleteByCompany(@PathVariable(value = "internshipId") Long internshipId,
                                    RedirectAttributes redirectAttributes) {
        // TODO delete internship by company
        return "redirect:/index";
    }

    @RequestMapping(value={"/company/update/internship"}, method = RequestMethod.POST)
    public String doUpdateByCompany(@Valid @ModelAttribute("internship") Internship internship, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.internship", result);
            redirectAttributes.addFlashAttribute("internship", internship);
            return "redirect:/company/update/internship/" + internship.getId();
        }

        //TODO check that the compnay is updating is own internship
        //TODO force the values of "active" and "company" to be the same as they were before the update

        try{
            internshipService.save(internship);
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN_SAVE));
            return "redirect:/company/update/internship/" + internship.getId();
        }

        // add success message in the model
        redirectAttributes.addFlashAttribute("popup", new Popup());
        // render Update form
        return "redirect:/company/update/internship/" + internship.getId();
    }

    @RequestMapping(value={"/company/update/internship/{id}"}, method = RequestMethod.GET)
    public String renderUpdateByCompany(ModelMap model, Pageable pageable, @PathVariable(value = "id") Long id) {

        Internship i = internshipService.findInternshipById(id);

        if(!model.containsAttribute("internship")){
            model.addAttribute("internship", i);
        }

        Page<StudentInternship> studentinternships = studentInternshipService.findCandidatesByInternship(i);
        PageWrapper<StudentInternship> page = new PageWrapper<>(studentinternships, "/company/update/internship/"+id);
        model.addAttribute("studentinternships", page.getContent());
        model.addAttribute("page", page);

        return "internship_update_by_company";
    }


    @RequestMapping(value={"/admin/create/internship"}, method = RequestMethod.GET)
    public String renderCreate(ModelMap model, Pageable pageable) {

        if(!model.containsAttribute("internship")){
            model.addAttribute("internship", new Internship(UUID.randomUUID()));
        }
        Page<Internship> internships = internshipService.findAll(pageable);
        PageWrapper<Internship> page = new PageWrapper<>(internships, "/admin/create/internship");
        model.addAttribute("internships", page.getContent());
        model.addAttribute("page", page);

        List<Company> companies = companyService.findAll();
        model.addAttribute("companies", companies);

        return "internship_create";
    }


    @RequestMapping(value={"/admin/update/internship/{id}"}, method = RequestMethod.GET)
    public String renderUpdate(ModelMap model, Pageable pageable, @PathVariable(value = "id") Long id) {

        Internship i = internshipService.findInternshipById(id);

        if(!model.containsAttribute("internship")){
            model.addAttribute("internship", i);
        }

        Page<Internship> internships = internshipService.findAll(pageable);
        PageWrapper<Internship> page = new PageWrapper<>(internships, "/admin/update/internship/"+id);
        model.addAttribute("internships", page.getContent());
        model.addAttribute("page", page);

        List<Company> companies = companyService.findAll();
        model.addAttribute("companies", companies);

        return "internship_update";
    }


    @RequestMapping(value = {"/admin/report/internships"}, method = RequestMethod.GET)
    public String renderReport(ModelMap model, Pageable pageable) {

        Page<Internship> internships = internshipService.findAll(pageable);
        PageWrapper<Internship> page = new PageWrapper<>(internships, "/admin/report/internships");
        model.addAttribute("collection", page.getContent());
        model.addAttribute("page", page);
        model.addAttribute("nameS", "internship");
        model.addAttribute("nameP", "Internships");

        return "report";
    }

}
