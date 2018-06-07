package it.univaq.we.internshipTutor.controller;

import it.univaq.we.internshipTutor.model.Degree;
import it.univaq.we.internshipTutor.model.Department;
import it.univaq.we.internshipTutor.service.DegreeService;
import it.univaq.we.internshipTutor.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.UUID;

@Controller
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @RequestMapping(value={"/create/department"}, method = RequestMethod.POST)
    public String create(@ModelAttribute("department") Department department, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }

        Department d = departmentService.save(department);

        // TODO handle redirection and return value

        return "depTestForm";
    }


    @RequestMapping(value={"/create/department"}, method = RequestMethod.GET)
    public ModelAndView createForm() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("department", new Department(UUID.randomUUID()));
        return new ModelAndView("depTestForm", map);
    }


}
