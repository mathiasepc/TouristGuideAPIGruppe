package com.example.touristguideapigruppe.models;

public enum Tags {
    BØRNEVENLIG("Børne venlig"),
    GRATIS("Gratis"),
    MUSEUM("Museum"),
    KUNST("Kunst"),
    NATUR("Natur"),
    RESTAURANT("Restaurant"),
    UNDERHOLDNING("Underholdning");


    private String displayName;

    Tags(String displayName){
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
