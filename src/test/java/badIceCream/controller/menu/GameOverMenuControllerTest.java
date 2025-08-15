package badIceCream.controller.menu;

import badIceCream.GUI.GUI;
import badIceCream.Game;
import badIceCream.model.game.arena.Arena;
import badIceCream.model.game.arena.LoaderArenaBuilder;
import badIceCream.model.menu.GameOverMenu;
import badIceCream.model.menu.InstructionsMenuFirstPage;
import badIceCream.model.menu.MainMenu;
import badIceCream.states.GameState;
import badIceCream.states.MainMenuState;
import badIceCream.states.State;
import badIceCream.utils.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class GameOverMenuControllerTest {

    private GameOverMenuController controller;
    private GameOverMenu mockMenu;
    private Game mockGame;

    private State mockState;

    @BeforeEach
    void setUp() {
        mockMenu = mock(GameOverMenu.class);
        mockGame = mock(Game.class);
        controller = new GameOverMenuController(mockMenu);

        mockGame = mock(Game.class);
        mockState = mock(State.class);

        when(mockGame.getState()).thenReturn(mockState);
        when(mockState.getLevel()).thenReturn(1);

    }

    @Test
    void testUpAction() throws IOException {
        controller.step(mockGame, GUI.ACTION.UP, 0);
        verify(mockMenu).previousEntry();
    }

    @Test
    void testDownAction() throws IOException {
        controller.step(mockGame, GUI.ACTION.DOWN, 0);
        verify(mockMenu).nextEntry();
    }

    @Test
    void testStep_SelectPlayAgain() throws IOException {
        when(mockMenu.isSelectedPlayAgain()).thenReturn(true);

        controller.step(mockGame, GUI.ACTION.SELECT, 0);

        verify(mockGame).setState(any(GameState.class), eq(Type.game), eq(14), eq(18));
    }

    @Test
    void testStep_SelectQuitToMainMenu() throws IOException {
        when(mockMenu.isSelectedQuitToMainMenu()).thenReturn(true);

        controller.step(mockGame, GUI.ACTION.SELECT, 0);

        verify(mockGame).setState(any(MainMenuState.class), eq(Type.menu), eq(140), eq(50));
    }

    @Test
    void testStep_NoAction() throws IOException {
        controller.step(mockGame, GUI.ACTION.NONE, 0);
        verifyNoInteractions(mockMenu); // Ensure no interaction with the menu
    }
}
