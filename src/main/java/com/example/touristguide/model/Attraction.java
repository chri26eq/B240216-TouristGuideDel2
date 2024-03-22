package com.example.touristguide.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Attraction {

    private String name;
    private String description;
    private String city;
    private List<String> tags;
    private String newTagsString;



    public Attraction(String name, String description, String city, List<String> tags) {
        this.name = name.trim();
        this.description = description.trim();
        this.city = city.trim();
        this.tags = tags;

    }

        private void addNewTagsToTags() {

        if (!newTagsString.isBlank()){
            List<String> newTagsList = new ArrayList<>(List.of(newTagsString.split(",")));
            newTagsList = newTagsList.stream().map(String::trim).collect(Collectors.toList());

        }
        }



    public void copyAttractionAttributes(Attraction attraction) {
        this.name = attraction.name.trim();
        this.description = attraction.description.trim();
        this.city = attraction.city.trim();
        this.tags = attraction.tags;

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

    public String getNewTagsString() {
        return newTagsString;
    }

    public void setNewTagsString(String newTagsString) {
        this.newTagsString = newTagsString;
    }
}
