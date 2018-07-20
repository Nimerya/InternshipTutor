package it.univaq.we.internshipTutor.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping(value="/error", method = {RequestMethod.GET})
    public String error(HttpServletRequest request, ModelMap model) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");

        if (statusCode.equals(403)){
            return "403";
        }

        model.addAttribute("status", statusCode);
        model.addAttribute("exception", exception);

        return "error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}