package it.univaq.we.internshipTutor.controller;

import it.univaq.we.internshipTutor.model.Degree;
import it.univaq.we.internshipTutor.model.Department;
import it.univaq.we.internshipTutor.model.Popup;
import it.univaq.we.internshipTutor.service.DegreeService;
import it.univaq.we.internshipTutor.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
public class DegreeController {

    @Autowired
    DegreeService degreeService;

    @Autowired
    DepartmentService departmentService;

    @RequestMapping(value={"/create/degree"}, method = RequestMethod.POST)
    public String doCreate(@Valid @ModelAttribute("degree") Degree degree, BindingResult result, HttpSession httpSession, ModelMap model) {

        if (result.hasErrors()) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            List<Degree> degrees = degreeService.findAll();
            model.addAttribute("degrees", degrees);

            List<Department> departments = departmentService.findAll();
            model.addAttribute("departments", departments);

            model.addAttribute("popup", new Popup("WARNING"));
            return "degree_create";
        }

        // else perform the insertion
        degreeService.save(degree);

        httpSession.setAttribute("popup", new Popup());

        return "redirect:/create/degree";
    }

    @RequestMapping(value={"/update/degree"}, method = RequestMethod.POST)
    public String doUpdate(@Valid @ModelAttribute("degree") Degree degree, BindingResult result, ModelMap model, HttpSession httpSession) {

        if (result.hasErrors()) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors

            List<Degree> degrees = degreeService.findAll();
            model.addAttribute("degrees", degrees);

            List<Department> departments = departmentService.findAll();
            model.addAttribute("departments", departments);

            model.addAttribute("popup", new Popup("WARNING"));
            return "degree_update";
        }

        // else perform the update
        degreeService.save(degree);

        httpSession.setAttribute("popup", new Popup());
        return "redirect:/update/degree/" + degree.getId();
    }

    @RequestMapping(value={"/delete/degree/{id}"}, method = RequestMethod.POST)
    public String doDelete(ModelMap model, @PathVariable(value = "id") Long id, HttpSession httpSession) {

        if (id == null || id < 0) {
            model.addAttribute("popup", new Popup("ERROR", "id"));

            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            return "degree_update";
        }

        // else perform the remove
        degreeService.deleteDegreeById(id);

        httpSession.setAttribute("popup", new Popup());
        return "redirect:/create/degree";
    }


    @RequestMapping(value={"/create/degree"}, method = RequestMethod.GET)
    public String renderCreate(ModelMap model, HttpSession httpSession) {

        model.addAttribute("degree", new Degree(UUID.randomUUID()));

        List<Degree> degrees = degreeService.findAll();
        model.addAttribute("degrees", degrees);

        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);

        model.addAttribute("popup", httpSession.getAttribute("popup"));
        httpSession.removeAttribute("popup");

        return "degree_create";
    }

    @RequestMapping(value={"/update/degree/{id}"}, method = RequestMethod.GET)
    public String renderUpdate(ModelMap model, @PathVariable(value = "id") Long id, HttpSession httpSession) {

        Degree d = degreeService.findDegreeById(id);
        model.addAttribute("degree", d);

        List<Degree> degrees = degreeService.findAll();
        model.addAttribute("degrees", degrees);

        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);

        model.addAttribute("popup", httpSession.getAttribute("popup"));
        httpSession.removeAttribute("popup");

        return "degree_update";
    }

}
