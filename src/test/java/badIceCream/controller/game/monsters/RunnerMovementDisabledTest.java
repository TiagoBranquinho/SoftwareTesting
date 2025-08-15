package badIceCream.controller.game.monsters;

import badIceCream.GUI.GUI;
import badIceCream.model.Position;
import badIceCream.model.game.arena.Arena;
import badIceCream.model.game.elements.IceCream;
import badIceCream.model.game.elements.monsters.Monster;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class RunnerMovementDisabledTest {

    private RunnerMovementDisabled runnerMovement;
    private Monster mockMonster;
    private Arena mockArena;
    private IceCream mockIceCream;

    @BeforeEach
    void setUp() {
        runnerMovement = new RunnerMovementDisabled();
        mockMonster = mock(Monster.class);
        mockArena = mock(Arena.class);
        mockIceCream = mock(IceCream.class);
        when(mockIceCream.getPosition()).thenReturn(new Position(1,1));

        when(mockArena.getIceCream()).thenReturn(mockIceCream);
        when(mockArena.isEmptyMonsters(any())).thenReturn(false);
    }

    @Test
    void testStep_MovesMonsterToValidPosition() throws IOException {
        Position monsterPosition = new Position(5, 5);
        Position newPosition = new Position(5, 6);

        when(mockMonster.getPosition()).thenReturn(monsterPosition);
        when(mockArena.isEmptyMonsters(newPosition)).thenReturn(true);

        runnerMovement.step(mockMonster, mockArena, 150, 0);

        verify(mockMonster).setPosition(newPosition); // Verify the monster moves
    }

    @Test
    void testStep_NoMoveDueToDelay() throws IOException {
        Position monsterPosition = new Position(5, 5);

        when(mockMonster.getPosition()).thenReturn(monsterPosition);

        runnerMovement.step(mockMonster, mockArena, 149, 0); // Not enough time has passed

        verify(mockMonster, never()).setPosition(any());
    }

    @Test
    void testMoveMonster_CollidesWithIceCream() {
        Position monsterPosition = new Position(5, 5);
        Position iceCreamPosition = new Position(5, 5);

        when(mockIceCream.getPosition()).thenReturn(iceCreamPosition);
        when(mockIceCream.isStrawberryActive()).thenReturn(false);

        runnerMovement.moveMonster(mockMonster, monsterPosition, mockArena);

        verify(mockIceCream).changeAlive(); // Verify ice cream's state changes
    }

    @Test
    void testMoveMonster_NoCollisionWhenStrawberryActive() {
        Position monsterPosition = new Position(5, 5);
        Position iceCreamPosition = new Position(5, 5);

        when(mockIceCream.getPosition()).thenReturn(iceCreamPosition);
        when(mockIceCream.isStrawberryActive()).thenReturn(true);

        runnerMovement.moveMonster(mockMonster, monsterPosition, mockArena);

        verify(mockIceCream, never()).changeAlive(); // Verify no state change
    }

    @Test
    void testLastMoveDown() throws IOException {
        Position startPosition = new Position(5, 5);
        Position endPosition = new Position(5, 6);

        when(mockMonster.getPosition()).thenReturn(startPosition);
        when(mockArena.isEmptyMonsters(endPosition)).thenReturn(true);

        runnerMovement.step(mockMonster, mockArena, 150, 0);

        verify(mockMonster).setLastAction(GUI.ACTION.DOWN); // Verify correct action is set
    }

    @Test
    void testLastMoveUp() throws IOException {
        Position startPosition = new Position(5, 5);
        Position endPosition = new Position(5, 4);

        when(mockMonster.getPosition()).thenReturn(startPosition);
        when(mockArena.isEmptyMonsters(endPosition)).thenReturn(true);

        runnerMovement.step(mockMonster, mockArena, 150, 0);

        verify(mockMonster).setLastAction(GUI.ACTION.UP); // Verify correct action is set
    }

    @Test
    void testLastMoveLeft() throws IOException {
        Position startPosition = new Position(5, 5);
        Position endPosition = new Position(4, 5);

        when(mockMonster.getPosition()).thenReturn(startPosition);
        when(mockArena.isEmptyMonsters(endPosition)).thenReturn(true);

        runnerMovement.step(mockMonster, mockArena, 150, 0);

        verify(mockMonster).setLastAction(GUI.ACTION.LEFT); // Verify correct action is set
    }

    @Test
    void testLastMoveRight() throws IOException {
        Position startPosition = new Position(5, 5);
        Position endPosition = new Position(6, 5);

        when(mockMonster.getPosition()).thenReturn(startPosition);
        when(mockArena.isEmptyMonsters(endPosition)).thenReturn(true);

        runnerMovement.step(mockMonster, mockArena, 150, 0);

        verify(mockMonster).setLastAction(GUI.ACTION.RIGHT); // Verify correct action is set
    }

}
