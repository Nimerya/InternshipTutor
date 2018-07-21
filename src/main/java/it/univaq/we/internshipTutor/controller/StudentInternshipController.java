package it.univaq.we.internshipTutor.controller;

import it.univaq.we.internshipTutor.model.*;
import it.univaq.we.internshipTutor.service.InternshipService;
import it.univaq.we.internshipTutor.service.ProfessorService;
import it.univaq.we.internshipTutor.service.StudentInternshipService;
import it.univaq.we.internshipTutor.service.StudentService;
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

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static it.univaq.we.internshipTutor.model.Popup.WAR_MSG_EN;
import static it.univaq.we.internshipTutor.model.Popup.WAR_MSG_EN_DEL;
import static it.univaq.we.internshipTutor.model.Popup.WAR_MSG_EN_SAVE;

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


    @RequestMapping(value={"/admin/create/studentinternship"}, method = RequestMethod.POST)
    public String doCreate(@Valid @ModelAttribute("studentinternship") StudentInternship studentinternship, BindingResult result, RedirectAttributes redirectAttributes) {

        if(result.hasErrors()) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.studentinternship", result);
            redirectAttributes.addFlashAttribute("studentinternship", studentinternship);
            return "redirect:/admin/create/studentinternship";
        }
        if((studentinternship.getAccepted() && studentinternship.getRejected()) ||
                (studentinternship.getRejected() && studentinternship.getCompleted()) ||
                (!studentinternship.getAccepted() && studentinternship.getCompleted() )){

            redirectAttributes.addFlashAttribute("popup", new Popup("warning", "There are inconsistencies with the options."));
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.studentinternship", result);
            redirectAttributes.addFlashAttribute("studentinternship", studentinternship);
            return "redirect:/admin/create/studentinternship";
        }

        try{
            // else perform the insertion
            studentinternshipService.save(studentinternship);
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN_SAVE));
            return "redirect:/admin/create/studentinternship";
        }


        // add success message in the model
        redirectAttributes.addFlashAttribute("popup", new Popup());

        return "redirect:/admin/create/studentinternship";
    }

    @RequestMapping(value={"/admin/update/studentinternship"}, method = RequestMethod.POST)
    public String doUpdate(@Valid @ModelAttribute("studentinternship") StudentInternship studentinternship, BindingResult result, RedirectAttributes redirectAttributes) {

        if(result.hasErrors()) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.studentinternship", result);
            redirectAttributes.addFlashAttribute("studentinternship", studentinternship);
            return "redirect:/admin/update/studentinternship/" + studentinternship.getId();
        }

        if((studentinternship.getAccepted() && studentinternship.getRejected()) ||
                (studentinternship.getRejected() && studentinternship.getCompleted()) ||
                (!studentinternship.getAccepted() && studentinternship.getCompleted() )){

            redirectAttributes.addFlashAttribute("popup", new Popup("warning", "There are inconsistencies with the options."));
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.studentinternship", result);
            redirectAttributes.addFlashAttribute("studentinternship", studentinternship);
            return "redirect:/admin/update/studentinternship"+studentinternship.getId();
        }

        try{
            // else perform the insertion
            studentinternshipService.save(studentinternship);
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN_SAVE));
            return "redirect:/admin/update/studentinternship/" + studentinternship.getId();
        }

        // add success message in the model
        redirectAttributes.addFlashAttribute("popup", new Popup());

        return "redirect:/admin/update/studentinternship/" + studentinternship.getId();
    }

    @RequestMapping(value={"/admin/delete/studentinternship/{id}"}, method = RequestMethod.POST)
    public String doDelete(ModelMap model, @PathVariable(value = "id") Long id, RedirectAttributes redirectAttributes) {

        if (id == null || id < 0) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            // add error message in the model
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            return "redirect:/admin/update/studentinternship/" + id;
        }

        try{
            studentinternshipService.deleteStudentInternshipById(id);
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN_DEL));
            redirectAttributes.addFlashAttribute("studentinternship", studentinternshipService.findStudentInternshipById(id));
            return "redirect:/admin/update/studentinternship/" + id;
        }

        // add success message in the model
        redirectAttributes.addFlashAttribute("popup", new Popup());
        return "redirect:/admin/create/studentinternship";
    }


    @RequestMapping(value={"/admin/create/studentinternship"}, method = RequestMethod.GET)
    public String renderCreate(ModelMap model, Pageable pageable) {

        if(!model.containsAttribute("studentinternship")){
            model.addAttribute("studentinternship", new StudentInternship(UUID.randomUUID()));
        }

        Page<StudentInternship> studentinternships = studentinternshipService.findAll(pageable);
        PageWrapper<StudentInternship> page = new PageWrapper<>(studentinternships, "/admin/create/studentinternship");
        model.addAttribute("studentinternships", page.getContent());
        model.addAttribute("page", page);

        List<Professor> professors = professorService.findAll();
        model.addAttribute("professors", professors);

        List<Student> students = studentService.findAll();
        model.addAttribute("students", students);

        List<Internship> internships = internshipService.findAll();
        model.addAttribute("internships", internships);

        return "studentinternship_create";
    }

    @RequestMapping(value={"/admin/update/studentinternship/{id}"}, method = RequestMethod.GET)
    public String renderUpdate(ModelMap model, Pageable pageable, @PathVariable(value = "id") Long id) {


        if(!model.containsAttribute("studentinternship")){
            StudentInternship d = studentinternshipService.findStudentInternshipById(id);
            model.addAttribute("studentinternship", d);
        }

        Page<StudentInternship> studentinternships = studentinternshipService.findAll(pageable);
        PageWrapper<StudentInternship> page = new PageWrapper<>(studentinternships, "/admin/update/studentinternship/"+id);
        model.addAttribute("studentinternships", page.getContent());
        model.addAttribute("page", page);

        List<Professor> professors = professorService.findAll();
        model.addAttribute("professors", professors);

        List<Student> students = studentService.findAll();
        model.addAttribute("students", students);

        List<Internship> internships = internshipService.findAll();
        model.addAttribute("internships", internships);

        return "studentinternship_update";
    }


    @RequestMapping(value = {"/admin/report/studentinternships"}, method = RequestMethod.GET)
    public String renderReport(ModelMap model, Pageable pageable) {

        Page<StudentInternship> studentinternships = studentinternshipService.findAll(pageable);
        PageWrapper<StudentInternship> page = new PageWrapper<>(studentinternships, "/admin/report/studentinternships");
        model.addAttribute("collection", page.getContent());
        model.addAttribute("page", page);
        model.addAttribute("nameS", "studentinternship");
        model.addAttribute("nameP", "Student - Internships");

        return "report";
    }

}
