package com.example.touristguideapigruppe.exceptions;

public class NotFoundException extends Exception {

    public NotFoundException(String input){
        super(input + " Findes ikke.");
    }
}
