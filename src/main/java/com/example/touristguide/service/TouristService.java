package com.example.touristguide.service;

import com.example.touristguide.model.Attraction;
import com.example.touristguide.repository.TouristRepository;
import org.springframework.stereotype.Service;
import org.w3c.dom.Attr;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class TouristService {
    final private TouristRepository repository;

    public TouristService(TouristRepository repository) {
        this.repository = repository;
    }


    public List<Attraction> getAttractionList() {
        return repository.getAttractionList();
    }

    public Attraction findByName(String name) {
        return repository.findByName(name);
    }

    public void addAttraction(Attraction attraction) {
        repository.addAttraction(attraction);
    }

    public void updateAttraction(Attraction attraction) {
        repository.updateAttraction(attraction);
    }

    public void deleteAttraction(String name) {
        repository.deleteAttraction(name);
    }


    public List<String> getAllTags() {
        return repository.getAllTags();
    }

    public List<String> getAllTowns() {
        return repository.getAllTowns();
    }

}
