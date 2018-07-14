package it.univaq.we.internshipTutor.controller;

import it.univaq.we.internshipTutor.model.Student;
import it.univaq.we.internshipTutor.model.Degree;
import it.univaq.we.internshipTutor.model.Popup;
import it.univaq.we.internshipTutor.service.StudentService;
import it.univaq.we.internshipTutor.service.DegreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    DegreeService degreeService;

    @RequestMapping(value={"/create/student"}, method = RequestMethod.POST)
    public String doCreate(@Valid @ModelAttribute("student") Student student, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            System.out.println("#####     Bindingresult errors:     #####");
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors ) {
                System.out.println (error.getObjectName() + " - " + error.getDefaultMessage());
            }
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            // add error message in the model as flash attribute
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.student", result);
            redirectAttributes.addFlashAttribute("student", student);

            return "redirect:/create/student";
        }

        try{
            studentService.save(student);
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            return "redirect:/create/student";
        }


        // add success message in the model as flash attribute
        redirectAttributes.addFlashAttribute("popup", new Popup());

        // render Create form
        return "redirect:/create/student";
    }


    @RequestMapping(value={"/update/student"}, method = RequestMethod.POST)
    public String doUpdate(@Valid @ModelAttribute("student") Student student, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.student", result);
            redirectAttributes.addFlashAttribute("student", student);
            return "redirect:/update/student/" + student.getId();
        }

        try{
            studentService.save(student);
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            return "redirect:/update/student/" + student.getId();
        }

        // add success message in the model
        redirectAttributes.addFlashAttribute("popup", new Popup());
        // render Update form
        return "redirect:/update/student/" + student.getId();
    }


    @RequestMapping(value={"/delete/student/{id}"}, method = RequestMethod.POST)
    public String doDelete(@PathVariable(value = "id") Long id, RedirectAttributes redirectAttributes) {
        if (id == null || id < 0) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            // add error message in the model
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            return "redirect:/update/student/" + id;
        }

        try{
            studentService.deleteStudentById(id);
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            return "redirect:/update/student/" + id;
        }

        // add success message in the model
        redirectAttributes.addFlashAttribute("popup", new Popup());
        return "redirect:/create/student";
    }


    @RequestMapping(value={"/create/student"}, method = RequestMethod.GET)
    public String renderCreate(ModelMap model) {

        if(!model.containsAttribute("student")){
            model.addAttribute("student", new Student(UUID.randomUUID()));
        }
        List<Student> students = studentService.findAll();
        model.addAttribute("students", students);

        List<Degree> degrees = degreeService.findAll();
        model.addAttribute("degrees", degrees);
        return "student_create";
    }


    @RequestMapping(value={"/update/student/{id}"}, method = RequestMethod.GET)
    public String renderUpdate(ModelMap model, @PathVariable(value = "id") Long id) {

        Student p = studentService.findStudentById(id);

        if(!model.containsAttribute("student")){
            model.addAttribute("student", p);
        }

        List<Student> students = studentService.findAll();
        model.addAttribute("students", students);

        List<Degree> degrees = degreeService.findAll();
        model.addAttribute("degrees", degrees);

        return "student_update";
    }


}
