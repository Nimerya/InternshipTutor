package it.univaq.we.internshipTutor.controller;

import it.univaq.we.internshipTutor.model.*;
import it.univaq.we.internshipTutor.service.CompanyService;
import it.univaq.we.internshipTutor.service.DegreeService;
import it.univaq.we.internshipTutor.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static it.univaq.we.internshipTutor.model.Popup.*;

@Controller
public class AffiliatedCompaniesController {

    @Autowired
    CompanyService companyService;

    @RequestMapping(value = {"/affiliatedCompanies"}, method = RequestMethod.GET)
    public String renderReport(ModelMap model, Pageable pageable) {

        Map<Long, Double> averages = companyService.findCompaniesAverage();
        Page<Company> affiliatedCompanies = companyService.findCompaniesByActiveTrue(pageable);
        PageWrapper<Company> page = new PageWrapper<>(affiliatedCompanies, "/affiliatedCompanies");
        model.addAttribute("affiliatedCompanies", affiliatedCompanies.getContent());
        model.addAttribute("page", page);
        model.addAttribute("averages", averages);

        return "affiliated_companies";
    }

}
