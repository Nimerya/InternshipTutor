package it.univaq.we.internshipTutor.controller;

import it.univaq.we.internshipTutor.model.Degree;
import it.univaq.we.internshipTutor.model.Department;
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

    @RequestMapping(value={"/create/degree"}, method = RequestMethod.POST)
    public String doCreate(@Valid @ModelAttribute("degree") Degree degree, BindingResult result, RedirectAttributes redirectAttributes) {

        if(result.hasErrors()) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.degree", result);
            redirectAttributes.addFlashAttribute("degree", degree);
            return "redirect:/create/degree";
        }

        try{
            // else perform the insertion
            degreeService.save(degree);
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            return "redirect:/create/degree";
        }


        // add success message in the model
        redirectAttributes.addFlashAttribute("popup", new Popup());

        return "redirect:/create/degree";
    }

    @RequestMapping(value={"/update/degree"}, method = RequestMethod.POST)
    public String doUpdate(@Valid @ModelAttribute("degree") Degree degree, BindingResult result, RedirectAttributes redirectAttributes) {

        if(result.hasErrors()) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.degree", result);
            redirectAttributes.addFlashAttribute("degree", degree);
            return "redirect:/update/degree/" + degree.getId();
        }

        // else perform the insertion
        degreeService.save(degree);

        // add success message in the model
        redirectAttributes.addFlashAttribute("popup", new Popup());

        return "redirect:/update/degree/" + degree.getId();
    }

    @RequestMapping(value={"/delete/degree/{id}"}, method = RequestMethod.POST)
    public String doDelete(ModelMap model, @PathVariable(value = "id") Long id, RedirectAttributes redirectAttributes) {

        if (id == null || id < 0) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            // add error message in the model
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            return "redirect:/update/degree/" + id;
        }

        // else perform the remove
        degreeService.deleteDegreeById(id);

        // add success message in the model
        redirectAttributes.addFlashAttribute("popup", new Popup());
        return "redirect:/create/degree";
    }


    @RequestMapping(value={"/create/degree"}, method = RequestMethod.GET)
    public String renderCreate(ModelMap model, Pageable pageable) {

        if(!model.containsAttribute("degree")){
            model.addAttribute("degree", new Degree(UUID.randomUUID()));
        }

        Page<Degree> degrees = degreeService.findAll(pageable);
        PageWrapper<Degree> page = new PageWrapper<>(degrees, "/create/degree");
        model.addAttribute("degrees", page.getContent());
        model.addAttribute("page", page);

        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);

        return "degree_create";
    }

    @RequestMapping(value={"/update/degree/{id}"}, method = RequestMethod.GET)
    public String renderUpdate(ModelMap model, Pageable pageable, @PathVariable(value = "id") Long id) {


        if(!model.containsAttribute("degree")){
            Degree d = degreeService.findDegreeById(id);
            model.addAttribute("degree", d);
        }

        Page<Degree> degrees = degreeService.findAll(pageable);
        PageWrapper<Degree> page = new PageWrapper<>(degrees, "/update/degree/"+id);
        model.addAttribute("degrees", page.getContent());
        model.addAttribute("page", page);

        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);

        return "degree_update";
    }

}
