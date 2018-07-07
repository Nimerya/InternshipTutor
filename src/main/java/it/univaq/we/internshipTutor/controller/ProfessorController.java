package it.univaq.we.internshipTutor.controller;

import it.univaq.we.internshipTutor.model.Professor;
import it.univaq.we.internshipTutor.model.Popup;
import it.univaq.we.internshipTutor.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
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
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
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
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
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
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            return "redirect:/update/professor/" + id;
        }

        // add success message in the model
        redirectAttributes.addFlashAttribute("popup", new Popup());
        return "redirect:/create/professor";
    }


    @RequestMapping(value={"/create/professor"}, method = RequestMethod.GET)
    public String renderCreate(ModelMap model) {

        if(!model.containsAttribute("professor")){
            model.addAttribute("professor", new Professor(UUID.randomUUID()));
        }
        List<Professor> professors = professorService.findAll();
        model.addAttribute("professors", professors);

        return "professor_create";
    }


    @RequestMapping(value={"/update/professor/{id}"}, method = RequestMethod.GET)
    public String renderUpdate(ModelMap model, @PathVariable(value = "id") Long id) {

        Professor p = professorService.findProfessorById(id);

        if(!model.containsAttribute("professor")){
            model.addAttribute("professor", p);
        }

        List<Professor> professors = professorService.findAll();
        model.addAttribute("professors", professors);

        return "professor_update";
    }


}
