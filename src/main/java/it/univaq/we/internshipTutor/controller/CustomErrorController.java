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

        Integer errorCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");

        String errorMessage;

        model.addAttribute("errorCode", errorCode);

        switch (errorCode) {
            case 403:
                errorMessage = "You are not authorized to access this resource.";
                break;
            case 404:
                errorMessage = "The resource you were looking for cannot be found.";
                break;

                //TODO add other error codes

            default:
                errorMessage = "Something went wrong.";
                break;
        }

        model.addAttribute("errorMessage", errorMessage);

        try{
            exception.printStackTrace();
        }catch (Exception e){

        }

        return "error_page";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}