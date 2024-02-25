package com.example.touristguide.repository;

import com.example.touristguide.model.Attraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class TouristRepository {

    List<Attraction> attractionList;

    public TouristRepository() {
        attractionList = new ArrayList<>();

        attractionList.add(new Attraction("Kronborg Castle", "An impressive Renaissance castle, famously known as Hamlet's castle. It is a UNESCO World Heritage Site, attracting visitors with its history and architecture.", "Kronborg", "1B", "3000", "Helsingør", Set.of("castle", "history", "museum")));
        attractionList.add(new Attraction("M S Maritime Museum of Denmark", "A maritime museum showcasing Denmark's maritime history. The museum features an impressive collection of ships, models, and artifacts.", "Ny Kronborgvej", "1", "3000", "Helsingør", Set.of("museum", "maritime", "history")));
        attractionList.add(new Attraction("Helsingør Cathedral (St. Olai Church)", "A beautiful cathedral, also known as St. Olai Church, dating back to the 13th century. The church boasts impressive architecture and artworks.", "Sct. Anna Gade", "36", "3000", "Helsingør", Set.of("cathedral", "church", "history")));
        attractionList.add(new Attraction("Kulturværftet", "A cultural center by the waterfront hosting theater performances, concerts, and art exhibitions. It is a vibrant hub for culture and entertainment.", "Allegade", "2", "3000", "Helsingør", Set.of("culture", "arts", "events", "food", "indoors")));
        attractionList.add(new Attraction("Øresund Aquarium", "An aquarium focusing on the marine life of the Øresund region. Visitors can explore a wide range of sea creatures and learn about the marine ecosystem.", "Strandpromenaden", "5", "3000", "Helsingør", Set.of("aquarium", "education", "indoors")));
        attractionList.add(new Attraction("Louisiana Museum of Modern Art", "An internationally renowned museum of modern and contemporary art. Located by the sea, the museum offers a unique cultural experience with its impressive art collection and scenic surroundings.", "Gl. Strandvej", "13", "3050", "Humlebæk", Set.of("museum", "art", "scenic")));
        attractionList.add(new Attraction("Hammermøllen", "Hammermøllen is a historic watermill located in Hellebæk. The mill has a fascinating history and beautiful surroundings next to the stream.", "Bøssemagergade", "21", "3050", "Hellebæk", Set.of("watermill", "history", "scenic", "forest", "nature")));
        attractionList.add(new Attraction("Skibstrup Recycling Center", "A recycling center located in Ålsgårde, providing the opportunity for proper disposal of waste and recycling. Contribute to environmentally friendly practices by reusing and recycling materials.", "Gørlundevej", "4", "3140", "Ålsgårde", Set.of("recycling", "environment", "sustainability")));

    }


    public void addAttraction(Attraction attraction) {
        attraction.addCustomTagsToTags();
        attractionList.add(attraction);
    }

    public void updateAttraction(Attraction editedAttraction) {
        editedAttraction.addCustomTagsToTags();
        Attraction oldAttraction = findByName(editedAttraction.getName());

        oldAttraction.copyAttractionAttributes(editedAttraction);
    }

    public void deleteAttraction(String name) {
        attractionList.remove(findByName(name));
    }


    public Attraction findByName(String name) {
        for (Attraction attraction : attractionList) {
            if (name.trim().equalsIgnoreCase(attraction.getName().trim())) {
                return attraction;
            }

        }
        return null;
    }


    public List<Attraction> getAttractionList() {
        return attractionList;
    }

    public List<String> getAllTags() {
        Set<String> tagSet = new HashSet<>();
        for (Attraction attraction : attractionList) {
            tagSet.addAll(attraction.getTags());
        }
        return new ArrayList<>(tagSet);
    }

    public List<String> getAllTowns() {
        Set<String> townSet = new HashSet<>();
        for (Attraction attraction : attractionList) {
            townSet.add(attraction.getTown());
        }
        return new ArrayList<>(townSet);
    }
}


