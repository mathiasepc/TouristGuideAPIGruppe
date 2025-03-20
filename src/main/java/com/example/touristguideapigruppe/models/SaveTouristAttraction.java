package com.example.touristguideapigruppe.models;

import java.util.List;


// Bliver brugt til at gemme en attraktion
public class SaveTouristAttraction {
    private int id;
    private String name;
    private String description;
    private int cityId;
    private List<Integer> tagIds;

    public SaveTouristAttraction(int id, String name, String description, int city, List<Integer> tagIds) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cityId = city;
        this.tagIds = tagIds;
    }

    public SaveTouristAttraction() { }

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

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public List<Integer> getTagIds() {
        return tagIds;
    }

    public void setTagIds(List<Integer> tagIds) {
        this.tagIds = tagIds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
