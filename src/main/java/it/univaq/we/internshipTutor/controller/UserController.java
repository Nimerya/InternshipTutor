package it.univaq.we.internshipTutor.controller;

import it.univaq.we.internshipTutor.model.*;
import it.univaq.we.internshipTutor.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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


    @RequestMapping(value = {"/admin/create/user"}, method = RequestMethod.POST)
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
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", result);
            redirectAttributes.addFlashAttribute("user", user);
            return "redirect:/admin/create/user";
        }

        // a user cannot be a company and a student at the same time
        if(user.getCompany() != null && user.getStudent() != null){
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", "Something Went Wrong! Cannot link user to both a Company and a Student."));
            return "redirect:/admin/create/user";
        }

        // a student should have student role
        if(user.getStudent() != null && !user.getRole().getName().equals("STUDENT")){
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", "Something Went Wrong! A Student should have STUDENT role."));
            return "redirect:/admin/create/user";
        }

        // a company should have company role
        if(user.getCompany() != null && !user.getRole().getName().equals("COMPANY")){
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", "Something Went Wrong! A Company should have COMPANY role."));
            return "redirect:/admin/create/user";
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        try{
            user.setImage(fileUploadService.uploadImage(imageFile, user.getFirstName()+"_"+user.getLastName()));
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", "Something Went Wrong! Check the image you have uploaded"));
            return "redirect:/admin/create/user";
        }

        try{
            User c = null;
            User s = null;

            if(user.getCompany() != null){
                c = userService.findUserByCompany(user.getCompany().getId());
            }
            if(user.getStudent() != null){
                s = userService.findUserByStudent(user.getStudent().getId());
            }
            if(c == null && s == null){
                userService.save(user);
            }else throw new Exception("this company/student is already bound to another user");

        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN_SAVE));
            return "redirect:/admin/create/user";
        }

        // add success message in the model
        redirectAttributes.addFlashAttribute("popup", new Popup("success", "Operation Completed Successfully!"));

        return "redirect:/admin/create/user";
    }

    @RequestMapping(value = {"/admin/update/user"}, method = RequestMethod.POST)
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
            return "redirect:/admin/update/user/" + user.getId();
        }


        // a user cannot be a company and a student at the same time
        if(user.getCompany() != null && user.getStudent() != null){
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", "Something Went Wrong! Cannot link user to both a Company and a Student."));
            return "redirect:/admin/update/user/" + user.getId();
        }

        // a student should have student role
        if(user.getStudent() != null && !user.getRole().getName().equals("STUDENT")){
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", "Something Went Wrong! A Student should have STUDENT role."));
            return "redirect:/admin/update/user/" + user.getId();
        }

        // a company should have company role
        if(user.getCompany() != null && !user.getRole().getName().equals("COMPANY")){
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", "Something Went Wrong! A Company should have COMPANY role."));
            return "redirect:/admin/update/user/" + user.getId();
        }

        try{
            user.setImage(fileUploadService.uploadImage(imageFile, user.getFirstName()+"_"+user.getLastName()));
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", "Something Went Wrong! Check the image you have selected"));
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", result);
            redirectAttributes.addFlashAttribute("user", user);
            return "redirect:/admin/update/user/" + user.getId();
        }

        try{
            // otherwise the password will be emptied
            String oldPassword = userService.findUserById(user.getId()).getPassword();
            user.setPassword(oldPassword);

            User c = null;
            User s = null;

            if(user.getCompany() != null){
                c = userService.findUserByCompany(user.getCompany().getId());
            }
            if(user.getStudent() != null){
                s = userService.findUserByStudent(user.getStudent().getId());
            }
            if(c == null && s == null){
                userService.save(user);
            }else throw new Exception("this company/student is already bound to another user");

        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN_SAVE));
            return "redirect:/admin/update/user/" + user.getId();
        }

        // add success message in the model
        redirectAttributes.addFlashAttribute("popup", new Popup());

        return "redirect:/admin/update/user/" + user.getId();
    }

    @RequestMapping(value = {"/admin/delete/user/{id}"}, method = RequestMethod.POST)
    public String doDelete(ModelMap model, @PathVariable(value = "id") Long id, RedirectAttributes redirectAttributes) {

        if (id == null || id < 0) {
            // if there are errors during the binding (e.g. NotNull, Min, etc.)
            // redirect to the form displaying the errors
            // add error message in the model
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN));
            return "redirect:/admin/update/user/" + id;
        }

        try{
            // else perform the insertion
            userService.deleteUserById(id);
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("popup", new Popup("warning", WAR_MSG_EN_DEL));
            redirectAttributes.addFlashAttribute("user", userService.findUserById(id));
            return "redirect:/admin/update/user/" + id;
        }

        // add success message in the model
        redirectAttributes.addFlashAttribute("popup", new Popup());
        return "redirect:/admin/create/user";
    }


    @RequestMapping(value = {"/admin/create/user"}, method = RequestMethod.GET)
    public String renderCreate(ModelMap model, Pageable pageable) {

        if (!model.containsAttribute("user")) {
            model.addAttribute("user", new User(UUID.randomUUID()));
        }

        Page<User> users = userService.findAll(pageable);
        PageWrapper<User> page = new PageWrapper<>(users, "/admin/create/user");
        model.addAttribute("users", page.getContent());
        model.addAttribute("page", page);

        List<Company> companies = companyService.findAll();
        model.addAttribute("companies", companies);

        List<Student> students = studentService.findAll();
        model.addAttribute("students", students);

        List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);

        return "user_create";
    }

    @RequestMapping(value = {"/admin/update/user/{id}"}, method = RequestMethod.GET)
    public String renderUpdate(ModelMap model, Pageable pageable, @PathVariable(value = "id") Long id) {


        if (!model.containsAttribute("user")) {
            User u = userService.findUserById(id);
            model.addAttribute("user", u);
        }

        Page<User> users = userService.findAll(pageable);
        PageWrapper<User> page = new PageWrapper<>(users, "/admin/update/user/"+id);
        model.addAttribute("users", page.getContent());
        model.addAttribute("page", page);

        List<Company> companies = companyService.findAll();
        model.addAttribute("companies", companies);

        List<Student> students = studentService.findAll();
        model.addAttribute("students", students);

        List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);

        return "user_update";
    }


    @RequestMapping(value = {"/admin/report/users"}, method = RequestMethod.GET)
    public String renderReport(ModelMap model, Pageable pageable) {

        Page<User> users = userService.findAll(pageable);
        PageWrapper<User> page = new PageWrapper<>(users, "/admin/report/users");
        model.addAttribute("collection", page.getContent());
        model.addAttribute("page", page);
        model.addAttribute("nameS", "user");
        model.addAttribute("nameP", "Users");

        return "report";
    }

}
