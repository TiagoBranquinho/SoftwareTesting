package badIceCream.controller.menu;

import badIceCream.GUI.GUI;
import badIceCream.Game;
import badIceCream.model.game.arena.Arena;
import badIceCream.model.game.arena.LoaderArenaBuilder;
import badIceCream.model.menu.GameOverMenu;
import badIceCream.model.menu.SelectLevelMenu;
import badIceCream.states.GameState;
import badIceCream.states.MainMenuState;
import badIceCream.states.State;
import badIceCream.utils.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class SelectLevelMenuControllerTest {

    private SelectLevelMenuController controller;
    private SelectLevelMenu mockMenu;
    private Game mockGame;
    private State mockState;

    @BeforeEach
    void setUp() {

        mockMenu = mock(SelectLevelMenu.class);
        mockGame = mock(Game.class);
        controller = new SelectLevelMenuController(mockMenu);

        mockGame = mock(Game.class);
        mockState = mock(State.class);

        when(mockGame.getState()).thenReturn(mockState);
        when(mockState.getLevel()).thenReturn(1);

        when(mockMenu.isSelectedLevel1()).thenReturn(false);
        when(mockMenu.isSelectedLevel2()).thenReturn(false);
        when(mockMenu.isSelectedLevel3()).thenReturn(false);
        when(mockMenu.isSelectedLevel4()).thenReturn(false);
        when(mockMenu.isSelectedLevel5()).thenReturn(false);
    }

    @Test
    void testLeftAction() throws IOException {
        controller.step(mockGame, GUI.ACTION.LEFT, 0);

        verify(mockMenu).previousEntry();
    }

    @Test
    void testRightAction() throws IOException {
        controller.step(mockGame, GUI.ACTION.RIGHT, 0);

        verify(mockMenu).nextEntry();
    }

    @Test
    void testSelectLevel1() throws IOException {
        when(mockMenu.isSelectedLevel1()).thenReturn(true);

        controller.step(mockGame, GUI.ACTION.SELECT, 0);

        verify(mockGame).setState(any(GameState.class), eq(Type.game), eq(14), eq(18));
    }

    @Test
    void testSelectLevel2() throws IOException {
        when(mockMenu.isSelectedLevel2()).thenReturn(true);

        when(mockState.getLevel()).thenReturn(2);

        controller.step(mockGame, GUI.ACTION.SELECT, 0);

        verify(mockGame).setState(any(GameState.class), eq(Type.game), eq(19), eq(22));
    }

    @Test
    void testSelectLevel3() throws IOException {
        when(mockMenu.isSelectedLevel3()).thenReturn(true);

        when(mockState.getLevel()).thenReturn(3);

        controller.step(mockGame, GUI.ACTION.SELECT, 0);

        verify(mockGame).setState(any(GameState.class), eq(Type.game), eq(17), eq(17));
    }

    @Test
    void testSelectLevel4() throws IOException {
        when(mockMenu.isSelectedLevel4()).thenReturn(true);

        when(mockState.getLevel()).thenReturn(4);

        controller.step(mockGame, GUI.ACTION.SELECT, 0);

        verify(mockGame).setState(any(GameState.class), eq(Type.game), eq(21), eq(19));
    }

    @Test
    void testSelectLevel5() throws IOException {
        when(mockMenu.isSelectedLevel5()).thenReturn(true);

        when(mockState.getLevel()).thenReturn(5);

        controller.step(mockGame, GUI.ACTION.SELECT, 0);

        verify(mockGame).setState(any(GameState.class), eq(Type.game), eq(20), eq(20));
    }

    @Test
    void testWrongLevel1() throws IOException {
        when(mockMenu.isSelectedLevel5()).thenReturn(true);

        when(mockState.getLevel()).thenReturn(0);

        controller.step(mockGame, GUI.ACTION.SELECT, 0);

        verify(mockGame, never()).setState(any(), any(), anyInt(), anyInt());
    }

    @Test
    void testWrongLevel2() throws IOException {
        when(mockMenu.isSelectedLevel2()).thenReturn(true);

        when(mockState.getLevel()).thenReturn(0);

        controller.step(mockGame, GUI.ACTION.SELECT, 0);

        verify(mockGame, never()).setState(any(), any(), anyInt(), anyInt());
    }
    @Test
    void testWrongLevel3() throws IOException {
        when(mockMenu.isSelectedLevel3()).thenReturn(true);

        when(mockState.getLevel()).thenReturn(0);

        controller.step(mockGame, GUI.ACTION.SELECT, 0);

        verify(mockGame, never()).setState(any(), any(), anyInt(), anyInt());
    }
    @Test
    void testWrongLevel4() throws IOException {
        when(mockMenu.isSelectedLevel4()).thenReturn(true);

        when(mockState.getLevel()).thenReturn(0);

        controller.step(mockGame, GUI.ACTION.SELECT, 0);

        verify(mockGame, never()).setState(any(), any(), anyInt(), anyInt());
    }
    @Test
    void testWrongLevel5() throws IOException {
        when(mockMenu.isSelectedLevel5()).thenReturn(true);

        when(mockState.getLevel()).thenReturn(0);

        controller.step(mockGame, GUI.ACTION.SELECT, 0);

        verify(mockGame, never()).setState(any(), any(), anyInt(), anyInt());
    }

    @Test
    void testUnrelatedAction() throws IOException {
        controller.step(mockGame, GUI.ACTION.UP, 0);

        verify(mockMenu, never()).previousEntry();
        verify(mockMenu, never()).nextEntry();
        verify(mockGame, never()).setState(any(), any(), anyInt(), anyInt());
    }
}
