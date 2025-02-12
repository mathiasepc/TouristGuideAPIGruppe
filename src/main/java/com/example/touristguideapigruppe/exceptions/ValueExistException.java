package com.example.touristguideapigruppe.exceptions;

public class ValueExistException extends RuntimeException {

    public ValueExistException(String input){
        super(input + " eksisterer allerede.");
    }
}
