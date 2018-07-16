package it.univaq.we.internshipTutor.controller;

import it.univaq.we.internshipTutor.model.IProfessorInternshipCountProjection;
import it.univaq.we.internshipTutor.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    ProfessorService professorService;

    @RequestMapping(value = {"/dashboard/admin"}, method = RequestMethod.GET)
    public String renderAdminDashboard(ModelMap model) {

        List<IProfessorInternshipCountProjection> mostRequestedProfessors = professorService.mostRequestedProfessors(10);
        for (IProfessorInternshipCountProjection pic : mostRequestedProfessors){
            System.out.println(pic.getEmail()+ " - " +pic.getCount());
        }

        return "dashboard_admin";
    }

}
