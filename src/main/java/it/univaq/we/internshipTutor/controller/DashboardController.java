package it.univaq.we.internshipTutor.controller;

import it.univaq.we.internshipTutor.service.CompanyService;
import it.univaq.we.internshipTutor.service.InternshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class DashboardController {

    @Autowired
    CompanyService companyService;

    @Autowired
    InternshipService internshipService;



    @RequestMapping(value = {"/dashboard/admin"}, method = RequestMethod.GET)
    public String renderAdminDashboard() {
        return "index";
    }

}
