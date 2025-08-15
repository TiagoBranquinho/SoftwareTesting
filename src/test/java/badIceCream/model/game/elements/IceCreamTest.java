package badIceCream.model.game.elements;

import badIceCream.GUI.GUI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IceCreamTest {

    private IceCream iceCream;
    @BeforeEach
    public void setUp() {
        iceCream = new IceCream(0, 0);
    }
    @Test
    public void testGetType() {
        Assertions.assertEquals(1, iceCream.getType());
    }

    @Test
    public void testMovement() {
        Assertions.assertEquals(iceCream.getLastMovement(), GUI.ACTION.DOWN);
        iceCream.setLastMovement(GUI.ACTION.UP);
        Assertions.assertEquals(iceCream.getLastMovement(), GUI.ACTION.UP);
    }

    @Test
    public void testAlive() {
        Assertions.assertTrue(iceCream.getAlive());
        iceCream.changeAlive();
        Assertions.assertFalse(iceCream.getAlive());
        iceCream.changeAlive();
        Assertions.assertTrue(iceCream.getAlive());
    }

    @Test
    public void testStrawberry() {
        Assertions.assertFalse(iceCream.isStrawberryActive());
        iceCream.setStrawberry(true);
        Assertions.assertTrue(iceCream.isStrawberryActive());
    }
}
