package badIceCream.viewer.menu;

import badIceCream.GUI.Graphics;
import badIceCream.model.Position;
import badIceCream.model.menu.PauseMenu;
import badIceCream.model.menu.SelectLevelMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class SelectLevelMenuViewerTest {

    private SelectLevelMenuViewer viewer;
    private SelectLevelMenu mockMenu;
    private Graphics mockGraphics;

    @BeforeEach
    void setUp() {
        mockMenu = mock(SelectLevelMenu.class);
        mockGraphics = mock(Graphics.class);
        viewer = new SelectLevelMenuViewer(mockMenu);
    }

    @Test
    void testDrawTitle() {
        viewer.drawTitle(mockGraphics);

        String s0 = "   _                    _    _____      _           _              ";
        String s1 = "  | |                  | |  / ____|    | |         | |             ";
        String s2 = "  | |     _____   _____| | | (___   ___| | ___  ___| |_            ";
        String s3 = "  | |    / _ \\ \\ / / _ \\ |  \\___ \\ / _ \\ |/ _ \\/ __| __|    ";
        String s4 = "  | |___|  __/\\ V /  __/ |  ____) |  __/ |  __/ (__| |_           ";
        String s5 = "  |______\\___| \\_/ \\___|_| |_____/ \\___|_|\\___|\\___|\\__|    ";

        List<String> title = List.of(s0, s1, s2, s3, s4, s5);

        for(int i = 0; i < 6; i++){
            verify(mockGraphics).drawText(new Position(41, 1 + i), title.get(i), "  #f7dc6f  ");
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
            verify(mockGraphics).drawText(new Position(65, 33 + i), snowflake.get(i), colors.get(2).get(i));
            verify(mockGraphics).drawText(new Position(100, 26 + i), snowflake.get(i), colors.get(3).get(i));
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

        verify(mockGraphics).drawText(new Position(43, 17), " --- ", "#f76fe0");
        verify(mockGraphics).drawText(new Position(43, 18), "|   |", "#f76fe0");
        verify(mockGraphics).drawText(new Position(43, 19), " ---", "#f76fe0");

        verify(mockGraphics).drawText(new Position(55, 17), " --- ", "#f76fe0");
        verify(mockGraphics).drawText(new Position(55, 18), "|   |", "#f76fe0");
        verify(mockGraphics).drawText(new Position(55, 19), " ---", "#f76fe0");

        verify(mockGraphics).drawText(new Position(67, 17), " --- ", "#f76fe0");
        verify(mockGraphics).drawText(new Position(67, 18), "|   |", "#f76fe0");
        verify(mockGraphics).drawText(new Position(67, 19), " ---", "#f76fe0");

        verify(mockGraphics).drawText(new Position(79, 17), " --- ", "#f76fe0");
        verify(mockGraphics).drawText(new Position(79, 18), "|   |", "#f76fe0");
        verify(mockGraphics).drawText(new Position(79, 19), " ---", "#f76fe0");

        verify(mockGraphics).drawText(new Position(91, 17), " --- ", "#f76fe0");
        verify(mockGraphics).drawText(new Position(91, 18), "|   |", "#f76fe0");
        verify(mockGraphics).drawText(new Position(91, 19), " ---", "#f76fe0");

        verify(mockGraphics, times(1)).drawText(new Position(45, 18), "A", "#D1D100"); // Selected entry
        verify(mockGraphics, times(1)).drawText(new Position(57, 18), "B", "#FFFFFF");    // Unselected entry
    }
}
