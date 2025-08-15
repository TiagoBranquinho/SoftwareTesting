package badIceCream.GUI;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

class GUITest {
    @Test
    void testScreenMethodCalls() throws IOException {
        Terminal terminal = mock(Terminal.class);
        when(terminal.getTerminalSize()).thenReturn(new TerminalSize(80, 24));

        GUI gui = new GameGraphics(80, 40);
        Screen screen = gui.createScreen(terminal);

        assertNotNull(screen);
    }
}