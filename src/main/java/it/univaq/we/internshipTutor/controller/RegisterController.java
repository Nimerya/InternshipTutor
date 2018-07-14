package it.univaq.we.internshipTutor.controller;


import it.univaq.we.internshipTutor.model.*;
import it.univaq.we.internshipTutor.service.*;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static it.univaq.we.internshipTutor.model.Popup.WAR_MSG_EN;

@Controller
public class RegisterController {

    @Autowired
    UserService userService;

    @Autowired
    CompanyService companyService;

    @Autowired
    StudentService studentService;

    @Autowired
    RoleService roleService;

    @Autowired
    FileUploadService fileUploadService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    DegreeService degreeService;

    @RequestMapping(value = {"/register/student"}, method = RequestMethod.POST)
    public String doStudentRegistration(@Valid @ModelAttribute("user") User user,
                                        @Valid @ModelAttribute("student") Student student,
                                        BindingResult result,
                                        RedirectAttributes redirectAttributes,
                                        @RequestParam("repeatPassword") String password,
                                        @RequestParam("imageFile") MultipartFile imageFile) {

        if (result.hasErrors()) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors

            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors) {
                System.out.println(error.getObjectName() + " - " + error.getDefaultMessage());
            }

            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", result);
            redirectAttributes.addFlashAttribute("user", user);
            redirectAttributes.addFlashAttribute("student", student);

            return "redirect:/register/student";
        }

        // set student permissions
        user.setRole(roleService.findRoleByName("STUDENT"));

        // set no handicap if not already set
        if (student.getHandicap() == null) {
            student.setHandicap(Boolean.FALSE);
        }

        // set default image for now
        if (user.getImage() == null) {
            user.setImage("default.jpg");
        }

        // check if password are the same
        try {
            if (user.getPassword().equals(password)) {
                user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            } else {
                throw new RuntimeException("Passwords do not match!");
            }
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", e.getMessage()));
            return "redirect:/register/student";
        }

        try {
            // else perform the insertion
            studentService.save(student);
            user.setStudent(student);
            userService.save(user);
        } catch (Throwable e) {
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            if (e.getMessage().contains("email_UNIQUE")) {
                redirectAttributes.addFlashAttribute("popup", new Popup("warning", "E-mail already in use!"));
                studentService.deleteStudentById(student.getId());
            }
            return "redirect:/register/student";
        }

        // check if uploaded file is a valid image and set it
        try {
            user.setImage(fileUploadService.uploadImage(imageFile, user.getFirstName() + "_" + user.getLastName()));
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", e.getMessage()));
            return "redirect:/register/student";
        }

        // add success message in the model
        redirectAttributes.addFlashAttribute("popup", new Popup("success", "Operation Completed Successfully!"));

        return "redirect:/register/student/success";
    }

    @RequestMapping(value = {"/register/company"}, method = RequestMethod.POST)
    public String doCompanyRegistration(@Valid @ModelAttribute("user") User user,
                                        @Valid @ModelAttribute("company") Company company,
                                        BindingResult result,
                                        RedirectAttributes redirectAttributes,
                                        @RequestParam("repeatPassword") String password,
                                        @RequestParam("imageFile") MultipartFile imageFile) {

        if (result.hasErrors()) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors

            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors) {
                System.out.println(error.getObjectName() + " - " + error.getDefaultMessage());
            }

            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", result);
            redirectAttributes.addFlashAttribute("user", user);
            redirectAttributes.addFlashAttribute("company", company);

            return "redirect:/register/company";
        }

        // set student permissions
        user.setRole(roleService.findRoleByName("COMPANY"));

        // set no active as default
        company.setActive(Boolean.FALSE);

        // set default image for now
        if (user.getImage() == null) {
            user.setImage("default.jpg");
        }

        // check if password are the same
        try {
            if (user.getPassword().equals(password)) {
                user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            } else {
                throw new RuntimeException("Passwords do not match!");
            }
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", e.getMessage()));
            return "redirect:/register/company";
        }

        try {
            // else perform the insertion
            companyService.save(company);
            user.setCompany(company);
            userService.save(user);
        } catch (Throwable e) {
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            // email already in use
            if (e.getMessage().contains("email_UNIQUE")) {
                redirectAttributes.addFlashAttribute("popup", new Popup("warning", "E-mail already in use!"));
                companyService.deleteCompanyById(company.getId());
            }
            // company name already in use
            if (e.getMessage().contains("name_UNIQUE")) {
                redirectAttributes.addFlashAttribute("popup", new Popup("warning", "Company name already in use!"));
                companyService.deleteCompanyById(company.getId());
            }
            return "redirect:/register/company";
        }

        // check if uploaded file is a valid image and set it
        try {
            user.setImage(fileUploadService.uploadImage(imageFile, user.getFirstName() + "_" + user.getLastName()));
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", e.getMessage()));
            return "redirect:/register/company";
        }

        // add success message in the model
        redirectAttributes.addFlashAttribute("popup", new Popup("success", "Operation Completed Successfully!"));

        return "redirect:/register/company/success";
    }


    @RequestMapping(value = {"/register/{userType}"}, method = RequestMethod.GET)
    public String renderRegistration(@PathVariable String userType, ModelMap model) {
        System.out.println(userType);
        switch (userType) {
            case "student":
                if (!model.containsAttribute("user")) {
                    model.addAttribute("user", new User(UUID.randomUUID()));
                }
                if (!model.containsAttribute("student")) {
                    model.addAttribute("student", new Student(UUID.randomUUID()));
                }
                List<Degree> degrees = degreeService.findAll();
                model.addAttribute("degrees", degrees);
                return "student_register";

            case "company":
                if (!model.containsAttribute("user")) {
                    model.addAttribute("user", new User(UUID.randomUUID()));
                }
                if (!model.containsAttribute("company")) {
                    model.addAttribute("company", new Company(UUID.randomUUID()));
                }
                return "company_register";

            default:
                return "redirect:/error";
        }

    }

    @RequestMapping(value = {"/register/{userType}/success"}, method = RequestMethod.GET)
    public String renderRegistrationSuccess(@PathVariable String userType, ModelMap model) {

        switch (userType) {
            case "student":
                return "student_register_success";

            case "company":
                return "company_register_success";

            default:
                return "redirect:/error";
        }


    }



}
