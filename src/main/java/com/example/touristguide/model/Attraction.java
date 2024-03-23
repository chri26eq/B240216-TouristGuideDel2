package com.example.touristguide.model;


import java.util.ArrayList;
import java.util.List;

public class Attraction {

    private String name;
    private String description;
    private String city;
    private List<String> tags;
    private String newRawTagsString;


    public Attraction(String name, String description, String city, List<String> tags) {
        this.name = name;
        this.description = description;
        this.city = city;
        this.tags = tags;

    }

    public void prepareAttraction() {
        addNewTagsToTags();
        trimAll();
    }

    private void addNewTagsToTags() {
        if (newRawTagsString != null) {
            List<String> newRawTagList = List.of(newRawTagsString.split(","));
            if (tags == null) tags = new ArrayList<>();
            for (String s : newRawTagList) {
                if (!s.isBlank()) tags.add(s.trim());
            }
            newRawTagsString = null;
        }
    }

    private void trimAll() {
        name = name.trim();
        description = description.trim();
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getNewRawTagsString() {
        return newRawTagsString;
    }

    public void setNewRawTagsString(String newRawTagsString) {
        this.newRawTagsString = newRawTagsString;
    }
}
