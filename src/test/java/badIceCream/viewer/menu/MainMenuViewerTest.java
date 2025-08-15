package badIceCream.viewer.menu;

import badIceCream.GUI.Graphics;
import badIceCream.model.Position;
import badIceCream.model.menu.LevelCompletedMenu;
import badIceCream.model.menu.MainMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class MainMenuViewerTest {

    private MainMenuViewer viewer;
    private MainMenu mockMenu;
    private Graphics mockGraphics;

    @BeforeEach
    void setUp() {
        mockMenu = mock(MainMenu.class);
        mockGraphics = mock(Graphics.class);
        viewer = new MainMenuViewer(mockMenu);
    }

    @Test
    void testDrawTitle() {
        viewer.drawTitle(mockGraphics);

        String s0 = "                        ....                .                                     ";
        String s1 = "                       |  . \\              | |                                    ";
        String s2 = "                       | |.) |   .. .    ..| |                                    ";
        String s3 = "                       |  . <   / .` |  / .` |                                    ";
        String s4 = "                       | |.) | | (.| | | (.| |                                    ";
        String s5 = "                       |...../  \\..,.|  \\..,.|                                    ";
        String s6 = "                                                                                   ";
        String s7 = "                                                                                   ";
        String s8 = "  .....                            .....                                           ";
        String s9 = " |.   .|                          / ....|                                          ";
        String s10= "   | |     ...    ...    ......  | |       . ..    ...    .. .   . .. ...        ";
        String s11= "   | |    / ..|  / . \\  |......| | |      | '..|  / . \\  / .` | | '. ` . \\      ";
        String s12= "  .| |.  | (..  |  ../           | |....  | |    |  ../ | (.| | | | | | | |      ";
        String s13= " |.....|  \\...|  \\...|            \\.....| |.|     \\...|  \\..,.| |.| |.| |.|      ";

        List<String> title = List.of(s0, s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12, s13);

        for(int i = 0; i < 6; i++){
            verify(mockGraphics).drawText(new Position(35, 1 + i), title.get(i), "  #f7dc6f  ");
        }

        for(int i = 6; i < 9; i++){
            verify(mockGraphics).drawText(new Position(35, 1 + i), title.get(i), " #bb8fce ");
        }

        for(int i = 9; i < 14; i++){
            verify(mockGraphics).drawText(new Position(35, 1 + i), title.get(i), " #bb8fce ");
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

        verify(mockGraphics, times(1)).drawText(new Position(63, 20), "A", "#D1D100"); // Selected entry
        verify(mockGraphics, times(1)).drawText(new Position(63, 24), "B", "#FFFFFF");    // Unselected entry
    }
}
