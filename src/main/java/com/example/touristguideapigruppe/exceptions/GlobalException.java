package com.example.touristguideapigruppe.exceptions;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

// Vi laver en bean. Som peger på de forskellige ExceptionHandler
@ControllerAdvice
public class GlobalException{

    // .class bliver brugt til at fortælle typen.
    @ExceptionHandler(NotFoundException.class)
    public String handleNotFoundException(NotFoundException ex, Model model){
        model.addAttribute("error", ex.getMessage());
        return "error-page";
    }

    // .class bliver brugt til at fortælle typen.
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleNullException(IllegalArgumentException ex, Model model){
        model.addAttribute("error", ex.getMessage());
        return "error-page";
    }

    // .class bliver brugt til at fortælle typen.
    @ExceptionHandler(UnknownErrorException.class)
    public String somethingWentWrongException(UnknownErrorException ex, Model model){
        model.addAttribute("error", ex.getMessage());
        return "error-page";
    }

    // .class bliver brugt til at fortælle typen.
    @ExceptionHandler(ValueExistException.class)
    public String valueExistException(ValueExistException ex, Model model){
        model.addAttribute("error", ex.getMessage());
        return "error-page";
    }

    // .class bliver brugt til at fortælle typen.
    @ExceptionHandler(SQLException.class)
    public String handleSQLException(SQLException ex, Model model){
        model.addAttribute("error", ex.getMessage());
        return "error-page";
    }
}
