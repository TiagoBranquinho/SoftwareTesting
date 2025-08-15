package badIceCream.viewer;

import badIceCream.GUI.GUI;
import badIceCream.GUI.Graphics;
import badIceCream.model.Position;
import badIceCream.model.game.elements.Wall;
import badIceCream.model.game.elements.monsters.Monster;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.mockito.Mockito.*;

public class WallViewerTest {

    @ParameterizedTest
    @CsvSource({
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            "11"

    })
    void testDrawWalls(int type) {
        Wall mockWall = mock(Wall.class);
        Graphics mockGraphics = mock(Graphics.class);
        Position mockPosition = new Position(3, 3);

        when(mockWall.getPosition()).thenReturn(mockPosition);

        WallViewer wallViewer = new WallViewer();
        wallViewer.draw(mockWall, mockGraphics, type);

        switch (type) {
            case 1, 3, 4, 5, 6, 7, 8, 9, 10 , 11 -> verify(mockGraphics).drawIceWall(mockPosition, type);
            case 2 -> verify(mockGraphics).drawStoneWall(mockPosition);
        }
    }
}
