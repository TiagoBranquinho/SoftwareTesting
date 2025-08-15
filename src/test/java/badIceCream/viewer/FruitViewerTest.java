package badIceCream.viewer;

import badIceCream.GUI.Graphics;
import badIceCream.model.Position;
import badIceCream.model.game.elements.fruits.Fruit;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.mockito.Mockito.*;

public class FruitViewerTest {

    @ParameterizedTest
    @CsvSource({
            "1",
            "2",
            "3",
            "4",
            "5"
    })
    void testDrawFruits(int type) {
        Fruit mockFruit = mock(Fruit.class);
        Graphics mockGraphics = mock(Graphics.class);
        Position mockPosition = new Position(3, 3);

        when(mockFruit.getPosition()).thenReturn(mockPosition);

        FruitViewer fruitViewer = new FruitViewer();
        fruitViewer.draw(mockFruit, mockGraphics, type);

        switch (type) {
            case 1 -> verify(mockGraphics).drawAppleFruit(mockPosition);
            case 2 -> verify(mockGraphics).drawBananaFruit(mockPosition);
            case 3 -> verify(mockGraphics).drawCherryFruit(mockPosition);
            case 4-> verify(mockGraphics).drawPineappleFruit(mockPosition);
            case 5 -> verify(mockGraphics).drawStrawberryFruit(mockPosition);
        }
    }
}
