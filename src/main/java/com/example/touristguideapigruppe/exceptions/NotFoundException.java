package com.example.touristguideapigruppe.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String input){
        super(input + " Findes ikke.");
    }
}
