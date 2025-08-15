package badIceCream.states;

import badIceCream.GUI.GUI;
import badIceCream.GUI.Graphics;
import badIceCream.Game;
import badIceCream.controller.game.ArenaController;
import badIceCream.controller.menu.*;
import badIceCream.model.game.arena.Arena;
import badIceCream.model.menu.*;
import badIceCream.viewer.ArenaViewer;
import badIceCream.viewer.menu.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class StateTest {
    @Test
    public void testGameOverMenuState() {
        GameOverMenu model = mock(GameOverMenu.class);
        GameOverMenuController controller = mock(GameOverMenuController.class);
        GameOverMenuViewer viewer = mock(GameOverMenuViewer.class);
        GameOverMenuState gameOverMenuState = new GameOverMenuState(model, controller, viewer, 0);
    }

    @Test
    public void testGameState() {
        Arena arena = mock(Arena.class);
        ArenaController controller = mock(ArenaController.class);
        ArenaViewer viewer = mock(ArenaViewer.class);
        GameState gameState = new GameState(arena, controller, viewer, 0);
    }

    @Test
    public void testInstructionsMenuFirstPageState() {
        InstructionsMenuFirstPage model = mock(InstructionsMenuFirstPage.class);
        InstructionsMenuFirstPageController controller = mock(InstructionsMenuFirstPageController.class);
        InstructionsMenuFirstPageViewer viewer = mock(InstructionsMenuFirstPageViewer.class);

        InstructionsMenuFirstPageState instructionsMenuFirstPageState = new InstructionsMenuFirstPageState(model, controller, viewer, 0);
    }

    @Test
    public void testInstructionsMenuSecondPageState() {
        InstructionsMenuSecondPage model = mock(InstructionsMenuSecondPage.class);
        InstructionsMenuSecondPageController controller = mock(InstructionsMenuSecondPageController.class);
        InstructionsMenuSecondPageViewer viewer = mock(InstructionsMenuSecondPageViewer.class);

        InstructionsMenuSecondPageState instructionsMenuSecondPageState = new InstructionsMenuSecondPageState(model, controller, viewer, 0);
    }

    @Test
    public void testLevelCompletedMenuState() {
        LevelCompletedMenu model = mock(LevelCompletedMenu.class);
        LevelCompletedMenuController controller = mock(LevelCompletedMenuController.class);
        LevelCompletedMenuViewer viewer = mock(LevelCompletedMenuViewer.class);
        LevelCompletedMenuState levelCompletedMenuState = new LevelCompletedMenuState(model, controller, viewer, 0);
    }

    @Test
    public void testMainMenuState() {
        MainMenu model = mock(MainMenu.class);
        MainMenuController controller = mock(MainMenuController.class);
        MainMenuViewer viewer = mock(MainMenuViewer.class);
        MainMenuState mainMenuState = new MainMenuState(model, controller, viewer, 0);
    }

    @Test
    public void testPauseMenuState() {
        PauseMenu model = mock(PauseMenu.class);
        PauseMenuController controller = mock(PauseMenuController.class);
        PauseMenuViewer viewer = mock(PauseMenuViewer.class);
        PauseMenuState pauseMenuState = new PauseMenuState(model, mock(GameState.class), controller, viewer, 0);
    }

    @Test
    public void testSelectLevelMenuState() {
        SelectLevelMenu model = mock(SelectLevelMenu.class);
        SelectLevelMenuController controller = mock(SelectLevelMenuController.class);
        SelectLevelMenuViewer viewer = mock(SelectLevelMenuViewer.class);
        SelectLevelMenuState selectLevelMenuState = new SelectLevelMenuState(model, controller, viewer, 0);
    }

    @Test
    public void testStep() throws IOException {
        Arena arena = mock(Arena.class);
        ArenaController controller = mock(ArenaController.class);
        ArenaViewer viewer = mock(ArenaViewer.class);

        Game game = mock(Game.class);
        Graphics graphics = mock(Graphics.class);

        when(graphics.getNextAction()).thenReturn(GUI.ACTION.UP);

        GameState gameState = new GameState(arena, controller, viewer, 0);
        gameState.step(game, graphics, 0);

        verify(viewer).draw(graphics);
        verify(controller).step(game, GUI.ACTION.UP, 0);

    }

    @Test
    public void testStepMonsters() throws IOException {
        Arena arena = mock(Arena.class);
        ArenaController controller = mock(ArenaController.class);
        ArenaViewer viewer = mock(ArenaViewer.class);

        Game game = mock(Game.class);
        Graphics graphics = mock(Graphics.class);

        when(graphics.getNextAction()).thenReturn(GUI.ACTION.UP);

        GameState gameState = new GameState(arena, controller, viewer, 0);
        gameState.stepMonsters(0);

        verify(controller).stepMonsters(0);
    }

    @Test
    public void testIncreaseLevel() {
        Arena arena = mock(Arena.class);
        ArenaController controller = mock(ArenaController.class);
        ArenaViewer viewer = mock(ArenaViewer.class);

        GameState gameState = new GameState(arena, controller, viewer, 0);

        Assertions.assertEquals(gameState.getModel(), arena);
        for(int i = 0; i < 10; i++) {
            Assertions.assertEquals(i > 5 ? 5 : i, gameState.getLevel());
            gameState.increaseLevel();
        }
    }

}
