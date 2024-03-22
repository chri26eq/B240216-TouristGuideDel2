package com.example.touristguide.service;

import com.example.touristguide.model.Attraction;
import com.example.touristguide.repository.TouristRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void updateAttraction(Attraction editedAttraction) {
        repository.updateAttraction(editedAttraction);
    }

    public void deleteAttraction(String attractionName) {
        repository.deleteAttraction(attractionName);
    }


    public List<String> getAllTags() {
        return repository.getAllTags();
    }

    public List<String> getAllCities() {
        return repository.getAllCities();
    }

}
