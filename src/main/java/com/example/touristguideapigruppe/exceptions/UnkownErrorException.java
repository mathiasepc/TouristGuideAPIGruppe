package com.example.touristguideapigruppe.exceptions;

public class UnkownErrorException extends RuntimeException{

    public UnkownErrorException(){
        super("Noget gik galt");
    }
}

