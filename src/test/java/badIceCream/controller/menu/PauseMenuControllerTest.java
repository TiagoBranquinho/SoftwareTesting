package badIceCream.controller.menu;

import badIceCream.GUI.GUI;
import badIceCream.Game;
import badIceCream.model.game.arena.Arena;
import badIceCream.model.menu.GameOverMenu;
import badIceCream.model.menu.PauseMenu;
import badIceCream.states.GameState;
import badIceCream.states.MainMenuState;
import badIceCream.states.State;
import badIceCream.utils.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class PauseMenuControllerTest {

    private PauseMenuController controller;
    private PauseMenu mockMenu;
    private GameState mockState;
    private Game mockGame;

    private Arena mockArena;

    @BeforeEach
    void setUp() {
        mockMenu = mock(PauseMenu.class);
        mockGame = mock(Game.class);

        mockGame = mock(Game.class);
        mockState = mock(GameState.class);

        mockArena = mock(Arena.class);
        when(mockGame.getState()).thenReturn(mockState);
        when(mockState.getLevel()).thenReturn(1);
        when(mockState.getModel()).thenReturn(mockArena);
        when(mockArena.getWidth()).thenReturn(14);
        when(mockArena.getHeight()).thenReturn(18);

        controller = new PauseMenuController(mockMenu, mockState);
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
    void testSelectResume() throws IOException {
        when(mockMenu.isSelectedResume()).thenReturn(true);

        controller.step(mockGame, GUI.ACTION.SELECT, 0);

        verify(mockGame).setState(eq(mockState), eq(Type.game), eq(14), eq(18));
    }

    @Test
    void testSelectMenu() throws IOException {
        when(mockMenu.isSelectedMenu()).thenReturn(true);

        controller.step(mockGame, GUI.ACTION.SELECT, 0);

        verify(mockGame).setState(any(MainMenuState.class), eq(Type.nulo), eq(0), eq(0));
    }

    @Test
    void testUnrelatedAction() throws IOException {
        controller.step(mockGame, GUI.ACTION.RIGHT, 0);

        verify(mockMenu, never()).nextEntry();
        verify(mockMenu, never()).previousEntry();
        verify(mockGame, never()).setState(any(), any(), anyInt(), anyInt());
    }
}
