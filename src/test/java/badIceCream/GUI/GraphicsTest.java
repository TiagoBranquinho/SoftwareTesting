package badIceCream.GUI;

import badIceCream.model.Position;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

public class GraphicsTest {

    private Graphics graphics;
    private GUI mockGui;
    private Screen mockScreen;
    private TextGraphics mockTextGraphics;

    @BeforeEach
    void setUp() throws IOException {
        mockGui = mock(GUI.class);
        mockScreen = mock(Screen.class);
        mockTextGraphics = mock(TextGraphics.class);
        when(mockGui.createScreen(any())).thenReturn(mockScreen);
        when(mockScreen.newTextGraphics()).thenReturn(mockTextGraphics);

        graphics = new Graphics(mockGui);
    }

    @Test
    void testDrawIceCream() {
        Position position = new Position(10, 10);

        graphics.drawIceCream(position, GUI.ACTION.UP, true);
        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#48DEFF"));
        verify(mockTextGraphics).putString(10, 10, "7");

        graphics.drawIceCream(position, GUI.ACTION.LEFT, false);
        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        verify(mockTextGraphics).putString(10, 10, ":");

        graphics.drawIceCream(position, GUI.ACTION.RIGHT, false);
        verify(mockTextGraphics).putString(10, 10, "9");

        graphics.drawIceCream(position, GUI.ACTION.NONE, true);
        verify(mockTextGraphics).putString(10, 10, "8");
    }

    @Test
    void testDrawStoneWall() {
        Position position = new Position(5, 5);

        graphics.drawStoneWall(position);
        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#696969"));
        verify(mockTextGraphics).putString(5, 5, "G");
    }

    @ParameterizedTest
    @CsvSource
    ({
        "1, F",
        "3, f",
        "4, h",
        "5, g",
        "6, i",
        "7, e",
        "8, k",
        "9, l",
        "10, n",
        "11, m",
        "2, x"
    })
    void testDrawIceWall(int type, char expected) {
        Position position = new Position(7, 7);

        graphics.drawIceWall(position, type);
        if(type != 2){
            verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#87CEFA"));
            verify(mockTextGraphics).putString(7, 7, String.valueOf(expected));
        }
    }

    @ParameterizedTest
    @CsvSource
    ({
        "UP, 4",
        "LEFT, ~",
        "RIGHT, È",
        "NONE, Y"
    })
    void testDrawDefaultMonster(String action, char expected) {
        Position position = new Position(8, 8);

        graphics.drawDefaultMonster(position, GUI.ACTION.valueOf(action));
        
        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#00FF00"));
        verify(mockTextGraphics).putString(8, 8, String.valueOf(expected));
    }

    @ParameterizedTest
    @CsvSource
            ({
                    "UP, /",
                    "LEFT, y",
                    "RIGHT, è",
                    "NONE, T"
            })
    void testDrawJumperMonster(String action, char expected) {
        Position position = new Position(9, 9);

        graphics.drawJumperMonster(position, GUI.ACTION.valueOf(action));
        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FF3333"));
        verify(mockTextGraphics).putString(9, 9, String.valueOf(expected));
    }

