package com.example.touristguide.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Attraction {

    private String name;
    private String description;
    private String street;
    private String streetNr;
    private String zipCode;
    private String town;
    private Set<String> tags;
    private String addedCustomTags;
    private String address;


    public Attraction(String name, String description, String street, String streetNr, String zipCode, String town, Set<String> tags) {
        this.name = name.trim();
        this.description = description.trim();
        this.street = street.trim();
        this.streetNr = streetNr.trim();
        this.zipCode = zipCode.trim();
        this.town = town.trim();
        this.tags = tags;
        parseAddress();
    }

    private void parseAddress() {
        address = street + " " + streetNr + ", " + zipCode + " " + town;
    }


    public void addCustomTagsToTags() {
        Set<String> newTags = new HashSet<>();
        if (addedCustomTags != null) {
            List<String> tagsRaw = List.of(addedCustomTags.split(","));
            for (String tag : tagsRaw) {
                if (!tag.isBlank()) {
                    newTags.add(tag.trim());
                }
            }
        }
        if (tags == null) tags = newTags;
        else tags.addAll(newTags);
    }


public void copyAttractionAttributes(Attraction attraction) {
    this.name = attraction.name.trim();
    this.description = attraction.description.trim();
    this.street = attraction.street.trim();
    this.streetNr = attraction.streetNr.trim();
    this.zipCode = attraction.zipCode.trim();
    this.town = attraction.town.trim();
    this.tags = attraction.tags;
    parseAddress();
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
        parseAddress();
    }

    public String getStreetNr() {
        return streetNr;
    }

    public void setStreetNr(String streetNr) {
        this.streetNr = streetNr;
        parseAddress();
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
        parseAddress();
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
        parseAddress();
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public String getAddress() {
        return address;
    }

    public String getAddedCustomTags() {
        return addedCustomTags;
    }

    public void setAddedCustomTags(String addedCustomTags) {
        this.addedCustomTags = addedCustomTags;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
