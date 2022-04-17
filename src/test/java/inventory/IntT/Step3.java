package inventory.IntT;

import inventory.model.InhousePart;
import inventory.repository.InventoryRepository;
import inventory.service.InventoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class Step3 {
    private InventoryRepository inventoryRepository = new InventoryRepository();
    private InventoryService inventoryService = new InventoryService(inventoryRepository);

    @Test
    public void TestAdd(){
        InhousePart p1 = new InhousePart(1, "working_part",1000.1,50,10,60,1);

        inventoryService.addInhousePart(p1.getName(),p1.getPrice(),p1.getInStock(),p1.getMin(),p1.getMax(),p1.getMachineId());

        assertEquals(inventoryService.lookupPart(p1.getName()).getPrice(), p1.getPrice());
        assertEquals(inventoryService.lookupPart(p1.getName()).getInStock(), p1.getInStock());
        assertEquals(inventoryService.lookupPart(p1.getName()).getName(), p1.getName());

        InhousePart p2 = (InhousePart) inventoryService.lookupPart("working_part");

        inventoryService.deletePart(p2);
    }

    @Test
    public void TestDelete(){
        InhousePart p1 = new InhousePart(1, "working_part",1000.1,50,10,60,1);

        inventoryService.addInhousePart(p1.getName(),p1.getPrice(),p1.getInStock(),p1.getMin(),p1.getMax(),p1.getMachineId());

        InhousePart p2 = (InhousePart) inventoryService.lookupPart("working_part");

        inventoryService.deletePart(p2);

        assertEquals(0,inventoryService.getAllParts().size());
    }

}
