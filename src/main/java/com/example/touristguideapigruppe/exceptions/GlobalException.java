package com.example.touristguideapigruppe.exceptions;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException{

    @ExceptionHandler(NotFoundException.class)
    public String handleNotFoundException(NotFoundException ex, Model model){
        model.addAttribute("error", ex.getMessage());
        return "error-page";
    }

    @ExceptionHandler(NullException.class)
    public String handleNullException(NullException ex, Model model){
        model.addAttribute("error", ex.getMessage());
        return "error-page";
    }

    @ExceptionHandler(UnkownErrorException.class)
    public String somethingWentWrongException(UnkownErrorException ex, Model model){
        model.addAttribute("error", ex.getMessage());
        return "error-page";
    }

    @ExceptionHandler(ValueExistException.class)
    public String valueExistException(ValueExistException ex, Model model){
        model.addAttribute("error", ex.getMessage());
        return "error-page";
    }
}