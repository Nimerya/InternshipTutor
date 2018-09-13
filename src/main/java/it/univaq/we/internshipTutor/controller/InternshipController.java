package it.univaq.we.internshipTutor.controller;


import it.univaq.we.internshipTutor.model.*;
import it.univaq.we.internshipTutor.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import static it.univaq.we.internshipTutor.model.Popup.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Collections;


@Controller
public class InternshipController {

    @Autowired
    InternshipService internshipService;

    @Autowired
    CompanyService companyService;

    @Autowired
    StudentInternshipService studentInternshipService;

    @Autowired
    UserService userService;

    @Autowired
    ProfessorService professorService;

    @Autowired
    StudentService studentService;

    @RequestMapping(value={"/admin/create/internship"}, method = RequestMethod.POST)
    public String doCreate(@Valid @ModelAttribute("internship") Internship internship, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            // add error message in the model as flash attribute
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.internship", result);
            redirectAttributes.addFlashAttribute("internship", internship);

            return "redirect:/admin/create/internship";
        }

        try{
            internshipService.save(internship);
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN_SAVE));
            return "redirect:/admin/create/internship";
        }


        // add success message in the model as flash attribute
        redirectAttributes.addFlashAttribute("popup", new Popup());

        // render Create form
        return "redirect:/admin/create/internship";
    }



    @RequestMapping(value={"/admin/update/internship"}, method = RequestMethod.POST)
    public String doUpdate(@Valid @ModelAttribute("internship") Internship internship, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.internship", result);
            redirectAttributes.addFlashAttribute("internship", internship);
            return "redirect:/admin/update/internship/" + internship.getId();
        }

        try{
            internshipService.save(internship);
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN_SAVE));
            return "redirect:/admin/update/internship/" + internship.getId();
        }

        // add success message in the model
        redirectAttributes.addFlashAttribute("popup", new Popup());
        // render Update form
        return "redirect:/admin/update/internship/" + internship.getId();
    }



    @RequestMapping(value={"/admin/delete/internship/{id}"}, method = RequestMethod.POST)
    public String doDelete(@PathVariable(value = "id") Long id, RedirectAttributes redirectAttributes) {
        if (id == null || id < 0) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            // add error message in the model
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            return "redirect:/admin/update/internship/" + id;
        }

        try{
            internshipService.deleteInternshipById(id);
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN_DEL));
            redirectAttributes.addFlashAttribute("internship", internshipService.findInternshipById(id));
            return "redirect:/admin/update/internship/" + id;
        }

        // add success message in the model
        redirectAttributes.addFlashAttribute("popup", new Popup());
        return "redirect:/admin/create/internship";
    }

    @RequestMapping(value={"/company/create/internship"}, method = RequestMethod.POST)
    public String doCreateByCompany(@Valid @ModelAttribute("internship") Internship internship, BindingResult result,
                                    RedirectAttributes redirectAttributes, Principal principal) {

        if (result.hasErrors()) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            // add error message in the model as flash attribute
            System.out.println("#####     Bindingresult errors:     #####");
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors ) {
                System.out.println (error.getField() + " - " + error.getDefaultMessage());
            }
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.internship", result);
            redirectAttributes.addFlashAttribute("internship", internship);

            return "redirect:/company/create/internship";
        }

        Company company = userService.findUserByEmail(principal.getName()).getCompany();

        internship.setCompany(company);

        try{
            internshipService.save(internship);
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN_SAVE));
            return "redirect:/company/create/internship";
        }


        // add success message in the model as flash attribute
        redirectAttributes.addFlashAttribute("popup", new Popup());

        // render Create form
        return "redirect:/company/create/internship";
    }


    @RequestMapping(value={"/company/delete/internship/{id}"}, method = RequestMethod.POST)
    public String doDeleteByCompany(@PathVariable(value = "id") Long id,
                                    RedirectAttributes redirectAttributes, Principal principal) {

        if (id == null || id < 0) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            // add error message in the model
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            return "redirect:/company/update/internship/" + id;
        }

        Company company = userService.findUserByEmail(principal.getName()).getCompany();

        // check that the company is deleting its own internship
        if(!(company.getId().equals(internshipService.findInternshipById(id).getCompany().getId()))){
            return "redirect:/error?code=403";
        }

        try{
            internshipService.deleteInternshipById(id);
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN_DEL));
            redirectAttributes.addFlashAttribute("internship", internshipService.findInternshipById(id));
            return "redirect:/company/update/internship/" + id;
        }

        // add success message in the model
        redirectAttributes.addFlashAttribute("popup", new Popup());
        return "redirect:/company/dashboard";

    }

    @RequestMapping(value={"/company/update/internship"}, method = RequestMethod.POST)
    public String doUpdateByCompany(@Valid @ModelAttribute("internship") Internship internship, BindingResult result,
                                    RedirectAttributes redirectAttributes, Principal principal) {

        if (result.hasErrors()) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.internship", result);
            redirectAttributes.addFlashAttribute("internship", internship);
            return "redirect:/company/update/internship/" + internship.getId();
        }

        Company company = userService.findUserByEmail(principal.getName()).getCompany();

        Internship oldInternship = internshipService.findInternshipById(internship.getId());

        // check that the company is updating its own internship
        if(!(company.getId().equals(oldInternship.getCompany().getId()))){
            return "redirect:/error?code=403";
        }

        if(!(company.getId().equals(internship.getCompany().getId()))){
            return "redirect:/error?code=403";
        }

        // check that the values of "active" and "company" are the same as they were before the update
        /*if(!(oldInternship.getActive().equals(internship.getActive()))){
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", "Cannot change Active attribute!"));
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.internship", result);
            redirectAttributes.addFlashAttribute("internship", internship);
            return "redirect:/company/update/internship/" + internship.getId();
        }*/

        if(!(oldInternship.getCompany().getId().equals(internship.getCompany().getId()))){
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.internship", result);
            redirectAttributes.addFlashAttribute("internship", internship);
            return "redirect:/company/update/internship/" + internship.getId();
        }

        try{
            internshipService.save(internship);
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN_SAVE));
            return "redirect:/company/update/internship/" + internship.getId();
        }

        // add success message in the model
        redirectAttributes.addFlashAttribute("popup", new Popup());
        // render Update form
        return "redirect:/company/update/internship/" + internship.getId();
    }


    @RequestMapping(value = {"/internship/{id}"}, method = RequestMethod.POST)
    public String doApplyInternship(@ModelAttribute("studentInternship") StudentInternship studentInternship, BindingResult result,
                                    @PathVariable(value = "id") Long id, RedirectAttributes redirectAttributes, Principal principal) {


        Student currStudent = userService.findUserByEmail(principal.getName()).getStudent();

        Internship currInternship = internshipService.findInternshipById(id);

        List<StudentInternship> internships = studentInternshipService.findStudentInternshipByStudentAndInternship(currStudent, currInternship);

        if(!internships.isEmpty()){
            // Student already applied for this internship
            redirectAttributes.addFlashAttribute("popup", new Popup("info", "You already applied for this Internship!"));
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.internship", result);
            redirectAttributes.addFlashAttribute("studentInternship", studentInternship);
            return "redirect:/internship/" + id;
        }

        // Insert the application
        studentInternship.setStudent(currStudent);
        studentInternship.setInternship(currInternship);
        studentInternship.setAccepted(new Boolean(false));
        studentInternship.setRejected(new Boolean(false));
        studentInternship.setCompleted(new Boolean(false));

        try{
            studentInternshipService.save(studentInternship);
            redirectAttributes.addFlashAttribute("popup", new Popup("success", "Application Sent Successfully!"));
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN_SAVE));
            return "redirect:/internship/" + id;
        }

        return "redirect:/internship/" + id;
    }


    @RequestMapping(value={"/company/create/internship"}, method = RequestMethod.GET)
    public String renderCreateByCompany(ModelMap model, Pageable pageable, Principal principal) {

        Company loggedCompany = userService.findUserByEmail(principal.getName()).getCompany();
        if(!loggedCompany.getActive()){
            return "redirect:/error?code=999";
        }

        if(!model.containsAttribute("internship")){
            Long userId = userService.findUserByEmail(principal.getName()).getId();
            Internship internship = new Internship(UUID.randomUUID());
            internship.setCompany(userService.findUserById(userId).getCompany());
            model.addAttribute("internship", internship);
        }

        Page<Internship> internships = internshipService.findAll(pageable);
        PageWrapper<Internship> page = new PageWrapper<>(internships, "/company/create/internship");
        model.addAttribute("internships", page.getContent());
        model.addAttribute("page", page);


        return "internship_create_by_company";
    }


    @RequestMapping(value={"/company/update/internship/{id}"}, method = RequestMethod.GET)
    public String renderUpdateByCompany(ModelMap model, Pageable pageable, @PathVariable(value = "id") Long id, Principal principal) {

        Company company = userService.findUserByEmail(principal.getName()).getCompany();

        // check that the company is updating its own internship
        if(!(company.getId().equals(internshipService.findInternshipById(id).getCompany().getId()))){
            return "redirect:/error?code=403";
        }

        Internship i = internshipService.findInternshipById(id);

        if(!model.containsAttribute("internship")){
            model.addAttribute("internship", i);
        }

        Page<StudentInternship> studentinternships = studentInternshipService.findCandidatesByInternship(pageable, i);
        PageWrapper<StudentInternship> page = new PageWrapper<>(studentinternships, "/company/update/internship/"+id);
        model.addAttribute("studentinternships", page.getContent());
        model.addAttribute("page", page);

        return "internship_update_by_company";
    }


    @RequestMapping(value={"/admin/create/internship"}, method = RequestMethod.GET)
    public String renderCreate(ModelMap model, Pageable pageable) {

        if(!model.containsAttribute("internship")){
            model.addAttribute("internship", new Internship(UUID.randomUUID()));
        }
        Page<Internship> internships = internshipService.findAll(pageable);
        PageWrapper<Internship> page = new PageWrapper<>(internships, "/admin/create/internship");
        model.addAttribute("internships", page.getContent());
        model.addAttribute("page", page);

        List<Company> companies = companyService.findAll();
        model.addAttribute("companies", companies);

        return "internship_create";
    }


    @RequestMapping(value={"/admin/update/internship/{id}"}, method = RequestMethod.GET)
    public String renderUpdate(ModelMap model, Pageable pageable, @PathVariable(value = "id") Long id) {

        Internship i = internshipService.findInternshipById(id);

        if(!model.containsAttribute("internship")){
            model.addAttribute("internship", i);
        }

        Page<Internship> internships = internshipService.findAll(pageable);
        PageWrapper<Internship> page = new PageWrapper<>(internships, "/admin/update/internship/"+id);
        model.addAttribute("internships", page.getContent());
        model.addAttribute("page", page);

        List<Company> companies = companyService.findAll();
        model.addAttribute("companies", companies);

        return "internship_update";
    }


    @RequestMapping(value = {"/admin/report/internships"}, method = RequestMethod.GET)
    public String renderReport(ModelMap model, Pageable pageable) {

        Page<Internship> internships = internshipService.findAll(pageable);
        PageWrapper<Internship> page = new PageWrapper<>(internships, "/admin/report/internships");
        model.addAttribute("collection", page.getContent());
        model.addAttribute("page", page);
        model.addAttribute("nameS", "internship");
        model.addAttribute("nameP", "Internships");

        return "report";
    }

    @RequestMapping(value = {"/internship/{id}"}, method = RequestMethod.GET)
    public String renderInternship(ModelMap model, @PathVariable(value = "id") Long id) {

        if(!model.containsAttribute("studentInternship")){
            model.addAttribute("studentInternship", new StudentInternship(UUID.randomUUID()));
        }
        List<Professor> professors = professorService.findAll();
        Internship internship = internshipService.findInternshipById(id);
        User userCompany = userService.findUserByCompany(internship.getCompany().getId());
        Company company = internship.getCompany();

        model.addAttribute("professors", professors);
        model.addAttribute("internship", internship);
        model.addAttribute("user", userCompany);
        model.addAttribute("company", company);

        return "internship";
    }

    @RequestMapping(value = {"/internships"}, method = RequestMethod.GET)
    public String renderInternships(ModelMap model, @RequestParam(value = "s", required = false) String query, Pageable pageable){

        Map<Long, User> map = new HashMap<>();
        Page<Internship> internships;

        if(query != null && query.length() > 0){
            internships = internshipService.findInternshipsByQuery(pageable, query);
        } else {
            internships = internshipService.findActiveInternships(pageable);
            query="";
        }

        for(Internship internship : internships){
            map.put(internship.getId(), userService.findUserByCompany(internship.getCompany().getId()));
        }

        PageWrapper<Internship> page = new PageWrapper<>(internships, "/internships?s="+query);
        model.addAttribute("internships", internships.getContent());
        model.addAttribute("map", map);
        model.addAttribute("page", page);
        return "search";
    }
}
