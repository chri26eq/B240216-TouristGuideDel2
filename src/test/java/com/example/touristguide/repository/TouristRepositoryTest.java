package com.example.touristguide.repository;

import com.example.touristguide.model.Attraction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TouristRepositoryTest {
@Autowired
    private TouristRepository repository;

    @Test
    void addAttractionSizeIncreased() {
        int sizeBeforeAdd = repository.getAttractionList().size();
        repository.addAttraction(new Attraction("TestName", "Test description", "Testtown", List.of("testTag", "tag", "test")));
        int sizeAfterAdd = repository.getAttractionList().size();

        assertEquals(sizeBeforeAdd + 1, sizeAfterAdd);

    }

    @Test
    void findByName() {

        Attraction foundAttraction = repository.findByName("Kronborg Castle");
        //Kronborg is first in the list
        assertEquals(repository.getAttractionList().get(0), foundAttraction);

    }

    @Test
    void updateAttraction() {
        String newDescription = "test";
        Attraction editedAttraction = new Attraction("Kronborg Castle", newDescription, "Helsingør", List.of("castle", "history", "museum"));

        repository.updateAttraction(editedAttraction);

        String expectedDescription = newDescription;
        String actualDescription = repository.getAttractionList().get(0).getDescription();

        assertEquals(expectedDescription, actualDescription);


    }

    @Test
    void deleteAttraction() {


        int sizeBeforeDelete = repository.getAttractionList().size();
        String nameOfFirstInList = repository.getAttractionList().get(0).getName();

        repository.deleteAttraction("Kronborg Castle");

        int sizeAfterDelete = repository.getAttractionList().size();
        String nameOfFirstInListAfterDelete = repository.getAttractionList().get(0).getName();

        assertEquals(sizeBeforeDelete - 1, sizeAfterDelete);
        assertNotEquals(nameOfFirstInList, nameOfFirstInListAfterDelete);

    }


    @Test
    void getAllTagsOnlyUniqueTags() {
        int numberOfTagsBefore = repository.getAllTags().size();
        // new tags "testTag", "tag", "test"
        repository.addAttraction(new Attraction("TestName", "Test description", "Testtown", List.of("testTag", "tag", "test")));
        int numberOfTagsAfter = repository.getAllTags().size();

        assertEquals(numberOfTagsBefore + 3, numberOfTagsAfter);

        // same tags are added again "testTag", "tag", "test"
        repository.addAttraction(new Attraction("TestNameTwo", "Test description", "Testtown", List.of("testTag", "tag", "test")));
        int numberOfTagsAfterSameTagsAreAdded = repository.getAllTags().size();
        assertEquals(numberOfTagsBefore + 3, numberOfTagsAfterSameTagsAreAdded);

    }

    @Test
    void tagsAreRemovedUponAttractionDeletion() {
        repository.addAttraction(new Attraction("TestName", "Test description", "Testtown", List.of("testTag", "tag", "test")));

        int numberOfUniqueTags = repository.getAllTags().size();
        repository.deleteAttraction("TestName");
        int numberOfUniqueTagsAfterAttractionDeletion = repository.getAllTags().size();

        assertEquals(numberOfUniqueTags - 3, numberOfUniqueTagsAfterAttractionDeletion);
    }

    @Test
    void tagsAreNotRemovedIfStillPresentInAttractionList() {
        repository.addAttraction(new Attraction("TestName", "Test description", "Testtown", List.of("testTag", "tag", "test")));
        repository.addAttraction(new Attraction("TestNameTwo", "Test description", "Testtown", List.of("testTag", "tag", "test")));

        int numberOfUniqueTags = repository.getAllTags().size();
        repository.deleteAttraction("TestName");
        int numberOfUniqueTagsAfterAttractionDeletion = repository.getAllTags().size();

        assertEquals(numberOfUniqueTags, numberOfUniqueTagsAfterAttractionDeletion);
    }

    @Test
    void getAllTownsOnlyUniqueTowns() {

    }
}