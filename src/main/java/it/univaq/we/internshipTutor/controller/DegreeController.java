package it.univaq.we.internshipTutor.controller;

import it.univaq.we.internshipTutor.model.Degree;
import it.univaq.we.internshipTutor.service.DegreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DegreeController {

    @Autowired
    DegreeService degreeService;

    @RequestMapping(value={"/create/degree/"}, method = RequestMethod.POST)
    public String create(@ModelAttribute("degree") Degree degree, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }

        degreeService.save(degree);

        // TODO handle redirection and return value

        return "";
    }

}
