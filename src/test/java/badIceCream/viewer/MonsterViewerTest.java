package badIceCream.viewer;

import badIceCream.GUI.GUI;
import badIceCream.GUI.Graphics;
import badIceCream.model.Position;
import badIceCream.model.game.elements.fruits.Fruit;
import badIceCream.model.game.elements.monsters.Monster;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.mockito.Mockito.*;

public class MonsterViewerTest {

    @ParameterizedTest
    @CsvSource({
            "1",
            "2",
            "3",
            "4",
            "5"
    })
    void testDrawMonsters(int type) {
        Monster mockMonster = mock(Monster.class);
        Graphics mockGraphics = mock(Graphics.class);
        Position mockPosition = new Position(3, 3);

        when(mockMonster.getPosition()).thenReturn(mockPosition);
        when(mockMonster.getLastAction()).thenReturn(GUI.ACTION.UP);
        when(mockMonster.isRunning()).thenReturn(true);

        MonsterViewer monsterViewer = new MonsterViewer();
        monsterViewer.draw(mockMonster, mockGraphics, type);

        switch (type) {
            case 1 -> verify(mockGraphics).drawDefaultMonster(mockPosition, GUI.ACTION.UP);
            case 2 -> verify(mockGraphics).drawJumperMonster(mockPosition, GUI.ACTION.UP);
            case 3 -> verify(mockGraphics).drawRunnerMonster(mockPosition, GUI.ACTION.UP, true);
            case 4-> verify(mockGraphics).drawWallBreakerMonster(mockPosition, GUI.ACTION.UP);
        }
    }
}
