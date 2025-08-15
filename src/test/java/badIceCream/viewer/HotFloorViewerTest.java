package badIceCream.viewer;

import badIceCream.GUI.Graphics;
import badIceCream.model.Position;
import badIceCream.model.game.elements.HotFloor;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class HotFloorViewerTest {
    @Test
    public void testDraw() {
        HotFloorViewer hotFloorViewer = new HotFloorViewer();
        Graphics gui = mock(Graphics.class);
        HotFloor hotFloor = mock(HotFloor.class);
        Position position = mock(Position.class);
        when(hotFloor.getPosition()).thenReturn(position);
        hotFloorViewer.draw(hotFloor, gui, 0);

        verify(gui).drawHotFloor(position, 0);
    }
}
