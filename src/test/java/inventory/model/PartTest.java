package inventory.model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PartTest {
    private String name;
    private int inStock;
    private Part part;

    @BeforeAll
    void setUp() {
        name = "Compresor";
        inStock = 50;
        part = new Part(1, name, 5000, inStock, 0, 100);
    }

    @Test
    @Order(1)
    void getName() {
        assertEquals("Compresor", part.getName(), "Part name should be \'Compresor\'");
    }

    @Test
    @Order(2)
    void getInStock() {
        assertEquals(50, part.getInStock(), "Part inStock should be \'50\'");
    }

    @Test
    @Disabled("test ignored, equivalent to @Ignored in Junit 4.x")
    void setName() {
        part.setName("ABC");
        assertEquals("ABC", part.getName(), "Part name should be \'ABC\'");
    }

    @Test
    @Disabled("test ignored, equivalent to @Ignored in Junit 4.x")
    void setInStock() {
        part.setInStock(60);
        assertEquals(60, part.getInStock(), "Part inStock should be \'60\'");
    }

    @Test
    @AfterAll
    void createPart() {
        Part part1 = new Part(1, "lab", 5000, inStock, 0, 100);

        assert part1 != null;
    }
}