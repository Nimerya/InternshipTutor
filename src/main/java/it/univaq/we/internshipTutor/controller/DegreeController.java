package it.univaq.we.internshipTutor.controller;

import it.univaq.we.internshipTutor.model.Degree;
import it.univaq.we.internshipTutor.model.Department;
import it.univaq.we.internshipTutor.model.PageWrapper;
import it.univaq.we.internshipTutor.model.Popup;
import it.univaq.we.internshipTutor.service.DegreeService;
import it.univaq.we.internshipTutor.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import static it.univaq.we.internshipTutor.model.Popup.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
public class DegreeController {

    @Autowired
    DegreeService degreeService;

    @Autowired
    DepartmentService departmentService;

    @RequestMapping(value={"/admin/create/degree"}, method = RequestMethod.POST)
    public String doCreate(@Valid @ModelAttribute("degree") Degree degree, BindingResult result, RedirectAttributes redirectAttributes) {

        if(result.hasErrors()) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.degree", result);
            redirectAttributes.addFlashAttribute("degree", degree);
            return "redirect:/admin/create/degree";
        }

        try{
            // else perform the insertion
            degreeService.save(degree);
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN_SAVE));
            return "redirect:/admin/create/degree";
        }


        // add success message in the model
        redirectAttributes.addFlashAttribute("popup", new Popup());

        return "redirect:/admin/create/degree";
    }

    @RequestMapping(value={"/admin/update/degree"}, method = RequestMethod.POST)
    public String doUpdate(@Valid @ModelAttribute("degree") Degree degree, BindingResult result, RedirectAttributes redirectAttributes) {

        if(result.hasErrors()) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.degree", result);
            redirectAttributes.addFlashAttribute("degree", degree);
            return "redirect:/admin/update/degree/" + degree.getId();
        }

        try{
            degreeService.save(degree);
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN_SAVE));
            return "redirect:/admin/update/degree/" + degree.getId();
        }

        // add success message in the model
        redirectAttributes.addFlashAttribute("popup", new Popup());

        return "redirect:/admin/update/degree/" + degree.getId();
    }

    @RequestMapping(value={"/admin/delete/degree/{id}"}, method = RequestMethod.POST)
    public String doDelete(ModelMap model, @PathVariable(value = "id") Long id, RedirectAttributes redirectAttributes) {

        if (id == null || id < 0) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            // add error message in the model
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            return "redirect:/admin/update/degree/" + id;
        }

        try{
            degreeService.deleteDegreeById(id);
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN_DEL));
            redirectAttributes.addFlashAttribute("company", degreeService.findDegreeById(id));
            return "redirect:/admin/update/degree/" + id;
        }

        // add success message in the model
        redirectAttributes.addFlashAttribute("popup", new Popup());
        return "redirect:/admin/create/degree";
    }


    @RequestMapping(value={"/admin/create/degree"}, method = RequestMethod.GET)
    public String renderCreate(ModelMap model, Pageable pageable) {

        if(!model.containsAttribute("degree")){
            model.addAttribute("degree", new Degree(UUID.randomUUID()));
        }

        Page<Degree> degrees = degreeService.findAll(pageable);
        PageWrapper<Degree> page = new PageWrapper<>(degrees, "/admin/create/degree");
        model.addAttribute("degrees", page.getContent());
        model.addAttribute("page", page);

        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);

        return "degree_create";
    }

    @RequestMapping(value={"/admin/update/degree/{id}"}, method = RequestMethod.GET)
    public String renderUpdate(ModelMap model, Pageable pageable, @PathVariable(value = "id") Long id) {


        if(!model.containsAttribute("degree")){
            Degree d = degreeService.findDegreeById(id);
            model.addAttribute("degree", d);
        }

        Page<Degree> degrees = degreeService.findAll(pageable);
        PageWrapper<Degree> page = new PageWrapper<>(degrees, "/admin/update/degree/"+id);
        model.addAttribute("degrees", page.getContent());
        model.addAttribute("page", page);

        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);

        return "degree_update";
    }

    @RequestMapping(value = {"/admin/report/degrees"}, method = RequestMethod.GET)
    public String renderReport(ModelMap model, Pageable pageable) {

        Page<Degree> degrees = degreeService.findAll(pageable);
        PageWrapper<Degree> page = new PageWrapper<>(degrees, "/admin/report/degrees");
        model.addAttribute("collection", page.getContent());
        model.addAttribute("page", page);
        model.addAttribute("nameS", "degree");
        model.addAttribute("nameP", "Degrees");

        return "report";
    }

}
