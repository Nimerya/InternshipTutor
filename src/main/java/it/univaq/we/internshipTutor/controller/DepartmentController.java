package it.univaq.we.internshipTutor.controller;

import it.univaq.we.internshipTutor.model.Department;
import it.univaq.we.internshipTutor.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
            redirectAttributes.addFlashAttribute("message", "Something Went Wrong, Try Again!");
            redirectAttributes.addFlashAttribute("type", "warning");
            return "redirect:/create/department";
        }

        // else perform the insertion
        departmentService.save(department);

        // add success message in the model as flash attribute
        redirectAttributes.addFlashAttribute("message", "Created Successfully!");
        redirectAttributes.addFlashAttribute("type", "success");

        // render Create form
        return "redirect:/create/department";
    }


    @RequestMapping(value={"/update/department"}, method = RequestMethod.POST)
    public String doUpdate(@Valid @ModelAttribute("department") Department department, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            redirectAttributes.addFlashAttribute("message", "Something Went Wrong. Try Again!");
            redirectAttributes.addFlashAttribute("type", "warning");
            return "redirect:/update/department/" + department.getId();
        }

        // else perform the update
        departmentService.save(department);

        // add success message in the model
        redirectAttributes.addFlashAttribute("message", "Updated Successfully");
        redirectAttributes.addFlashAttribute("type", "success");

        // render Update form
        return "redirect:/update/department/" + department.getId();
    }


    @RequestMapping(value={"/delete/department/{id}"}, method = RequestMethod.POST)
    public String doDelete(@PathVariable(value = "id") Long id, RedirectAttributes redirectAttributes) {
        if (id == null || id < 0) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            // add error message in the model
            redirectAttributes.addFlashAttribute("message", "Something Went Wrong, Try Again!");
            redirectAttributes.addFlashAttribute("type", "warning");
            return "redirect:/update/department/" + id;
        }

        // else perform the remove
        departmentService.deleteDepartmentById(id);

        // add success message in the model
        redirectAttributes.addFlashAttribute("message", "Deleted Successfully");
        redirectAttributes.addFlashAttribute("type", "success");
        return "redirect:/create/department";
    }


    @RequestMapping(value={"/create/department"}, method = RequestMethod.GET)
    public String renderCreate(ModelMap model) {

        model.addAttribute("department", new Department(UUID.randomUUID()));
        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);
        return "department:create";
    }


    @RequestMapping(value={"/update/department/{id}"}, method = RequestMethod.GET)
    public String renderUpdate(ModelMap model, @PathVariable(value = "id") Long id) {

        Department d = departmentService.findDepartmentById(id);

        List<Department> departments = departmentService.findAll();

        model.addAttribute("department", d);
        model.addAttribute("departments", departments);

        return "department:update";
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("language", "english");
    }

}
