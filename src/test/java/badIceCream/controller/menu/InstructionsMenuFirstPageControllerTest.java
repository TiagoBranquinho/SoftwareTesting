package badIceCream.controller.menu;

import badIceCream.GUI.GUI;
import badIceCream.Game;
import badIceCream.model.menu.InstructionsMenuFirstPage;
import badIceCream.model.menu.InstructionsMenuSecondPage;
import badIceCream.model.menu.MainMenu;
import badIceCream.states.InstructionsMenuFirstPageState;
import badIceCream.states.InstructionsMenuSecondPageState;
import badIceCream.states.MainMenuState;
import badIceCream.states.State;
import badIceCream.utils.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class InstructionsMenuFirstPageControllerTest {

    private InstructionsMenuFirstPageController controller;
    private InstructionsMenuFirstPage mockMenu;
    private Game mockGame;
    private State mockState;

    @BeforeEach
    void setUp() {
        mockMenu = mock(InstructionsMenuFirstPage.class);
        mockGame = mock(Game.class);
        mockState = mock(State.class);

        when(mockGame.getState()).thenReturn(mockState);
        when(mockState.getLevel()).thenReturn(1);

        controller = new InstructionsMenuFirstPageController(mockMenu);
    }

    @Test
    void testPauseAction() throws IOException {
        controller.step(mockGame, GUI.ACTION.PAUSE, 0);

        verify(mockGame).setState(any(MainMenuState.class), eq(Type.nulo), eq(0), eq(0));
    }

    @Test
    void testRightAction() throws IOException {
        controller.step(mockGame, GUI.ACTION.RIGHT, 0);

        verify(mockGame).setState(any(InstructionsMenuSecondPageState.class), eq(Type.nulo), eq(0), eq(0));
    }

    @Test
    void testStep_UnrelatedAction() throws IOException {
        controller.step(mockGame, GUI.ACTION.UP, 0);

        verify(mockGame, never()).setState(any(), any(), anyInt(), anyInt());
    }
}
