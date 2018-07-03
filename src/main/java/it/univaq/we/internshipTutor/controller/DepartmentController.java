package it.univaq.we.internshipTutor.controller;

import it.univaq.we.internshipTutor.model.Department;
import it.univaq.we.internshipTutor.model.Popup;
import it.univaq.we.internshipTutor.service.DepartmentService;
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
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @RequestMapping(value={"/create/department"}, method = RequestMethod.POST)
    public String doCreate(@Valid @ModelAttribute("department") Department department, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            // add error message in the model as flash attribute
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.department", result);
            redirectAttributes.addFlashAttribute("department", department);

            return "redirect:/create/department";
        }

        try{
            departmentService.save(department);
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            return "redirect:/create/department";
        }


        // add success message in the model as flash attribute
        redirectAttributes.addFlashAttribute("popup", new Popup());

        // render Create form
        return "redirect:/create/department";
    }


    @RequestMapping(value={"/update/department"}, method = RequestMethod.POST)
    public String doUpdate(@Valid @ModelAttribute("department") Department department, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.department", result);
            redirectAttributes.addFlashAttribute("department", department);
            return "redirect:/update/department/" + department.getId();
        }

        try{
            departmentService.save(department);
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            return "redirect:/update/department/" + department.getId();
        }

        // add success message in the model
        redirectAttributes.addFlashAttribute("popup", new Popup());
        // render Update form
        return "redirect:/update/department/" + department.getId();
    }


    @RequestMapping(value={"/delete/department/{id}"}, method = RequestMethod.POST)
    public String doDelete(@PathVariable(value = "id") Long id, RedirectAttributes redirectAttributes) {
        if (id == null || id < 0) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            // add error message in the model
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            return "redirect:/update/department/" + id;
        }

        try{
            departmentService.deleteDepartmentById(id);
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            return "redirect:/update/department/" + id;
        }

        // add success message in the model
        redirectAttributes.addFlashAttribute("popup", new Popup());
        return "redirect:/create/department";
    }


    @RequestMapping(value={"/create/department"}, method = RequestMethod.GET)
    public String renderCreate(ModelMap model) {

        if(!model.containsAttribute("department")){
            model.addAttribute("department", new Department(UUID.randomUUID()));
        }
        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);

        return "department_create";
    }


    @RequestMapping(value={"/update/department/{id}"}, method = RequestMethod.GET)
    public String renderUpdate(ModelMap model, @PathVariable(value = "id") Long id) {

        Department d = departmentService.findDepartmentById(id);

        if(!model.containsAttribute("department")){
            model.addAttribute("department", d);
        }

        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);

        return "department_update";
    }

}
