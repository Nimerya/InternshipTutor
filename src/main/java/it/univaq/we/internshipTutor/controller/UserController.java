package it.univaq.we.internshipTutor.controller;

import it.univaq.we.internshipTutor.model.*;
import it.univaq.we.internshipTutor.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import static it.univaq.we.internshipTutor.model.Popup.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
public class UserController {

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


    @RequestMapping(value = {"/create/user"}, method = RequestMethod.POST)
    public String doCreate(@Valid @ModelAttribute("user") User user,
                           BindingResult result,
                           RedirectAttributes redirectAttributes,
                           @RequestParam("imageFile")MultipartFile imageFile) {

        if (result.hasErrors()) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors ) {
                System.out.println (error.getObjectName() + " - " + error.getDefaultMessage());
            }
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN+"AAA"));
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", result);
            redirectAttributes.addFlashAttribute("user", user);
            return "redirect:/create/user";
        }

        if(user.getCompany() != null && user.getStudent() != null){
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", "Something Went Wrong! Cannot link user to both a company and a student."));
            return "redirect:/create/user";
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        try{
            user.setImage(fileUploadService.uploadImage(imageFile, user.getFirstName()+"_"+user.getLastName()));
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", "Something Went Wrong! Check the image you have uploaded"));
            return "redirect:/create/user";
        }

        try{
            // else perform the insertion
            userService.save(user);
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            return "redirect:/create/user";
        }

        // add success message in the model
        redirectAttributes.addFlashAttribute("popup", new Popup("success", "Operation Completed Successfully!"));

        return "redirect:/create/user";
    }

    @RequestMapping(value = {"/update/user"}, method = RequestMethod.POST)
    public String doUpdate(@Valid @ModelAttribute("user") User user,
                           BindingResult result,
                           RedirectAttributes redirectAttributes,
                           @RequestParam("imageFile")MultipartFile imageFile) {

        if (result.hasErrors()) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", result);
            redirectAttributes.addFlashAttribute("user", user);
            return "redirect:/update/user/" + user.getId();
        }

        if(user.getCompany() != null && user.getStudent() != null){
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", "Something Went Wrong! Cannot link user to both a company and a student."));
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", result);
            redirectAttributes.addFlashAttribute("user", user);
            return "redirect:/update/user/" + user.getId();
        }

        try{
            user.setImage(fileUploadService.uploadImage(imageFile, user.getFirstName()+"_"+user.getLastName()));
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", "Something Went Wrong! Check the image you have selected"));
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", result);
            redirectAttributes.addFlashAttribute("user", user);
            return "redirect:/update/user/" + user.getId();
        }

        try{
            // otherwise the password will be emptied
            String oldPassword = userService.findUserById(user.getId()).getPassword();
            user.setPassword(oldPassword);

            userService.save(user);
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            return "redirect:/update/user/" + user.getId();
        }

        // add success message in the model
        redirectAttributes.addFlashAttribute("popup", new Popup());

        return "redirect:/update/user/" + user.getId();
    }

    @RequestMapping(value = {"/delete/user/{id}"}, method = RequestMethod.POST)
    public String doDelete(ModelMap model, @PathVariable(value = "id") Long id, RedirectAttributes redirectAttributes) {

        if (id == null || id < 0) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            // add error message in the model
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            return "redirect:/update/user/" + id;
        }

        try{
            // else perform the insertion
            userService.deleteUserById(id);
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            return "redirect:/create/user";
        }

        // add success message in the model
        redirectAttributes.addFlashAttribute("popup", new Popup());
        return "redirect:/create/user";
    }


    @RequestMapping(value = {"/create/user"}, method = RequestMethod.GET)
    public String renderCreate(ModelMap model) {

        if (!model.containsAttribute("user")) {
            model.addAttribute("user", new User(UUID.randomUUID()));
        }

        List<User> users = userService.findAll();
        model.addAttribute("users", users);

        List<Company> companies = companyService.findAll();
        model.addAttribute("companies", companies);

        List<Student> students = studentService.findAll();
        model.addAttribute("students", students);

        List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);

        return "user_create";
    }

    @RequestMapping(value = {"/update/user/{id}"}, method = RequestMethod.GET)
    public String renderUpdate(ModelMap model, @PathVariable(value = "id") Long id) {


        if (!model.containsAttribute("user")) {
            User u = userService.findUserById(id);
            model.addAttribute("user", u);
        }

        List<User> users = userService.findAll();
        model.addAttribute("users", users);

        List<Company> companies = companyService.findAll();
        model.addAttribute("companies", companies);

        List<Student> students = studentService.findAll();
        model.addAttribute("students", students);

        List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);

        return "user_update";
    }

}
