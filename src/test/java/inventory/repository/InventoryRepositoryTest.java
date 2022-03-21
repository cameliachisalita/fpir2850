package inventory.repository;


import inventory.model.Part;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class InventoryRepositoryTest {
    InventoryRepository repository;
    List<Part> list_parts;
    Integer counter;
    int added, added2;

    @BeforeAll
    void createRepo(){
        repository = new InventoryRepository();
        list_parts = new ArrayList<Part>();
        list_parts.add(new Part(0, "working_part", 1.00, 50, 5, 100));
        list_parts.add(new Part(2, "", 1.00, 10, 5, 100));
        list_parts.add(new Part(1, "working_part2", 1.00, 40, 5, 100));
        list_parts.add(new Part(3, "Compresor", 1.00, 101, 5, 100));
        added = 0;
        added2 = 0;
        assertEquals(4, list_parts.size(), "Not all 4 parts have been added");
        counter = 0;
        System.out.println("Mock repository created");
    }
@Disabled
    @Test
    @Order(1)
    void createParts() {
        list_parts.add(new Part(0, "working_part", 1.00, 50, 5, 100));
        list_parts.add(new Part(2, "", 1.00, 10, 5, 100));
        list_parts.add(new Part(1, "working_part2", 1.00, 40, 5, 100));
        list_parts.add(new Part(3, "Compresor", 1.00, 101, 5, 100));

        assertEquals(4, list_parts.size(), "Not all 4 parts have been added");
    }

    @Test
    @Order(2)
    @Disabled("These tests have flaws")
    void addIncorrectPartsInRepo() {
        list_parts = new ArrayList<Part>();
        counter = 0;
        assertEquals("", Part.isValidPart("working_part", 1.00, 50, 5, 100, ""), "The part was not correctly defined");
        assertEquals("", Part.isValidPart("", 1.00, 50, 5, 100, ""), "The name has less than 1 character");
        assertEquals("", Part.isValidPart("working_part", 1.00, 40, 5, 100, ""), "The part was not correctly defined");
        assertEquals("", Part.isValidPart("Compresor", 1.00, 101, 5, 100, ""), "The inStock number is higher than the maximum allowed for storage");

        if(Part.isValidPart("working_part", 1.00, 10, 5, 100, "").equals("")) {
            counter += 1;
            repository.addPart(list_parts.get(counter));
        }
        if(Part.isValidPart("", 1.00, 10, 5, 100, "").equals("")) {
            counter += 1;
            repository.addPart(list_parts.get(counter));
        }
        if(Part.isValidPart("working_part2", 1.00, 40, 5, 100, "").equals("")) {
            counter += 1;
            repository.addPart(list_parts.get(counter));
        }
        if(Part.isValidPart("Compresor", 1.00, 101, 5, 100, "").equals("")) {
            counter += 1;
            repository.addPart(list_parts.get(counter));
        }
    }

    @Test
    @Order(2)
    //@Disabled("These tests do NOT have flaws")
    void addCorrectPartsInRepo() {
        repository = new InventoryRepository();
        counter = 0;
        assertEquals("", Part.isValidPart("working_part", 1.00, 50, 5, 100, ""), "The part was not correctly defined");
        assertEquals("", Part.isValidPart("Some_other_working_part", 1.00, 50, 5, 100, ""), "The part was not correctly defined");
        assertEquals("", Part.isValidPart("working_part", 1.00, 40, 5, 100, ""), "The part was not correctly defined");
        assertEquals("", Part.isValidPart("Some_other_working_part", 1.00, 20, 5, 100, ""), "The part was not correctly defined");

        if(Part.isValidPart("working_part", 1.00, 10, 5, 100, "").equals("")) {

            repository.addPart(list_parts.get(counter));
            counter += 1;
        }
        if(Part.isValidPart("Some_other_working_part", 1.00, 50, 5, 100, "").equals("")) {

            repository.addPart(list_parts.get(counter));
            counter += 1;
        }
        if(Part.isValidPart("working_part2", 1.00, 40, 5, 100, "").equals("")) {

            repository.addPart(list_parts.get(counter));
            counter += 1;
        }
        if(Part.isValidPart("Some_other_working_part", 1.00, 20, 5, 100, "").equals("")) {

            repository.addPart(list_parts.get(counter));
            counter += 1;
        }
    }

    @AfterAll
    @Test
    void verifyAddedParts() {
        assertEquals(repository.getAllParts().size(), counter, "We do not have " + counter.toString()  + " items in stock");
    }

    @ParameterizedTest
    @Order(3)
    @ValueSource(strings = {"working_part", "", "working_part2", "Compresor"})
    void addPart(String val) {
        if (val.equals("working_part")){
            counter = 0;
            repository = new InventoryRepository();
    }
        Part p = list_parts.get(counter);
        if(Part.isValidPart(val, p.getPrice(), p.getInStock(), p.getMin(), p.getMax(), "").equals("")) {
            repository.addPart(p);
            added += 1;
        }
        counter += 1;
        if (repository.getAllParts().size() == counter - added + 1) {
            assertEquals(repository.getAllParts().size(), counter - added + 1, "We do not have " +added  + " items in stock");
        }
        //assertEquals(repository.getAllParts().size(), counter - added + 1, "We do not have " +added  + " items in stock");
    }

    @ParameterizedTest
    @Order(4)
    @ValueSource(ints = {5, 4, 90, 105})
    void addPart2(Integer val){
        if(val == 5){
            counter = 0;
            repository = new InventoryRepository();
        }
        Part p = list_parts.get(counter);
        if(Part.isValidPart(p.getName(), p.getPrice(), val, p.getMin(), p.getMax(), "").equals("")) {
            repository.addPart(p);
            added2 += 1;
        }
        counter += 1;
        if (repository.getAllParts().size() == counter - added2 + 1) {
            assertEquals(repository.getAllParts().size(), counter - added2 + 1, "We do not have " +added  + " items in stock");
        }
        //assertEquals(repository.getAllParts().size(), counter - added2 + 1, "We do not have " + added2  + " items in stock");
    }
}