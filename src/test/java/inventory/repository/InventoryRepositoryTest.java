package inventory.repository;


import inventory.model.Part;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class InventoryRepositoryTest {
    InventoryRepository repository;
    List<Part> list_parts;
    Integer counter;
    int added, added2;

    @BeforeAll
    void initCounter() {
        counter = 0;
        added = 0;
        added2 = 0;
        System.out.println("Mock repository created");
    }

    @Test
    @Order(1)
    void createList() {
        list_parts = new ArrayList<Part>();

        list_parts.add(new Part(0, "working_part", 1.00, 50, 5, 100));
        list_parts.add(new Part(2, "", 1.00, 10, 5, 100));
        list_parts.add(new Part(1, "working_part2", 1.00, 40, 5, 100));
        list_parts.add(new Part(3, "Compresor", 1.00, 101, 5, 100));

        assertEquals(4, list_parts.size(), "Not all 4 parts have been added");
    }

    @Test
    @Order(2)
    void addIncorrectPartsInRepoECP() {
        InventoryRepository inventoryRepository = new InventoryRepository();

        inventoryRepository.addPart(new Part(0, "working_part", 1.00, 50, 5, 100));
        if (Objects.equals(Part.isValidPart("", 1.00, 10, 5, 100, ""), "")) {
            inventoryRepository.addPart(new Part(2, "", 1.00, 10, 5, 100));
        }
        inventoryRepository.addPart(new Part(1, "working_part2", 1.00, 40, 5, 100));
        if (Objects.equals(Part.isValidPart("Compresor", 1.00, 101, 5, 100, ""), "")) {
            inventoryRepository.addPart(new Part(3, "Compresor", 1.00, 101, 5, 100));
        }

        assertEquals(2, inventoryRepository.getAllParts().size(), "The invalid part objects have been incorrectly added.");
    }

    @Test
    @Order(3)
    void addIncorrectPartsInRepoECP2() {
        InventoryRepository inventoryRepository = new InventoryRepository();

        inventoryRepository.addPart(new Part(0, "working_part", 1.00, 50, 5, 100));
        if (Objects.equals(Part.isValidPart("", 1.00, 10, 5, 100, ""), "")) {
            inventoryRepository.addPart(new Part(2, "", 1.00, 10, 5, 100));
        }
        inventoryRepository.addPart(new Part(1, "working_part2", 1.00, 40, 5, 100));
        if (Objects.equals(Part.isValidPart("Compresor", 1.00, 101, 5, 100, ""), "")) {
            inventoryRepository.addPart(new Part(3, "Compresor", 1.00, 101, 5, 100));
        }

        assertEquals(2, inventoryRepository.getAllParts().size(), "The invalid part objects have been incorrectly added.");
    }

    @Test
    @Order(4)
    void addCorrectPartsInRepoECP() {
        InventoryRepository inventoryRepository = new InventoryRepository();

        inventoryRepository.addPart(new Part(0, "working_part", 1.00, 50, 5, 100));
        inventoryRepository.addPart(new Part(2, "Some_other_working_part", 1.00, 10, 5, 100));
        inventoryRepository.addPart(new Part(1, "working_part2", 1.00, 40, 5, 100));
        inventoryRepository.addPart(new Part(3, "Some_other_working_part", 1.00, 20, 5, 100));

        assertEquals(4, inventoryRepository.getAllParts().size(), "The invalid part objects have been incorrectly added.");
    }

    @Test
    @Order(5)
    void addCorrectPartsInRepoECP2() {
        InventoryRepository inventoryRepository = new InventoryRepository();

        inventoryRepository.addPart(new Part(0, "working_part", 1.00, 50, 5, 100));
        inventoryRepository.addPart(new Part(2, "Some_other_working_part", 1.00, 10, 5, 100));
        inventoryRepository.addPart(new Part(1, "working_part2", 1.00, 40, 5, 100));
        inventoryRepository.addPart(new Part(3, "Some_other_working_part", 1.00, 20, 5, 100));

        assertEquals(4, inventoryRepository.getAllParts().size(), "The invalid part objects have been incorrectly added.");
    }

    @Test
    @Order(6)
    //@Disabled("These tests have flaws")
    void addIncorrectPartsInRepoBVA() {
        InventoryRepository inventoryRepository = new InventoryRepository();

        inventoryRepository.addPart(new Part(0, "Compresor", 3000.00, 50, 5, 100));
        if (Objects.equals(Part.isValidPart("", 3000.00, 10, 5, 100, ""), "")) {
            inventoryRepository.addPart(new Part(1, "", 3000.00, 10, 5, 100));
        }
        inventoryRepository.addPart(new Part(2, "Compresor", 3000.00, 40, 5, 100));
        if (Objects.equals(Part.isValidPart("Compresor", 1.00, 101, 5, 100, ""), "")) {
            inventoryRepository.addPart(new Part(3, "Compresor", 3000.00, 101, 5, 100));
        }

        assertEquals(2, inventoryRepository.getAllParts().size(), "The invalid part objects have been incorrectly added.");
    }

    @Test
    @Order(7)
        //@Disabled("These tests have flaws")
    void addIncorrectPartsInRepoBVA2() {
        InventoryRepository inventoryRepository = new InventoryRepository();

        inventoryRepository.addPart(new Part(0, "Compresor", 3000.00, 50, 5, 100));
        if (Objects.equals(Part.isValidPart("", 3000.00, 10, 5, 100, ""), "")) {
            inventoryRepository.addPart(new Part(1, "", 3000.00, 10, 5, 100));
        }
        inventoryRepository.addPart(new Part(2, "Compresor", 3000.00, 40, 5, 100));
        if (Objects.equals(Part.isValidPart("Compresor", 1.00, 101, 5, 100, ""), "")) {
            inventoryRepository.addPart(new Part(3, "Compresor", 3000.00, 101, 5, 100));
        }

        assertEquals(2, inventoryRepository.getAllParts().size(), "The invalid part objects have been incorrectly added.");
    }

    @Test
    @Order(8)
    void addCorrectPartsInRepoBVA() {
        InventoryRepository inventoryRepository = new InventoryRepository();

        inventoryRepository.addPart(new Part(0, "Compresor", 3000.00, 50, 5, 100));
        inventoryRepository.addPart(new Part(2, "Compresor", 3000.00, 10, 5, 100));
        inventoryRepository.addPart(new Part(1, "Compresor", 3000.00, 40, 5, 100));
        inventoryRepository.addPart(new Part(3, "Compresor", 3000.00, 20, 5, 100));

        assertEquals(4, inventoryRepository.getAllParts().size(), "The invalid part objects have been incorrectly added.");
    }

    @Test
    @Order(9)
    void addCorrectPartsInRepoBVA2() {
        InventoryRepository inventoryRepository = new InventoryRepository();

        inventoryRepository.addPart(new Part(0, "Compresor", 3000.00, 50, 5, 100));
        inventoryRepository.addPart(new Part(2, "Compresor", 3000.00, 10, 5, 100));
        inventoryRepository.addPart(new Part(1, "Compresor", 3000.00, 40, 5, 100));
        inventoryRepository.addPart(new Part(3, "Compresor", 3000.00, 20, 5, 100));

        assertEquals(4, inventoryRepository.getAllParts().size(), "The invalid part objects have been incorrectly added.");
    }
}