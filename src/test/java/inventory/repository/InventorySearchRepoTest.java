package inventory.repository;


import inventory.model.Part;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class InventorySearchRepoTest {
    InventoryRepository repository;

    @BeforeAll
    void initRepo() {
        repository = new InventoryRepository();

        repository.addPart(new Part(0, "Compressor", 5000.00, 75, 5, 100));
        repository.addPart(new Part(1, "Compressor2", 3000.00, 50, 5, 100));
        repository.addPart(new Part(2, "Compressor3", 4500.00, 25, 5, 100));
        repository.addPart(new Part(3, "Compressor4", 3500.00, 100, 5, 100));
        repository.addPart(new Part(4, "Compressor5", 3500.00, 100, 5, 100));
        repository.addPart(new Part(5, "Compressor6", 3500.00, 100, 5, 100));
        repository.addPart(new Part(6, "Compressor7", 3500.00, 100, 5, 100));
        repository.addPart(new Part(7, "Compressor8", 3500.00, 100, 5, 100));
        repository.addPart(new Part(8, "Compressor9", 3500.00, 100, 5, 100));
        repository.addPart(new Part(9, "Compressor10", 3500.00, 100, 5, 100));

        assertEquals(10, repository.getAllParts().size(), "The invalid part objects have been incorrectly added.");
        System.out.println("Mock repository created");
    }

    @Test
    //@Order(1)
    void searchItemsInEmptyRepo() {
        InventoryRepository emptyRepo = new InventoryRepository();

        Part result = emptyRepo.lookupPart("Compressor");

        assertNull(result, "The search went wrong, it should be null!");
    }

    @Test
    //@Order(2)
    @Disabled("Equals is not overridden") // it's wrong only when the method "equals" is not overridden in the Part class
    void searchExistingItemsInRepoByNameWrong() {
        String title = "Compressor";
        Part part = new Part(0, "Compressor", 5000.00, 75, 5, 100);

        Part result = repository.lookupPart(title);

        assertEquals(part, result, "Equals is not overridden");
    }

    @Test
    //@Order(3)
    void searchExistingItemsInRepoByName() {
        String title = "Compressor";
        Part part = new Part(0, "Compressor", 5000.00, 75, 5, 100);

        Part result = repository.lookupPart(title);

        assertEquals(part.getPartId(), result.getPartId(), "The search went wrong!");
        assertEquals(part.getName(), result.getName(), "The search went wrong!");
        assertEquals(part.getPrice(), result.getPrice(), "The search went wrong!");
        assertEquals(part.getInStock(), result.getInStock(), "The search went wrong!");
        assertEquals(part.getMax(), result.getMax(), "The search went wrong!");
        assertEquals(part.getMin(), result.getMin(), "The search went wrong!");
    }

    @Test
    //@Order(4)
    void searchExistingItemsInRepoById() {
        String id = "1";
        Part part = new Part(1, "Compressor2", 3000.00, 50, 5, 100);

        Part result = repository.lookupPart(id);

        assertEquals(part.getPartId(), result.getPartId(), "The search went wrong!");
        assertEquals(part.getName(), result.getName(), "The search went wrong!");
        assertEquals(part.getPrice(), result.getPrice(), "The search went wrong!");
        assertEquals(part.getInStock(), result.getInStock(), "The search went wrong!");
        assertEquals(part.getMax(), result.getMax(), "The search went wrong!");
        assertEquals(part.getMin(), result.getMin(), "The search went wrong!");
    }

    @Test
    //@Order(5)
    void searchNonExistingItemsInRepo() {
        String title = "Alibaba";

        Part result1 = repository.lookupPart(title);

        assertNull(result1, "The search went wrong, it should be null!");
    }
}