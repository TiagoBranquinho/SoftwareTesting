package badIceCream.controller.game;

import badIceCream.GUI.GUI;
import badIceCream.Game;
import badIceCream.model.Position;
import badIceCream.model.game.arena.Arena;
import badIceCream.model.game.elements.IceCream;
import badIceCream.model.game.elements.monsters.*;
import badIceCream.model.menu.GameOverMenu;
import badIceCream.model.menu.LevelCompletedMenu;
import badIceCream.model.menu.PauseMenu;
import badIceCream.states.GameOverMenuState;
import badIceCream.states.GameState;
import badIceCream.states.LevelCompletedMenuState;
import badIceCream.states.PauseMenuState;
import badIceCream.utils.Type;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ArenaControllerTest {

    private ArenaController arenaController;
    private IceCreamController mockIceCreamController;
    private List<MonsterController> mockMonsterControllers;
    private Arena mockArena;
    private Game mockGame;
    private IceCream mockIceCream;

    @BeforeEach
    void setUp() {
        mockArena = mock(Arena.class);
        mockIceCreamController = mock(IceCreamController.class);
        mockMonsterControllers = new ArrayList<>();
        mockGame = mock(Game.class);
        mockIceCream = mock(IceCream.class);

        List<Monster> mockMonsters = new ArrayList<>();

        Position position1 = new Position(1, 1);
        Position position2 = new Position(2, 2);
        Position position3 = new Position(3, 3);
        Position position4 = new Position(4, 4);

        Monster mockDefault = mock(DefaultMonster.class);
        when(mockDefault.getType()).thenReturn(1);
        Monster mockRunner = mock(RunnerMonster.class);
        when(mockRunner.getType()).thenReturn(3);
        Monster mockJumper = mock(JumperMonster.class);
        when(mockJumper.getType()).thenReturn(2);
        Monster mockWallBreaker = mock(WallBreakerMonster.class);
        when(mockWallBreaker.getType()).thenReturn(4);

        Monster mockIncorrect = mock(Monster.class);
        when(mockIncorrect.getType()).thenReturn(5);

        when(mockDefault.getPosition()).thenReturn(position1);
        when(mockRunner.getPosition()).thenReturn(position2);
        when(mockJumper.getPosition()).thenReturn(position3);
        when(mockWallBreaker.getPosition()).thenReturn(position4);
        when(mockIncorrect.getPosition()).thenReturn(new Position(5, 5));


        mockMonsters.add(mockDefault);
        mockMonsters.add(mockRunner);
        mockMonsters.add(mockJumper);
        mockMonsters.add(mockWallBreaker);

        mockMonsters.add(mockIncorrect);

        when(mockArena.getMonsters()).thenReturn(mockMonsters);

        when(mockArena.getIceCream()).thenReturn(mockIceCream);
        arenaController = new ArenaController(mockArena, mockIceCreamController, mockMonsterControllers);
    }


    @Test
    void testStep_EatFruit() throws IOException {

        when(mockArena.getLevel()).thenReturn(1);
        when(mockGame.getState()).thenReturn(mock(GameState.class));
        when(mockGame.getState().getLevel()).thenReturn(1);
        when(mockIceCreamController.eatFruit()).thenReturn(5);
        when(mockIceCream.isStrawberryActive()).thenReturn(true);


        arenaController.step(mockGame, GUI.ACTION.NONE, 1000);

        verify(mockIceCream).setStrawberry(true);

        arenaController.step(mockGame, GUI.ACTION.NONE, 10002);
        verify(mockIceCream, never()).setStrawberry(false);
    }

    @Test
    void testStep_EatFruit2() throws IOException {

        when(mockArena.getLevel()).thenReturn(1);
        when(mockGame.getState()).thenReturn(mock(GameState.class));
        when(mockGame.getState().getLevel()).thenReturn(2);
        when(mockIceCreamController.eatFruit()).thenReturn(5);
        when(mockIceCream.isStrawberryActive()).thenReturn(true);


        arenaController.step(mockGame, GUI.ACTION.NONE, 1000);

        verify(mockIceCream).setStrawberry(true);

        arenaController.step(mockGame, GUI.ACTION.NONE, 10002);
        verify(mockIceCream, never()).setStrawberry(false);
    }

    @Test
    void testStep_StrawberryExpires() throws IOException {
        when(mockArena.getLevel()).thenReturn(1);
        when(mockGame.getState()).thenReturn(mock(GameState.class));
        when(mockGame.getState().getLevel()).thenReturn(1);


        // Mocking IceCreamController to ensure strawberry mode is active
        when(mockIceCreamController.eatFruit()).thenReturn(-1);
        when(mockIceCream.isStrawberryActive()).thenReturn(true);

        // Simulate the "strawberry" activation time
        arenaController.step(mockGame, GUI.ACTION.NONE, 1000); // Initial step to activate strawberry

        // Test right before expiration (boundary case)
        arenaController.step(mockGame, GUI.ACTION.NONE, 9999);
        verify(mockIceCream, never()).setStrawberry(false);

        // Test at the exact expiration point
        arenaController.step(mockGame, GUI.ACTION.NONE, 10000);



        // Test after the expiration point
        arenaController.step(mockGame, GUI.ACTION.NONE, 10001);
        verify(mockIceCream, times(2)).setStrawberry(false); // Ensure it was only deactivated once

    }


    @Test
    void testStep_LevelCompleted() throws IOException {
        when(mockArena.getFruits()).thenReturn(new ArrayList<>());
        when(mockArena.getLevel()).thenReturn(1);
        GameState mockGameState = mock(GameState.class);
        when(mockGame.getState()).thenReturn(mockGameState);
        when(mockGameState.getLevel()).thenReturn(1);

        arenaController.step(mockGame, GUI.ACTION.NONE, 1000);
        verify(mockArena).generateNewFruits(1);
        arenaController.step(mockGame, GUI.ACTION.NONE, 2000);

        verify(mockGameState).increaseLevel();

        verify(mockGame).setState(any(LevelCompletedMenuState.class), eq(Type.win), eq(140), eq(50));

        when(mockArena.getLevel()).thenReturn(2);
    }

    @Test
    void testStep_GameOver() throws IOException {
        List mockFruits = mock(List.class);
        when(mockArena.getFruits()).thenReturn(mockFruits);
        when(mockArena.getLevel()).thenReturn(1);
        when(mockGame.getState()).thenReturn(mock(GameState.class));
        when(mockGame.getState().getLevel()).thenReturn(1);

        when(mockIceCream.getAlive()).thenReturn(false);

        arenaController.step(mockGame, GUI.ACTION.NONE, 1000);

        verify(mockGame).setState(any(GameOverMenuState.class), eq(Type.gameOver), eq(140), eq(50));
    }


    @Test
    void testStep_Pause() throws IOException {
        List mockFruits = mock(List.class);
        when(mockArena.getFruits()).thenReturn(mockFruits);
        when(mockArena.getLevel()).thenReturn(1);
        when(mockGame.getState()).thenReturn(mock(GameState.class));
        when(mockGame.getState().getLevel()).thenReturn(1);

        when(mockIceCream.getAlive()).thenReturn(true);

        arenaController.step(mockGame, GUI.ACTION.PAUSE, 1000);

        verify(mockGame).setState(any(PauseMenuState.class), eq(Type.menu), eq(140), eq(50));
    }

    @Test
    void testStep_ContinueGame() throws IOException {
        List mockFruits = mock(List.class);
        when(mockArena.getFruits()).thenReturn(mockFruits);
        when(mockArena.getLevel()).thenReturn(1);
        when(mockGame.getState()).thenReturn(mock(GameState.class));
        when(mockGame.getState().getLevel()).thenReturn(1);

        when(mockIceCream.getAlive()).thenReturn(true);

        arenaController.step(mockGame, GUI.ACTION.NONE, 1000);

        verify(mockIceCreamController).step(mockGame, GUI.ACTION.NONE, 1000);
    }

    @Test
    void testStepMonsters() throws IOException {
        MonsterController mockMonster1 = mock(MonsterController.class);
        MonsterController mockMonster2 = mock(MonsterController.class);

        mockMonsterControllers.add(mockMonster1);
        mockMonsterControllers.add(mockMonster2);

        arenaController.stepMonsters(1000);

        verify(mockMonster1, times(1)).step(1000);
        verify(mockMonster2, times(1)).step(1000);
    }

}
