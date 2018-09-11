package it.univaq.we.internshipTutor.controller;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String renderReport(@Nullable @RequestParam(value = "error") String error, ModelMap model) {

        if(error != null) {
            model.addAttribute("error", error);
        }

        return "login";
    }

}
