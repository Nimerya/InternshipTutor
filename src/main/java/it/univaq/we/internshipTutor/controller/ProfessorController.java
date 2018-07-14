package it.univaq.we.internshipTutor.controller;

import it.univaq.we.internshipTutor.model.Department;
import it.univaq.we.internshipTutor.model.PageWrapper;
import it.univaq.we.internshipTutor.model.Professor;
import it.univaq.we.internshipTutor.model.Popup;
import it.univaq.we.internshipTutor.service.DepartmentService;
import it.univaq.we.internshipTutor.service.ProfessorService;
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
public class ProfessorController {

    @Autowired
    ProfessorService professorService;

    @Autowired
    DepartmentService departmentService;

    @RequestMapping(value={"/create/professor"}, method = RequestMethod.POST)
    public String doCreate(@Valid @ModelAttribute("professor") Professor professor, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            // add error message in the model as flash attribute
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.professor", result);
            redirectAttributes.addFlashAttribute("professor", professor);

            return "redirect:/create/professor";
        }

        try{
            professorService.save(professor);
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN_SAVE));
            return "redirect:/create/professor";
        }


        // add success message in the model as flash attribute
        redirectAttributes.addFlashAttribute("popup", new Popup());

        // render Create form
        return "redirect:/create/professor";
    }


    @RequestMapping(value={"/update/professor"}, method = RequestMethod.POST)
    public String doUpdate(@Valid @ModelAttribute("professor") Professor professor, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.professor", result);
            redirectAttributes.addFlashAttribute("professor", professor);
            return "redirect:/update/professor/" + professor.getId();
        }

        try{
            professorService.save(professor);
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN_SAVE));
            return "redirect:/update/professor/" + professor.getId();
        }

        // add success message in the model
        redirectAttributes.addFlashAttribute("popup", new Popup());
        // render Update form
        return "redirect:/update/professor/" + professor.getId();
    }


    @RequestMapping(value={"/delete/professor/{id}"}, method = RequestMethod.POST)
    public String doDelete(@PathVariable(value = "id") Long id, RedirectAttributes redirectAttributes) {
        if (id == null || id < 0) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            // add error message in the model
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            return "redirect:/update/professor/" + id;
        }

        try{
            professorService.deleteProfessorById(id);
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN_DEL));
            return "redirect:/update/professor/" + id;
        }

        // add success message in the model
        redirectAttributes.addFlashAttribute("popup", new Popup());
        return "redirect:/create/professor";
    }


    @RequestMapping(value={"/create/professor"}, method = RequestMethod.GET)
    public String renderCreate(ModelMap model, Pageable pageable) {

        if(!model.containsAttribute("professor")){
            model.addAttribute("professor", new Professor(UUID.randomUUID()));
        }
        Page<Professor> professors = professorService.findAll(pageable);
        PageWrapper<Professor> page = new PageWrapper<>(professors, "/create/professor");
        model.addAttribute("professors", page.getContent());
        model.addAttribute("page", page);

        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);
        return "professor_create";
    }


    @RequestMapping(value={"/update/professor/{id}"}, method = RequestMethod.GET)
    public String renderUpdate(ModelMap model, Pageable pageable, @PathVariable(value = "id") Long id) {

        Professor p = professorService.findProfessorById(id);

        if(!model.containsAttribute("professor")){
            model.addAttribute("professor", p);
        }

        Page<Professor> professors = professorService.findAll(pageable);
        PageWrapper<Professor> page = new PageWrapper<>(professors, "/update/professor/"+id);
        model.addAttribute("professors", page.getContent());
        model.addAttribute("page", page);

        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);

        return "professor_update";
    }


    @RequestMapping(value = {"/report/professors"}, method = RequestMethod.GET)
    public String renderReport(ModelMap model, Pageable pageable) {

        Page<Professor> professors = professorService.findAll(pageable);
        PageWrapper<Professor> page = new PageWrapper<>(professors, "/report/professors");
        model.addAttribute("collection", page.getContent());
        model.addAttribute("page", page);
        model.addAttribute("nameS", "professor");
        model.addAttribute("nameP", "Professors");

        return "report";
    }


}
