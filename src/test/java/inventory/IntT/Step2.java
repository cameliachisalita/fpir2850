package inventory.IntT;

import inventory.model.InhousePart;
import inventory.model.Part;
import inventory.repository.InventoryRepository;
import inventory.service.InventoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class Step2 {
    private InventoryRepository inventoryRepositoryr = new InventoryRepository();
    private InventoryService inventoryService = new InventoryService(inventoryRepositoryr);

    @Mock
    InhousePart p1;

    @Test
    public void TestAdd(){
        inventoryService.addInhousePart(p1.getName(),p1.getPrice(),p1.getInStock(),p1.getMin(),p1.getMax(),p1.getMachineId());

        assertEquals(1,inventoryService.getAllParts().size());

        for(Part part: inventoryService.getAllParts()){
            if (Objects.equals(part.getName(), p1.getName())){
                p1 = (InhousePart) part;
            }
        }

        inventoryService.deletePart(p1);
    }

    @Test
    public void TestDelete(){
        inventoryService.addInhousePart(p1.getName(),p1.getPrice(),p1.getInStock(),p1.getMin(),p1.getMax(),p1.getMachineId());

        for(Part part: inventoryService.getAllParts()){
            if (Objects.equals(part.getName(), p1.getName())){
                p1 = (InhousePart) part;
            }
        }

        inventoryService.deletePart(p1);

        assertEquals(0,inventoryService.getAllParts().size());
    }

    @Test
    public void testAll() {
        TestAdd();
        TestDelete();
    }
}
