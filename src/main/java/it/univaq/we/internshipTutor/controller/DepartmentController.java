package it.univaq.we.internshipTutor.controller;

import it.univaq.we.internshipTutor.model.Degree;
import it.univaq.we.internshipTutor.model.Department;
import it.univaq.we.internshipTutor.service.DegreeService;
import it.univaq.we.internshipTutor.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
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
            return "depTestForm";
        }

        // else perform the insertion
        departmentService.save(department);

        //TODO handle redirect/success message
        return "redirect:/index";
    }


    @RequestMapping(value={"/create/department"}, method = RequestMethod.GET)
    public ModelAndView createForm() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("department", new Department(UUID.randomUUID()));
        return new ModelAndView("depTestForm", map);
    }


}
