package com.example.touristguideapigruppe.exceptions;

public class UnknownErrorException extends RuntimeException{

    public UnknownErrorException(){
        super("Noget gik galt");
    }
}

