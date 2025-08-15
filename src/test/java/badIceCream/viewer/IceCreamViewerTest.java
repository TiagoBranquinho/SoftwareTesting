package badIceCream.viewer;

import badIceCream.GUI.GUI;
import badIceCream.GUI.Graphics;
import badIceCream.model.Position;
import badIceCream.model.game.elements.HotFloor;
import badIceCream.model.game.elements.IceCream;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class IceCreamViewerTest {
    @Test
    public void testDraw() {
        IceCreamViewer iceCreamViewer = new IceCreamViewer();
        Graphics gui = mock(Graphics.class);
        IceCream iceCream = mock(IceCream.class);
        Position position = mock(Position.class);
        when(iceCream.getPosition()).thenReturn(position);
        when(iceCream.isStrawberryActive()).thenReturn(false);
        when(iceCream.getLastMovement()).thenReturn(GUI.ACTION.UP);
        iceCreamViewer.draw(iceCream, gui, 0);

        verify(gui).drawIceCream(position, GUI.ACTION.UP, false);
    }
}
