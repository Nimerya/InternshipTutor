package it.univaq.we.internshipTutor.controller;

import it.univaq.we.internshipTutor.model.Company;
import it.univaq.we.internshipTutor.model.IProfessorInternshipCountProjection;
import it.univaq.we.internshipTutor.model.Student;
import it.univaq.we.internshipTutor.model.User;
import it.univaq.we.internshipTutor.service.CompanyService;
import it.univaq.we.internshipTutor.service.ProfessorService;
import it.univaq.we.internshipTutor.service.StudentService;
import it.univaq.we.internshipTutor.service.UserService;
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

    @Autowired
    UserService userService;

    @Autowired
    CompanyService companyService;

    @Autowired
    StudentService studentService;

    @RequestMapping(value = {"/dashboard/admin"}, method = RequestMethod.GET)
    public String renderAdminDashboard(ModelMap model) {

        model.addAttribute("userType",  "Admin");

        List<User> users = userService.findAll();
        int numUsers = users.size();
        model.addAttribute("numUsers", numUsers);

        List<Company> companies = companyService.findAll();
        int numCompanies = companies.size();
        model.addAttribute("numCompanies", numCompanies);

        List<Student> students = studentService.findAll();
        int numStudents = students.size();
        model.addAttribute("numStudents", numStudents);

        model.addAttribute("numAdmins", numUsers - numCompanies - numStudents);

        List<IProfessorInternshipCountProjection> mostRequestedProfessors = professorService.mostRequestedProfessors(10);
        model.addAttribute("mostRequestedProfessors", mostRequestedProfessors);

        return "dashboard_admin";
    }

}
