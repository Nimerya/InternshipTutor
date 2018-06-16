package it.univaq.we.internshipTutor.controller;

import it.univaq.we.internshipTutor.model.Department;
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

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Controller
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @RequestMapping(value={"/create/department"}, method = RequestMethod.POST)
    public String doCreate(@Valid @ModelAttribute("department") Department department, BindingResult result) {

        if (result.hasErrors()) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            return "department:create";
        }

        // else perform the insertion
        departmentService.save(department);

        //TODO handle redirect/success message
        return "redirect:/index";
    }

    @RequestMapping(value={"/update/department"}, method = RequestMethod.POST)
    public String doUpdate(@Valid @ModelAttribute("department") Department department, BindingResult result) {

        if (result.hasErrors()) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            return "department:update";
        }

        // else perform the update
        departmentService.save(department);

        //TODO handle redirect/success message
        return "redirect:/index";
    }

    @RequestMapping(value={"/delete/department/{id}"}, method = RequestMethod.POST)
    public String doDelete(ModelMap model, @PathVariable(value = "id") Long id) {

        if (id == null || id < 0) {
            model.addAttribute("error:id", "error:id");

            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            return "department:update";
        }

        // else perform the remove
        departmentService.deleteDepartmentById(id);

        //TODO handle redirect/success message
        return "redirect:/index";
    }


    @RequestMapping(value={"/create/department"}, method = RequestMethod.GET)
    public ModelAndView createForm() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("department", new Department(UUID.randomUUID()));
        return new ModelAndView("depTestForm", map);
    }

    @RequestMapping(value={"/update/department/{id}"}, method = RequestMethod.GET)
    public String renderUpdate(ModelMap model, @PathVariable(value = "id") Long id) {

        Department d = departmentService.findDepartmentById(id);

        List<Department> departments = departmentService.findAll();

        model.addAttribute("department", d);
        model.addAttribute("departments", departments);

        return "department:update";
    }

}
