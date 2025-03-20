package com.example.touristguideapigruppe.models;

import java.util.List;

// Bliver brugt til at vise en attraktion
public class TouristAttraction {
    private int id;
    private String name, description;
    private KeyValuePair city;
    private List<KeyValuePair> tags;

    public TouristAttraction(int id, String name, String description, KeyValuePair city, List<KeyValuePair> tags) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.city = city;
        this.tags = tags;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public KeyValuePair getCity() {
        return city;
    }

    public void setCity(KeyValuePair city) {
        this.city = city;
    }

    public List<KeyValuePair> getTags() {
        return tags;
    }

    public void setTag(List<KeyValuePair> tags) {
        this.tags = tags;
    }
}
