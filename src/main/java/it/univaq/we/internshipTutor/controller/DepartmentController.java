package it.univaq.we.internshipTutor.controller;

import it.univaq.we.internshipTutor.model.Department;
import it.univaq.we.internshipTutor.model.PageWrapper;
import it.univaq.we.internshipTutor.model.Popup;
import it.univaq.we.internshipTutor.service.DepartmentService;
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
import java.util.UUID;

@Controller
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @RequestMapping(value={"/admin/create/department"}, method = RequestMethod.POST)
    public String doCreate(@Valid @ModelAttribute("department") Department department, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            // add error message in the model as flash attribute
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.department", result);
            redirectAttributes.addFlashAttribute("department", department);

            return "redirect:/admin/create/department";
        }

        try{
            departmentService.save(department);
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN_SAVE));
            return "redirect:/admin/create/department";
        }


        // add success message in the model as flash attribute
        redirectAttributes.addFlashAttribute("popup", new Popup());

        // render Create form
        return "redirect:/admin/create/department";
    }


    @RequestMapping(value={"/admin/update/department"}, method = RequestMethod.POST)
    public String doUpdate(@Valid @ModelAttribute("department") Department department, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.department", result);
            redirectAttributes.addFlashAttribute("department", department);
            return "redirect:/admin/update/department/" + department.getId();
        }

        try{
            departmentService.save(department);
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN_SAVE));
            return "redirect:/admin/update/department/" + department.getId();
        }

        // add success message in the model
        redirectAttributes.addFlashAttribute("popup", new Popup());
        // render Update form
        return "redirect:/admin/update/department/" + department.getId();
    }


    @RequestMapping(value={"/admin/delete/department/{id}"}, method = RequestMethod.POST)
    public String doDelete(@PathVariable(value = "id") Long id, RedirectAttributes redirectAttributes) {
        if (id == null || id < 0) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            // add error message in the model
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN_DEL));
            return "redirect:/admin/update/department/" + id;
        }

        try{
            departmentService.deleteDepartmentById(id);
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN_DEL));
            redirectAttributes.addFlashAttribute("department", departmentService.findDepartmentById(id));
            return "redirect:/admin/update/department/" + id;
        }

        // add success message in the model
        redirectAttributes.addFlashAttribute("popup", new Popup());
        return "redirect:/admin/create/department";
    }


    @RequestMapping(value={"/admin/create/department"}, method = RequestMethod.GET)
    public String renderCreate(ModelMap model, Pageable pageable) {

        if(!model.containsAttribute("department")){
            model.addAttribute("department", new Department(UUID.randomUUID()));
        }
        Page<Department> departments = departmentService.findAll(pageable);
        PageWrapper<Department> page = new PageWrapper<>(departments, "/admin/create/department");
        model.addAttribute("departments", page.getContent());
        model.addAttribute("page", page);

        return "department_create";
    }


    @RequestMapping(value={"/admin/update/department/{id}"}, method = RequestMethod.GET)
    public String renderUpdate(ModelMap model, Pageable pageable, @PathVariable(value = "id") Long id) {

        Department d = departmentService.findDepartmentById(id);

        if(!model.containsAttribute("department")){
            model.addAttribute("department", d);
        }

        Page<Department> departments = departmentService.findAll(pageable);
        PageWrapper<Department> page = new PageWrapper<>(departments, "/admin/update/department/"+id);
        model.addAttribute("departments", page.getContent());
        model.addAttribute("page", page);

        return "department_update";
    }

    @RequestMapping(value = {"/admin/report/departments"}, method = RequestMethod.GET)
    public String renderReport(ModelMap model, Pageable pageable) {

        Page<Department> departments = departmentService.findAll(pageable);
        PageWrapper<Department> page = new PageWrapper<>(departments, "/admin/report/departments");
        model.addAttribute("collection", page.getContent());
        model.addAttribute("page", page);
        model.addAttribute("nameS", "department");
        model.addAttribute("nameP", "Departments");

        return "report";
    }

}