    @ParameterizedTest
    @CsvSource
            ({
                    "UP, 1, 3",
                    "UP, 0, 1",
                    "LEFT, 1, X",
                    "LEFT, 0, W",
                    "RIGHT, 1, }",
                    "RIGHT, 0, 2",
                    "NONE, 1, |",
                    "NONE, 0, V"
            })
    void testDrawRunnerMonster(String action, String runner, char expected) {
        Position position = new Position(9, 9);

        graphics.drawRunnerMonster(position, GUI.ACTION.valueOf(action), Integer.parseInt(runner) == 1);
        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString(Integer.parseInt(runner) == 1 ? "#FF0000" : "#FFFF66"));
        verify(mockTextGraphics).putString(9, 9, String.valueOf(expected));
    }

    @ParameterizedTest
    @CsvSource
            ({
                    "UP, 0",
                    "LEFT, é",
                    "RIGHT, z",
                    "NONE, U"
            })
    void testDrawWallBreakerMonster(String action, char expected) {
        Position position = new Position(9, 9);

        graphics.drawWallBreakerMonster(position, GUI.ACTION.valueOf(action));
        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FF99FF"));
        verify(mockTextGraphics).putString(9, 9, String.valueOf(expected));
    }

    static Stream<Arguments> provideHotFloorData() {
        return Stream.of(
                Arguments.of(1, 'w'),
                Arguments.of(2, 'd'),
                Arguments.of(3, 'c'),
                Arguments.of(4, 'x'),
                Arguments.of(5, '%'),
                Arguments.of(6, '('),
                Arguments.of(7, '\''),
                Arguments.of(8, '&'),
                Arguments.of(9, ')'),
                Arguments.of(10, '+'),
                Arguments.of(11, ','),
                Arguments.of(12, '*'),
                Arguments.of(13, '-'),
                Arguments.of(14, '.'),
                Arguments.of(15, 'S'),
                Arguments.of(16, 'R'),
                Arguments.of(17, '!'),
                Arguments.of(18, '#'),
                Arguments.of(19, '"'),
                Arguments.of(20, '$'),
                Arguments.of(21, 'C'),
                Arguments.of(22, '@'),
                Arguments.of(23, 'D'),
                Arguments.of(24, 'B'),
                Arguments.of(25, 'A'),
                Arguments.of(26, ';'),
                Arguments.of(27, '='),
                Arguments.of(28, '>'),
                Arguments.of(29, '<'),
                Arguments.of(30, 'b')
        );
    }

    @ParameterizedTest
    @MethodSource("provideHotFloorData")
    void testDrawHotFloor(int type, char expected) {
        Position position = new Position(7, 7);

        graphics.drawHotFloor(position, type);
        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#e14750"));
        verify(mockTextGraphics).putString(7, 7, String.valueOf(expected));
    }

    @Test
    void testDrawCharacters() {
        graphics.drawCharacters();

        verify(mockTextGraphics, times(6)).setForegroundColor(TextColor.Factory.fromString("#00FF00"));
        verify(mockTextGraphics).putString(33, 15, "Ê");
        verify(mockTextGraphics).putString(33, 18, "À");
        verify(mockTextGraphics).putString(33, 21, "Á");
        verify(mockTextGraphics).putString(33, 24, "È");
        verify(mockTextGraphics).putString(33, 27, "É");
        verify(mockTextGraphics).putString(33, 30, "Í");
    }

    @ParameterizedTest
    @CsvSource
    ({
        "ArrowDown, DOWN",
        "ArrowUp, UP",
        "ArrowLeft, LEFT",
        "ArrowRight, RIGHT",
        "Escape, PAUSE",
        "Enter, SELECT",
        "Backspace, NONE"
    })
    void testNextAction(KeyType keyType, GUI.ACTION expected) throws IOException {
        KeyStroke keyStroke = mock(KeyStroke.class);
        when(keyStroke.getKeyType()).thenReturn(keyType);
        when(mockScreen.pollInput()).thenReturn(keyStroke);

        GUI.ACTION action = graphics.getNextAction();
        Assertions.assertEquals(expected, action);
    }

    @Test
    void testNextAction2() throws IOException {
        when(mockScreen.pollInput()).thenReturn(null);

        GUI.ACTION action = graphics.getNextAction();
        Assertions.assertEquals(GUI.ACTION.NONE, action);

        when(mockScreen.pollInput()).thenReturn(mock(KeyStroke.class));
        when(mockScreen.pollInput().getCharacter()).thenReturn(' ');

        action = graphics.getNextAction();

        Assertions.assertEquals(GUI.ACTION.SPACE, action);
    }

    @Test
    void testDrawAppleFruit() {
        Position position = new Position(10, 10);

        graphics.drawAppleFruit(position);
        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        verify(mockTextGraphics).putString(10, 10, "]");
    }

    @Test
    void testDrawBananFruit() {
        Position position = new Position(10, 10);

        graphics.drawBananaFruit(position);
        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
        verify(mockTextGraphics).putString(10, 10, "a");
    }

    @Test
    void drawPineappleFruit() {
        Position position = new Position(10, 10);

        graphics.drawPineappleFruit(position);
        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFF66"));
        verify(mockTextGraphics).putString(10, 10, "^");
    }

    @Test
    void drawCherryFruit() {
        Position position = new Position(10, 10);

        graphics.drawCherryFruit(position);
        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        verify(mockTextGraphics).putString(10, 10, "\\");
    }

    @Test
    void drawStrawberryFruit() {
        Position position = new Position(10, 10);

        graphics.drawStrawberryFruit(position);
        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        verify(mockTextGraphics).putString(10, 10, "_");
    }

    @Test
    void testDrawText() {
        Position position = new Position(10, 10);

        TextGraphics mockTextGraphics = mock(TextGraphics.class);
        when(mockScreen.newTextGraphics()).thenReturn(mockTextGraphics);
        graphics.drawText(position, "A", "#FF0000");
        verify(mockTextGraphics).setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        verify(mockTextGraphics).putString(10, 10, "A");
    }

    @Test
    void testGetGui(){
        GUI mockGui = mock(GUI.class);

        graphics.setGui(mockGui);
        Assertions.assertEquals(mockGui, graphics.getGui());
    }

    @Test
    void testScreen() throws IOException {
        graphics.clear();
        verify(mockScreen).clear();

        graphics.refresh();
        verify(mockScreen).refresh();

        graphics.close();
        verify(mockScreen).close();
    }
}
