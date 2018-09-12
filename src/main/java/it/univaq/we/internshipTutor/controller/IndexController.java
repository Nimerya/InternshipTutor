package it.univaq.we.internshipTutor.controller;

import it.univaq.we.internshipTutor.model.Company;
import it.univaq.we.internshipTutor.model.Internship;
import it.univaq.we.internshipTutor.model.Popup;
import it.univaq.we.internshipTutor.model.User;
import it.univaq.we.internshipTutor.service.InternshipService;
import it.univaq.we.internshipTutor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.expression.Lists;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    InternshipService internshipService;

    @Autowired
    UserService userService;

    @RequestMapping(value={"/", "/index", "/index.html"}, method = RequestMethod.GET)
    public String index(ModelMap model) {

        Map<Long, User> map = new HashMap<>();

        List<Internship> internships = internshipService.findActiveInternships();

        int limit = Math.min(3, internships.size());
        internships = internships.subList(0, limit);
        
        for(Internship internship : internships){
            map.put(internship.getId(), userService.findUserByCompany(internship.getCompany().getId()));
        }

        model.addAttribute("internships", internships);
        model.addAttribute("map", map);

        return "index";
    }

}
