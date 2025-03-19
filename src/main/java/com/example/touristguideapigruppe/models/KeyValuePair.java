package com.example.touristguideapigruppe.models;

//Glæder både for Tags og City
public class KeyValuePair {
    private int id;
    private String name;

    public KeyValuePair(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

