package it.univaq.we.internshipTutor.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.logging.Logger;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping(value="/error", method = {RequestMethod.GET})
    public String error(HttpServletRequest request, ModelMap model,
                        @RequestParam(value = "code", required = false) Integer code) {

        Integer errorCode;
        String errorMessage;

        if(code == null) {
            errorCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
            Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
            try{
                exception.printStackTrace();
            }catch (Exception e){
                // there is nothing that I can do :(
            }
        }else{
            errorCode = code;
        }

        switch (errorCode) {
            case 401:
            case 403:
                errorMessage = "You are not authorized to access this resource.";
                break;
            case 404:
                errorMessage = "The resource you were looking for cannot be found.";
                break;
            case 500:
            case 501:
            case 502:
            case 503:
            case 504:
            case 505:
            case 506:
            case 507:
            case 508:
            case 509:
            case 510:
            case 511:
                errorMessage = "Something went wrong on our end.";
                break;

            default:
                errorMessage = "Something went wrong.";
                break;
        }

        model.addAttribute("errorCode", errorCode);
        model.addAttribute("errorMessage", errorMessage);

        return "error_page";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}