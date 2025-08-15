package badIceCream.GUI;

import com.googlecode.lanterna.terminal.Terminal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static java.awt.Font.createFont;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class MenuGraphicsTest {

    private MenuGraphics menuGraphics;

    @BeforeEach
    void setUp() {
        menuGraphics = new MenuGraphics(10, 10);
    }

    @Test
    void testCreateTerminal() throws IOException {
        File mockFile = mock(File.class);
        when(mockFile.getPath()).thenReturn("mock/path/to/font.otf");

        Font mockFont = mock(Font.class);
        when(mockFont.deriveFont(anyFloat())).thenReturn(mockFont);

        Terminal terminal = menuGraphics.createTerminal();

        assertNotNull(terminal);
    }

    @Test
    void testCreateTerminalWithFontFormatException() throws IOException, FontFormatException {
        try (MockedStatic<Font> font = mockStatic(Font.class)) {
            File mockFile = mock(File.class);
            when(mockFile.getPath()).thenReturn("invalid/path/to/font.otf");


            FontFormatException spy = spy(new FontFormatException("Mock FontFormatException"));
            font.when(() -> createFont(eq(Font.TRUETYPE_FONT), any(File.class)))
                    .thenThrow(spy);


            IOException exception = Assertions.assertThrows(IOException.class, () -> menuGraphics.createTerminal());
            Assertions.assertTrue(exception.getMessage().contains("Error creating terminal with custom font."));

            verify(spy).printStackTrace();
        }
    }

}