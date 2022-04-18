package inventory.IntT;

import inventory.model.InhousePart;
import inventory.model.Inventory;
import inventory.model.Part;
import inventory.repository.InventoryRepository;
import inventory.service.InventoryService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class Step1 {

    @Mock
    private Part thePart;

    @Spy
    private InventoryRepository inventoryrepository = new InventoryRepository();
    @Spy
    private InventoryService inventoryservice = new InventoryService(inventoryrepository);

    @Test
    @Order(1)
    public void verifyPart(){
        Mockito.when(thePart.getName()).thenReturn("working_part");
        Mockito.when(thePart.getPrice()).thenReturn(1.00);
        Mockito.when(thePart.getInStock()).thenReturn(50);
        Mockito.when(thePart.getMin()).thenReturn(5);
        Mockito.when(thePart.getMax()).thenReturn(100);

        assertEquals("", Part.isValidPart(thePart.getName()
                , thePart.getPrice()
                , thePart.getInStock()
                , thePart.getMin()
                , thePart.getMax(), ""));
    }

    @Test
    @Order(2)
    public void verifyPart2(){
        Mockito.when(thePart.getName()).thenReturn("nonworking_part");
        Mockito.when(thePart.getPrice()).thenReturn(1.00);
        Mockito.when(thePart.getInStock()).thenReturn(-1);
        Mockito.when(thePart.getMin()).thenReturn(5);
        Mockito.when(thePart.getMax()).thenReturn(100);

        assertEquals("Inventory level must be greater than 0. Inventory level is lower than minimum value. ",
                Part.isValidPart(thePart.getName()
                , thePart.getPrice()
                , thePart.getInStock()
                , thePart.getMin()
                , thePart.getMax(), ""));
    }

    @Test
    @Order(3)
    public void verifyRepo(){
        Mockito.spy(inventoryrepository).addPart(thePart);
        Mockito.spy(inventoryrepository).addPart(thePart);

        assertEquals(2, inventoryrepository.getAllParts().size());
    }

    @Test
    @Order(4)
    public void verifyRepo2(){
        inventoryrepository.deletePart(thePart);
        inventoryrepository.deletePart(thePart);

        assertEquals(0,inventoryrepository.getAllParts().size());
    }

    @Test
    @Order(5)
    public void verifyService(){
        Mockito.spy(inventoryservice).addInhousePart(thePart.getName(), thePart.getPrice(), thePart.getInStock(), thePart.getMin(), thePart.getMax(), 1);

        assertEquals(1, inventoryservice.getAllParts().size());

        for(Part part: inventoryservice.getAllParts()){
            if (Objects.equals(part.getName(), thePart.getName())){
                thePart = (InhousePart) part;
            }
        }

        inventoryservice.deletePart(thePart);
    }

    @Test
    @Order(6)
    public void verifyService2(){
        assertEquals(0, inventoryservice.getAllParts().size());
    }

    @Test
    public void testAll() {
        verifyPart();
        verifyPart2();
        verifyRepo();
        verifyRepo2();
        verifyService();
        verifyService2();
    }
}
