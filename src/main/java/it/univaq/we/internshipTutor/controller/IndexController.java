package it.univaq.we.internshipTutor.controller;

import it.univaq.we.internshipTutor.model.Popup;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @RequestMapping(value={"/", "/index", "/index.html"}, method = RequestMethod.GET)
    public String index(HttpSession httpSession, ModelMap model) {

        model.addAttribute("popup", httpSession.getAttribute("popup"));
        httpSession.removeAttribute("popup");
        return "index";
    }

}
