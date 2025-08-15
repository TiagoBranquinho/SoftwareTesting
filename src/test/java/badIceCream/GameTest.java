package badIceCream;

import badIceCream.GUI.GUI;
import badIceCream.GUI.GameGraphics;
import badIceCream.GUI.Graphics;
import badIceCream.GUI.MenuGraphics;
import badIceCream.audio.AudioController;
import badIceCream.model.menu.MainMenu;
import badIceCream.states.GameState;
import badIceCream.states.MainMenuState;
import badIceCream.states.State;
import badIceCream.utils.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;

import java.awt.*;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameTest {

    private Game game;

    private Graphics gui;

    private State mockState;

    private Game spy;

    @BeforeEach
    void setUp() throws FontFormatException, IOException {
        game = new Game();
        spy = spy(game);
        gui = mock(Graphics.class);
        mockState = mock(State.class);

    }

    @Test
    void testInitialState() {
        try (MockedStatic<AudioController> audioMock = mockStatic(AudioController.class)) {
            game = new Game();
            assertNotNull(game.getGui());
            assertNotNull(game.getState());
            assertTrue(game.getState() instanceof MainMenuState);

            audioMock.verify(AudioController::playMenuMusic);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        }

    }

    @ParameterizedTest
    @CsvSource({
            "menu",
            "win",
            "game",
            "gameOver",
            "nulo"
    })
    void testSetState(String typeString) throws IOException, FontFormatException {
        try (MockedStatic<AudioController> audioMock = mockStatic(AudioController.class)) {
            Type type = Type.valueOf(typeString);

            Game game = spy(new Game());

            if(type == Type.nulo) {
                game.setState(mockState, type, 100, 50);
                return;
            }

            doReturn(gui).when(game).getGraphicsForGame(type, 100, 50);
            System.out.println("a " + gui);
            game.setState(mockState, type, 100, 50);
            game.setState(mockState, type, 100, 50);
            assertEquals(mockState, game.getState());
            verify(gui, times(1)).close();
            verify(gui, times(2)).refresh();

            switch (type) {
                case menu -> {
                    audioMock.verify(AudioController::stopGameOverMusic, times(2));
                    audioMock.verify(AudioController::stopLevelCompleteMusic, times(2));
                    audioMock.verify(AudioController::stopLevelMusic, times(2));
                    audioMock.verify(AudioController::playMenuMusic, times(3));
                }
                case win -> {
                    audioMock.verify(AudioController::stopGameOverMusic, times(2));
                    audioMock.verify(AudioController::stopLevelMusic, times(2));
                    audioMock.verify(AudioController::stopMenuMusic, times(2));
                    audioMock.verify(AudioController::playLevelCompleteMusic, times(2));
                }
                case gameOver -> {
                    audioMock.verify(AudioController::stopLevelMusic, times(2));
                    audioMock.verify(AudioController::stopMenuMusic, times(2));
                    audioMock.verify(AudioController::stopLevelCompleteMusic, times(2));
                    audioMock.verify(AudioController::playGameOverMusic, times(2));
                }
                case game -> {
                    audioMock.verify(AudioController::stopMenuMusic, times(2));
                    audioMock.verify(AudioController::stopLevelCompleteMusic, times(2));
                    audioMock.verify(AudioController::stopGameOverMusic, times(2));
                    audioMock.verify(AudioController::playLevelMusic, times(2));
                }
            }
        }
    }

    @ParameterizedTest
    @CsvSource
    ({
        "menu",
        "win",
        "game",
        "gameOver",
        "nulo"
    })
    void testGetGraphicsForGame(String typeString) throws IOException {
        Type type = Type.valueOf(typeString);
        Graphics gameGraphics = game.getGraphicsForGame(type, 100, 50);

        if (type == Type.nulo) {
            assertNull(gameGraphics);
        } else {
            assertNotNull(gameGraphics);
        }
    }

}
