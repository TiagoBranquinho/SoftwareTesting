package badIceCream.viewer.menu;

import badIceCream.GUI.Graphics;
import badIceCream.model.Position;
import badIceCream.model.menu.GameOverMenu;
import net.jqwik.api.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class GameOverMenuViewerTest {

    private GameOverMenu mockMenu;
    private Graphics mockGraphics;
    private GameOverMenuViewer gameOverMenuViewer;

    @BeforeEach
    void setUp() {
        mockMenu = mock(GameOverMenu.class);
        mockGraphics = mock(Graphics.class);
        gameOverMenuViewer = new GameOverMenuViewer(mockMenu);
    }

    @Test
    void testDrawTitle() {
        gameOverMenuViewer.drawTitle(mockGraphics);

        verify(mockGraphics).drawText(new Position(37, 4), "  _____                                 ____                            ", "#f6160f");
        verify(mockGraphics).drawText(new Position(37, 5), " / ____|                               / __ \\                           ", "#f6160f");
        verify(mockGraphics).drawText(new Position(37, 6), "| |  __    __ _   _ __ ___     ___    | |  | | __   __   ___   _ __    ", "#f6160f");
        verify(mockGraphics).drawText(new Position(37, 7), "| | |_ |  / _` | | '_ ` _ \\   / _ \\   | |  | | \\ \\ / /  / _ \\ | '__|   ", "#f6160f");
        verify(mockGraphics).drawText(new Position(37, 8), "| |__| | | (_| | | | | | | | |  __/   | |__| |  \\ V /  |  __/ | |      ", "#f6160f");
        verify(mockGraphics).drawText(new Position(37, 9), " \\_____|  \\__,_| |_| |_| |_|  \\___|    \\____/    \\_/    \\___| |_|       ", "#f6160f");
    }

    @Test
    void testDrawSnowflake() {
        gameOverMenuViewer.drawSnowflake(mockGraphics);

        String s0 = "   ..    ..          ";
        String s1 = "   '\\    /'         ";
        String s2 = "     \\\\//          ";
        String s3 = "_.__\\\\\\///__._    ";
        String s4 = " '  ///\\\\\\  '     ";
        String s5 = "     //\\\\          ";
        String s6 = "   ./    \\.         ";
        String s7 = "   ''    ''          ";


        List<String> snowflake = List.of(s0, s1, s2, s3, s4, s5, s6, s7);

        List<List<String>> colors = List.of(
            List.of("#ffffff", "#ffffff", "#ffffff", "#ffffff", "#ffffff", "#ffffff", "#ffffff", "#ffffff"),
            List.of("  #ffffff  ", "  #ffffff  ", "  #ffffff  ", "  #ffffff  ", "  #ffffff  ", "  #ffffff  ", " #ffffff ", " #ffffff "),
            List.of("  #ffffff  ", "  #ffffff  ", "  #ffffff  ", "  #ffffff  ", "  #ffffff  ", "  #ffffff  ", " #ffffff ", " #ffffff "),
            List.of("  #ffffff  ", "  #ffffff  ", "  #ffffff  ", "  #ffffff  ", "  #ffffff  ", "  #ffffff  ", " #ffffff ", " #ffffff "),
            List.of("  #ffffff  ", "  #ffffff  ", "  #ffffff  ", "  #ffffff  ", "  #ffffff  ", "  #ffffff  ", " #ffffff ", " #ffffff "));

        for(int i = 0; i < 8; i++){
            verify(mockGraphics).drawText(new Position(15, 25 + i), snowflake.get(i), colors.get(0).get(i));
            verify(mockGraphics).drawText(new Position(5, 1 + i), snowflake.get(i), colors.get(1).get(i));
            verify(mockGraphics).drawText(new Position(70, 33 + i), snowflake.get(i), colors.get(2).get(i));
            verify(mockGraphics).drawText(new Position(100, 20 + i), snowflake.get(i), colors.get(3).get(i));
            verify(mockGraphics).drawText(new Position(120, 7 + i), snowflake.get(i), colors.get(4).get(i));

        }
    }

    @Test
    void testDrawElements() {
        when(mockMenu.getNumberEntries()).thenReturn(2);
        when(mockMenu.getEntry(0)).thenReturn("A");
        when(mockMenu.getEntry(1)).thenReturn("B");
        when(mockMenu.isSelected(0)).thenReturn(true);
        when(mockMenu.isSelected(1)).thenReturn(false);

        gameOverMenuViewer.drawElements(mockGraphics);

        verify(mockGraphics, times(1)).drawText(any(Position.class), eq("  _____                                 ____                            "), eq("#f6160f"));
        verify(mockGraphics, times(1)).drawText(any(Position.class), eq("   ..    ..          "), eq("#ffffff"));

        verify(mockGraphics, times(1)).drawText(new Position(65, 17), "A", "#D1D100"); // Selected entry
        verify(mockGraphics, times(1)).drawText(new Position(65, 21), "B", "#FFFFFF");    // Unselected entry
    }

}
