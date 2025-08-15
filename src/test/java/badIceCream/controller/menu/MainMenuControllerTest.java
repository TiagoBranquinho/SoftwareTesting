package badIceCream.controller.menu;

import badIceCream.GUI.GUI;
import badIceCream.Game;
import badIceCream.model.menu.InstructionsMenuFirstPage;
import badIceCream.model.menu.MainMenu;
import badIceCream.model.menu.SelectLevelMenu;
import badIceCream.states.InstructionsMenuFirstPageState;
import badIceCream.states.SelectLevelMenuState;
import badIceCream.states.State;
import badIceCream.utils.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class MainMenuControllerTest {

    private MainMenuController controller;
    private MainMenu mockMenu;
    private Game mockGame;
    private State mockState;

    @BeforeEach
    void setUp() {
        mockMenu = mock(MainMenu.class);
        mockGame = mock(Game.class);
        mockState = mock(State.class);

        when(mockGame.getState()).thenReturn(mockState);
        when(mockState.getLevel()).thenReturn(1);

        controller = new MainMenuController(mockMenu);
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
    void testSelectExit() throws IOException {
        when(mockMenu.isSelectedExit()).thenReturn(true);

        controller.step(mockGame, GUI.ACTION.SELECT, 0);

        verify(mockGame).setState(null, Type.nulo, 0, 0);
    }

    @Test
    void testSelectInstructions() throws IOException {
        when(mockMenu.isSelectedInstructions()).thenReturn(true);

        controller.step(mockGame, GUI.ACTION.SELECT, 0);

        verify(mockGame).setState(any(InstructionsMenuFirstPageState.class), eq(Type.nulo), eq(0), eq(0));
    }

    @Test
    void testSelectStart() throws IOException {
        when(mockMenu.isSelectedStart()).thenReturn(true);

        controller.step(mockGame, GUI.ACTION.SELECT, 0);

        verify(mockGame).setState(any(SelectLevelMenuState.class), eq(Type.nulo), eq(0), eq(0));
    }

    @Test
    void testUnrelatedAction() throws IOException {
        controller.step(mockGame, GUI.ACTION.RIGHT, 0);

        verify(mockMenu, never()).nextEntry();
        verify(mockMenu, never()).previousEntry();
        verify(mockGame, never()).setState(any(), any(), anyInt(), anyInt());
    }
}
