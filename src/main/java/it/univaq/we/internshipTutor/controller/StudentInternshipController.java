package it.univaq.we.internshipTutor.controller;

import it.univaq.we.internshipTutor.model.*;
import it.univaq.we.internshipTutor.service.InternshipService;
import it.univaq.we.internshipTutor.service.ProfessorService;
import it.univaq.we.internshipTutor.service.StudentInternshipService;
import it.univaq.we.internshipTutor.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static it.univaq.we.internshipTutor.model.Popup.WAR_MSG_EN;

@Controller
public class StudentInternshipController {

    @Autowired
    StudentInternshipService studentinternshipService;

    @Autowired
    StudentService studentService;

    @Autowired
    InternshipService internshipService;

    @Autowired
    ProfessorService professorService;


    @RequestMapping(value={"/create/studentinternship"}, method = RequestMethod.POST)
    public String doCreate(@Valid @ModelAttribute("studentinternship") StudentInternship studentinternship, BindingResult result, RedirectAttributes redirectAttributes) {

        if(result.hasErrors()) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.studentinternship", result);
            redirectAttributes.addFlashAttribute("studentinternship", studentinternship);
            return "redirect:/create/studentinternship";
        }

        try{
            // else perform the insertion
            studentinternshipService.save(studentinternship);
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            return "redirect:/create/studentinternship";
        }


        // add success message in the model
        redirectAttributes.addFlashAttribute("popup", new Popup());

        return "redirect:/create/studentinternship";
    }

    @RequestMapping(value={"/update/studentinternship"}, method = RequestMethod.POST)
    public String doUpdate(@Valid @ModelAttribute("studentinternship") StudentInternship studentinternship, BindingResult result, RedirectAttributes redirectAttributes) {

        if(result.hasErrors()) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.studentinternship", result);
            redirectAttributes.addFlashAttribute("studentinternship", studentinternship);
            return "redirect:/update/studentinternship/" + studentinternship.getId();
        }

        // else perform the insertion
        studentinternshipService.save(studentinternship);

        // add success message in the model
        redirectAttributes.addFlashAttribute("popup", new Popup());

        return "redirect:/update/studentinternship/" + studentinternship.getId();
    }

    @RequestMapping(value={"/delete/studentinternship/{id}"}, method = RequestMethod.POST)
    public String doDelete(ModelMap model, @PathVariable(value = "id") Long id, RedirectAttributes redirectAttributes) {

        if (id == null || id < 0) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            // add error message in the model
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            return "redirect:/update/studentinternship/" + id;
        }

        // else perform the remove
        studentinternshipService.deleteStudentInternshipById(id);

        // add success message in the model
        redirectAttributes.addFlashAttribute("popup", new Popup());
        return "redirect:/create/studentinternship";
    }


    @RequestMapping(value={"/create/studentinternship"}, method = RequestMethod.GET)
    public String renderCreate(ModelMap model) {

        if(!model.containsAttribute("studentinternship")){
            model.addAttribute("studentinternship", new StudentInternship(UUID.randomUUID()));
        }

        List<StudentInternship> studentinternships = studentinternshipService.findAll();
        model.addAttribute("studentinternships", studentinternships);

        List<Professor> professors = professorService.findAll();
        model.addAttribute("professors", professors);

        List<Student> students = studentService.findAll();
        model.addAttribute("students", students);

        List<Internship> internships = internshipService.findAll();
        model.addAttribute("internships", internships);

        return "studentinternship_create";
    }

    @RequestMapping(value={"/update/studentinternship/{id}"}, method = RequestMethod.GET)
    public String renderUpdate(ModelMap model, @PathVariable(value = "id") Long id) {


        if(!model.containsAttribute("studentinternship")){
            StudentInternship d = studentinternshipService.findStudentInternshipById(id);
            model.addAttribute("studentinternship", d);
        }

        List<StudentInternship> studentinternships = studentinternshipService.findAll();
        model.addAttribute("studentinternships", studentinternships);

        List<Professor> professors = professorService.findAll();
        model.addAttribute("professors", professors);

        List<Student> students = studentService.findAll();
        model.addAttribute("students", students);

        List<Internship> internships = internshipService.findAll();
        model.addAttribute("internships", internships);

        return "studentinternship_update";
    }

}
