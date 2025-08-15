package badIceCream.model.game.elements;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HotFloorTest {
    @Test
    public void testGetType() {
        HotFloor hotFloor = new HotFloor(0, 0);
        Assertions.assertEquals(0, hotFloor.getType());
    }
}
