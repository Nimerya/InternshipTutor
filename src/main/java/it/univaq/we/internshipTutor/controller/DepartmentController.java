package it.univaq.we.internshipTutor.controller;

import it.univaq.we.internshipTutor.model.Department;
import it.univaq.we.internshipTutor.model.Popup;
import it.univaq.we.internshipTutor.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Controller
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @RequestMapping(value={"/create/department"}, method = RequestMethod.POST)
    public String doCreate(@Valid @ModelAttribute("department") Department department, BindingResult result, HttpSession httpSession, ModelMap model) {

        if (result.hasErrors()) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            List<Department> departments = departmentService.findAll();
            model.addAttribute("departments", departments);

            model.addAttribute("popup", new Popup("WARNING"));
            return "department_create";
        }

        // else perform the insertion
        departmentService.save(department);

        httpSession.setAttribute("popup", new Popup());

        return "redirect:/create/department";
    }

    @RequestMapping(value={"/update/department"}, method = RequestMethod.POST)
    public String doUpdate(@Valid @ModelAttribute("department") Department department, BindingResult result, ModelMap model, HttpSession httpSession) {

        if (result.hasErrors()) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors

            List<Department> departments = departmentService.findAll();
            model.addAttribute("departments", departments);

            model.addAttribute("popup", new Popup("WARNING"));
            return "department_update";
        }

        // else perform the update
        departmentService.save(department);

        httpSession.setAttribute("popup", new Popup());
        return "redirect:/update/department/" + department.getId();
    }

    @RequestMapping(value={"/delete/department/{id}"}, method = RequestMethod.POST)
    public String doDelete(ModelMap model, @PathVariable(value = "id") Long id, HttpSession httpSession) {

        if (id == null || id < 0) {
            model.addAttribute("popup", new Popup("ERROR", "id"));

            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            return "department_update";
        }

        // else perform the remove
        departmentService.deleteDepartmentById(id);

        httpSession.setAttribute("popup", new Popup());
        return "redirect:/create/department";
    }


    @RequestMapping(value={"/create/department"}, method = RequestMethod.GET)
    public String renderCreate(ModelMap model, HttpSession httpSession) {

        model.addAttribute("department", new Department(UUID.randomUUID()));

        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);

        model.addAttribute("popup", httpSession.getAttribute("popup"));
        httpSession.removeAttribute("popup");

        return "department_create";
    }

    @RequestMapping(value={"/update/department/{id}"}, method = RequestMethod.GET)
    public String renderUpdate(ModelMap model, @PathVariable(value = "id") Long id, HttpSession httpSession) {

        Department d = departmentService.findDepartmentById(id);
        model.addAttribute("department", d);

        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);

        model.addAttribute("popup", httpSession.getAttribute("popup"));
        httpSession.removeAttribute("popup");

        return "department_update";
    }

}
