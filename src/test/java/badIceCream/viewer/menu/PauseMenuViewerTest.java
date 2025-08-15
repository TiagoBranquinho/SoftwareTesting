package badIceCream.viewer.menu;

import badIceCream.GUI.Graphics;
import badIceCream.model.Position;
import badIceCream.model.menu.MainMenu;
import badIceCream.model.menu.PauseMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class PauseMenuViewerTest {

    private PauseMenuViewer viewer;
    private PauseMenu mockMenu;
    private Graphics mockGraphics;

    @BeforeEach
    void setUp() {
        mockMenu = mock(PauseMenu.class);
        mockGraphics = mock(Graphics.class);
        viewer = new PauseMenuViewer(mockMenu);
    }

    @Test
    void testDrawTitle() {
        viewer.drawTitle(mockGraphics);

        String s0 = "  _____                                            ";
        String s1 = " |  __ \\                                          ";
        String s2 = " | |__) |  __ _   _   _   ___    ___              ";
        String s3 = " |  ___/  / _` | | | | | / __|  / _ \\            ";
        String s4 = " | |     | (_| | | |_| | \\__ \\ |  __/           ";
        String s5 = " |_|      \\__,_|  \\__,_| |___/  \\___|          ";

        List<String> title = List.of(s0, s1, s2, s3, s4, s5);

        for(int i = 0; i < 6; i++){
            verify(mockGraphics).drawText(new Position(51, 3 + i), title.get(i), "  #f7dc6f  ");
        }


    }

    @Test
    void testDrawSnowflake() {
        viewer.drawSnowflake(mockGraphics);

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

        
        viewer.drawElements(mockGraphics);

        verify(mockGraphics, times(6)).drawText(any(Position.class), anyString(), eq("  #f7dc6f  "));

        verify(mockGraphics, atLeastOnce()).drawText(any(Position.class), anyString(), eq("  #f7dc6f  "));
        verify(mockGraphics, atLeastOnce()).drawText(any(Position.class), anyString(), eq("#ffffff"));
        verify(mockGraphics, atLeastOnce()).drawText(any(Position.class), anyString(), eq("  #ffffff  "));

        verify(mockGraphics, atLeastOnce()).drawText(any(Position.class), anyString(), eq("  #b05fa3  "));


        verify(mockGraphics, times(1)).drawText(new Position(68, 21), "A", "#D1D100"); // Selected entry
        verify(mockGraphics, times(1)).drawText(new Position(68, 24), "B", "#FFFFFF");    // Unselected entry
    }

    @Test
    void testPauseSymbol() {
        viewer.drawPauseSymbol(mockGraphics);

        String s1 = " __    _           ";
        String s2 = "|  |  | \\          ";
        String s3 = "|  |  |  \\         ";
        String s4 = "|  |  |   \\         ";
        String s5 = "|  |  |   /         ";
        String s6 = "|  |  |  /          ";
        String s7 = "|__|  |_/             ";

        List<String> title = List.of(s1, s2, s3, s4, s5, s6, s7);

        for(int i = 0; i < 7; i++){
            verify(mockGraphics).drawText(new Position(66, 10 + i), title.get(i), "  #b05fa3  ");
        }
    }
}
