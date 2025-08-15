package badIceCream.viewer.menu;

import badIceCream.GUI.Graphics;
import badIceCream.model.Position;
import badIceCream.model.menu.InstructionsMenuFirstPage;
import badIceCream.model.menu.InstructionsMenuSecondPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.*;

public class InstructionsMenuSecondPageViewerTest {

    private InstructionsMenuSecondPageViewer viewer;
    private InstructionsMenuSecondPage mockMenu;
    private Graphics mockGraphics;

    @BeforeEach
    void setUp() {
        mockMenu = mock(InstructionsMenuSecondPage.class);
        mockGraphics = mock(Graphics.class);
        viewer = new InstructionsMenuSecondPageViewer(mockMenu);
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
    void testDrawElements() throws IOException {
        viewer.drawElements(mockGraphics);

        verify(mockGraphics, times(6)).drawText(any(Position.class), anyString(), eq("  #f7dc6f  "));

        verify(mockGraphics, atLeastOnce()).drawText(any(Position.class), anyString(), eq("#ffffff"));
        verify(mockGraphics, atLeastOnce()).drawText(any(Position.class), anyString(), eq("  #ffffff  "));
        verify(mockGraphics, atLeastOnce()).drawText(any(Position.class), anyString(), eq(" #ffffff "));

        verify(mockGraphics).drawText(new Position(45, 15), "Default Monster: Just walks around the arena.", "#FFFFFF");
        verify(mockGraphics).drawText(new Position(45, 18), "Jumper Monster: Has the ability to jump walls.", "#FFFFFF");
        verify(mockGraphics).drawText(new Position(45, 21), "WallBreaker Monster: Has the ability to break ice walls.", "#FFFFFF");
        verify(mockGraphics).drawText(new Position(45, 24), "Runner Monster Inactive: Acts like a default monster.", "#FFFFFF");
        verify(mockGraphics).drawText(new Position(45, 27), "Default Monster Active: Has the ability to track Bad Ice Cream and run faster.", "#FFFFFF");
        verify(mockGraphics).drawText(new Position(45, 30), "Hot Floor: Blocks the progression of ice walls.", "#FFFFFF");


        verify(mockGraphics).drawText(new Position(36, 40), "Last Page", "#FFFFFF");
        verify(mockGraphics).drawText(new Position(30, 39), " ___", "#FFFFFF");
        verify(mockGraphics).drawText(new Position(30, 40), "|<- |", "#FFFFFF");
        verify(mockGraphics).drawText(new Position(30, 41), "|___| ", "#FFFFFF");

        verify(mockGraphics).drawText(new Position(110, 40), "Main Menu", "#FFFFFF");
        verify(mockGraphics).drawText(new Position(120, 39), " ___", "#FFFFFF");
        verify(mockGraphics).drawText(new Position(120, 40), "|ESC|", "#FFFFFF");
        verify(mockGraphics).drawText(new Position(120, 41), "|___| ", "#FFFFFF");
    }
}
