package badIceCream.viewer.menu;

import badIceCream.GUI.Graphics;
import badIceCream.model.Position;
import badIceCream.model.menu.InstructionsMenuFirstPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class InstructionsMenuFirstPageViewerTest {

    private InstructionsMenuFirstPageViewer viewer;
    private InstructionsMenuFirstPage mockMenu;
    private Graphics mockGraphics;

    @BeforeEach
    void setUp() {
        mockMenu = mock(InstructionsMenuFirstPage.class);
        mockGraphics = mock(Graphics.class);
        viewer = new InstructionsMenuFirstPageViewer(mockMenu);
    }

    @Test
    void testDrawTitle() {
        viewer.drawTitle(mockGraphics);

        String s0 = "           _____           _                   _   _                       ";
        String s1 = "          |_   _|         | |                 | | (_)                      " ;
        String s2 = "            | |  _ __  ___| |_ _ __ _   _  ___| |_ _  ___  _ __  ___       ";
        String s3 = "            | | | '_ \\/ __| __| '__| | | |/ __| __| |/ _ \\| '_ \\/ __|      ";
        String s4 = "           _| |_| | | \\__ \\ |_| |  | |_| | (__| |_| | (_) | | | \\__ \";    ";
        String s5 = "          |_____|_| |_|___/\\__|_|   \\__,_|\\___|\\__|_|\\___/|_| |_|___/      ";

        List<String> title = List.of(s0, s1, s2, s3, s4, s5);

        for(int i = 0; i < 6; i++){
            verify(mockGraphics).drawText(new Position(35, 1 + i), title.get(i), "  #f7dc6f  ");
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
                List.of("  #ffffff  ", "  #ffffff  ", "  #ffffff  ", "  #ffffff  ", "  #ffffff  ", "  #ffffff  ", " #ffffff ", " #ffffff "));

        for(int i = 0; i < 8; i++){
            verify(mockGraphics).drawText(new Position(15, 25 + i), snowflake.get(i), colors.get(0).get(i));
            verify(mockGraphics).drawText(new Position(5, 1 + i), snowflake.get(i), colors.get(1).get(i));
            verify(mockGraphics).drawText(new Position(120, 7 + i), snowflake.get(i), colors.get(2).get(i));

        }
    }

    @Test
    void testDrawElements() {
        viewer.drawElements(mockGraphics);

        verify(mockGraphics, times(6)).drawText(any(Position.class), anyString(), eq("  #f7dc6f  "));

        verify(mockGraphics, atLeastOnce()).drawText(any(Position.class), anyString(), eq("#ffffff"));
        verify(mockGraphics, atLeastOnce()).drawText(any(Position.class), anyString(), eq("  #ffffff  "));
        verify(mockGraphics, atLeastOnce()).drawText(any(Position.class), anyString(), eq(" #ffffff "));

        verify(mockGraphics).drawText(new Position(33, 15), "The goal of the game is to collect all fruits without being caught by the monsters", "#f76fe0");
        verify(mockGraphics).drawText(new Position(40, 20), "Movements", "#FFFFFF");
        verify(mockGraphics).drawText(new Position(40, 25), "Build/Break Ice", "#FFFFFF");
        verify(mockGraphics).drawText(new Position(40, 30), "Pause", "#FFFFFF");



        verify(mockGraphics).drawText(new Position(91, 17), "       ___          ", "#FFFFFF");
        verify(mockGraphics).drawText(new Position(91, 18), "      | ^ |         ", "#FFFFFF");
        verify(mockGraphics).drawText(new Position(91, 19), "      |_|_|         ", "#FFFFFF");
        verify(mockGraphics).drawText(new Position(91, 20), "  ___  ___  ___     ", "#FFFFFF");
        verify(mockGraphics).drawText(new Position(91, 21), " | <-|| | ||-> |    ", "#FFFFFF");
        verify(mockGraphics).drawText(new Position(91, 22), " |___||_v_||___|    ", "#FFFFFF");

        verify(mockGraphics).drawText(new Position(90, 24), " _________________ ", "#FFFFFF");
        verify(mockGraphics).drawText(new Position(90, 25), "|      SPACE      |", "#FFFFFF");
        verify(mockGraphics).drawText(new Position(90, 26), "|_________________|", "#FFFFFF");


        verify(mockGraphics).drawText(new Position(96, 29), " _____", "#FFFFFF");
        verify(mockGraphics).drawText(new Position(96, 30), "| ESC |", "#FFFFFF");
        verify(mockGraphics).drawText(new Position(96, 31), "|_____|", "#FFFFFF");

        verify(mockGraphics).drawText(new Position(110, 40), "Next Page", "#FFFFFF");
        verify(mockGraphics).drawText(new Position(120, 39), " ___", "#FFFFFF");
        verify(mockGraphics).drawText(new Position(120, 40), "| ->|", "#FFFFFF");
        verify(mockGraphics).drawText(new Position(120, 41), "|___|", "#FFFFFF");

        verify(mockGraphics).drawText(new Position(36, 40), "Main Menu", "#FFFFFF");
        verify(mockGraphics).drawText(new Position(30, 39), " ___", "#FFFFFF");
        verify(mockGraphics).drawText(new Position(30, 40), "|ESC|", "#FFFFFF");
        verify(mockGraphics).drawText(new Position(30, 41), "|___| ", "#FFFFFF");
    }
}
