package badIceCream.model.game.elements.fruits;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FruitTest {
    @Test
    public void testGetType() {
        Fruit apple = new AppleFruit(0, 0);
        Fruit banana = new BananaFruit(0, 0);
        Fruit cherry = new CherryFruit(0, 0);
        Fruit pineapple = new PineappleFruit(0, 0);
        Fruit strawberry = new StrawberryFruit(0, 0);

        Assertions.assertEquals(1, apple.getType());
        Assertions.assertEquals(2, banana.getType());
        Assertions.assertEquals(3, cherry.getType());
        Assertions.assertEquals(4, pineapple.getType());
        Assertions.assertEquals(5, strawberry.getType());
    }
}
