package it.univaq.we.internshipTutor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
@ControllerAdvice
public class InitController {

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("msg_1", "Pippo!");
    }

}
