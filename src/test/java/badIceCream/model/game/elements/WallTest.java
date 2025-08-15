package badIceCream.model.game.elements;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WallTest {
    @Test
    public void testGetType() {
        Wall stoneWall = new StoneWall(0, 0);
        Wall iceWall = new IceWall(0, 0);
        Assertions.assertEquals(2, stoneWall.getType());
        Assertions.assertEquals(1, iceWall.getType());
    }
}
