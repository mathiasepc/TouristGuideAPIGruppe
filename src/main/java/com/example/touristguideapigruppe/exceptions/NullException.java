package com.example.touristguideapigruppe.exceptions;

public class NullException extends RuntimeException{

    public NullException(){
        super("Du skal indtaste noget.");
    }
}
