package it.univaq.we.internshipTutor.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping(value="/error", method = RequestMethod.GET)
    public ModelAndView error(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");

        HashMap<String, Object> map = new HashMap<>();
        map.put("status", statusCode);
        map.put("exception", exception);

        return new ModelAndView("error", map);
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}