package badIceCream.viewer.menu;

import badIceCream.GUI.Graphics;
import badIceCream.model.Position;
import badIceCream.model.menu.InstructionsMenuSecondPage;
import badIceCream.model.menu.LevelCompletedMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.*;

public class LevelCompletedMenuViewerTest {

    private LevelCompletedMenuViewer viewer;
    private LevelCompletedMenu mockMenu;
    private Graphics mockGraphics;

    @BeforeEach
    void setUp() {
        mockMenu = mock(LevelCompletedMenu.class);
        mockGraphics = mock(Graphics.class);
        viewer = new LevelCompletedMenuViewer(mockMenu);
    }

    @Test
    void testDrawTitle() {
        viewer.drawTitle(mockGraphics);

        String s0 = "  _                              _      _____                               _          _  ";
        String s1 = " | |                            | |    / ____|                             | |        | | ";
        String s2 = " | |        ___  __   __   ___  | |   | |        ___    _ __ ___    _ __   | |   ___  | |_    ___  ";
        String s3 = " | |       / _ \\ \\ \\ / /  / _ \\ | |   | |       / _ \\  | '_ ` _ \\  | '_ \\  | |  / _ \\ | __|  / _ \\ ";
        String s4 = " | |____  |  __/  \\ V /  |  __/ | |   | |____  | (_) | | | | | | | | |_) | | | |  __/ | |_  |  __/ ";
        String s5 = " |______|  \\___|   \\_/    \\___| |_|    \\_____|  \\___/  |_| |_| |_| | .__/  |_|  \\___|  \\__|  \\___| ";
        String s6 = "                                                                   | | ";
        String s7 = "                                                                   |_|   ";

        List<String> title = List.of(s0, s1, s2, s3, s4, s5, s6, s7);

        for(int i = 0; i < 6; i++){
            verify(mockGraphics).drawText(new Position(24, 2 + i), title.get(i), "  #f7dc6f  ");
        }

        for(int i = 6; i < 8; i++){
            verify(mockGraphics).drawText(new Position(24, 2 + i), title.get(i), " #f7dc6f ");
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
                List.of(" #f70d09  ", " #f70d09  ", " #f70d09  ", " #f70d09  ", " #f70d09  ", " #f70d09  ", " #f70d09 ", " #f70d09 "),
                List.of("  #8bf117  ", "  #8bf117  ", "  #8bf117  ", "  #8bf117  ", "  #8bf117  ", "  #8bf117  ", "  #8bf117 ", "  #8bf117 "),
                List.of("  #56b6f4  ", "  #56b6f4  ", "  #56b6f4  ", "  #56b6f4  ", "  #56b6f4  ", "  #56b6f4  ", " #56b6f4 ", " #56b6f4 "),
                List.of("  #fc9a02  ", "  #fc9a02  ", "  #fc9a02  ", "  #fc9a02  ", "  #fc9a02  ", "  #fc9a02  ", " #fc9a02 ", " #fc9a02 "),
                List.of("  #ff53f7  ", "  #ff53f7  ", "  #ff53f7  ", "  #ff53f7  ", "  #ff53f7  ", "  #ff53f7  ", " #ff53f7 ", " #ff53f7 "));

        for(int i = 0; i < 8; i++){
            verify(mockGraphics).drawText(new Position(15, 25 + i), snowflake.get(i), colors.get(0).get(i));
            verify(mockGraphics).drawText(new Position(5, 1 + i), snowflake.get(i), colors.get(1).get(i));
            verify(mockGraphics).drawText(new Position(70, 33 + i), snowflake.get(i), colors.get(2).get(i));
            verify(mockGraphics).drawText(new Position(100, 20 + i), snowflake.get(i), colors.get(3).get(i));
            verify(mockGraphics).drawText(new Position(120, 11 + i), snowflake.get(i), colors.get(4).get(i));

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

        verify(mockGraphics, atLeastOnce()).drawText(any(Position.class), anyString(), eq(" #f70d09  "));
        verify(mockGraphics, atLeastOnce()).drawText(any(Position.class), anyString(), eq("  #8bf117  "));
        verify(mockGraphics, atLeastOnce()).drawText(any(Position.class), anyString(), eq("  #56b6f4  "));
        verify(mockGraphics, atLeastOnce()).drawText(any(Position.class), anyString(), eq("  #fc9a02  "));
        verify(mockGraphics, atLeastOnce()).drawText(any(Position.class), anyString(), eq("  #ff53f7  "));

        verify(mockGraphics, times(1)).drawText(new Position(65, 17), "A", "#D1D100"); // Selected entry
        verify(mockGraphics, times(1)).drawText(new Position(65, 21), "B", "#FFFFFF");    // Unselected entry
    }
}
