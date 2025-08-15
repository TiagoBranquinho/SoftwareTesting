package badIceCream.controller.game;

import badIceCream.GUI.GUI;
import badIceCream.Game;
import badIceCream.model.Position;
import badIceCream.model.game.arena.Arena;
import badIceCream.model.game.elements.IceCream;
import badIceCream.model.game.elements.monsters.DefaultMonster;
import badIceCream.model.game.elements.monsters.Monster;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IceCreamControllerTest {

    private IceCreamController iceCreamController;
    private Arena mockArena;
    private IceCream mockIceCream;

    @BeforeEach
    void setUp() {
        mockArena = mock(Arena.class);
        mockIceCream = mock(IceCream.class);

        when(mockArena.getIceCream()).thenReturn(mockIceCream);
        when(mockIceCream.getPosition()).thenReturn(new Position(5, 5));

        iceCreamController = new IceCreamController(mockArena);
    }

    @Test
    void testMoveIceCreamLeft() {
        Position newPosition = new Position(4, 5);
        when(mockArena.isEmpty(newPosition)).thenReturn(true);

        iceCreamController.step(null, GUI.ACTION.LEFT, 1000);

        verify(mockIceCream).setPosition(newPosition);
        verify(mockIceCream).setLastMovement(GUI.ACTION.LEFT);
    }

    @Test
    void testMoveIceCreamRight() {
        Position newPosition = new Position(6, 5);
        when(mockArena.isEmpty(newPosition)).thenReturn(true);

        iceCreamController.step(null, GUI.ACTION.RIGHT, 1000);

        verify(mockIceCream).setPosition(newPosition);
        verify(mockIceCream).setLastMovement(GUI.ACTION.RIGHT);
    }

    @Test
    void testMoveIceCreamUp() {
        Position newPosition = new Position(5, 4);
        when(mockArena.isEmpty(newPosition)).thenReturn(true);

        iceCreamController.step(null, GUI.ACTION.UP, 1000);

        verify(mockIceCream).setPosition(newPosition);
        verify(mockIceCream).setLastMovement(GUI.ACTION.UP);
    }

    @Test
    void testMoveIceCreamDown() {
        Position newPosition = new Position(5, 6);
        when(mockArena.isEmpty(newPosition)).thenReturn(true);

        iceCreamController.step(null, GUI.ACTION.DOWN, 1000);

        verify(mockIceCream).setPosition(newPosition);
        verify(mockIceCream).setLastMovement(GUI.ACTION.DOWN);
    }

    @Test
    void testPowerIceCream() {
        iceCreamController.step(null, GUI.ACTION.SPACE, 1000);

        verify(mockArena).powerIceCream(GUI.ACTION.DOWN); // Default lastMovement is DOWN
    }

    @Test
    void testEatFruit() {
        when(mockArena.eatFruit(new Position(5, 5))).thenReturn(1);

        int result = iceCreamController.eatFruit();

        Assertions.assertEquals(1, result);
        verify(mockArena).eatFruit(new Position(5, 5));
    }

    @Test
    void testMovementDelay() {
        Position initialPosition = new Position(5, 5);
        Position firstLeftPosition = new Position(4, 5);
        Position secondLeftPosition = new Position(3, 5);

        // Mock the position chain for LEFT movements
        when(mockIceCream.getPosition()).thenReturn(initialPosition);
        when(mockArena.isEmpty(firstLeftPosition)).thenReturn(true);
        when(mockArena.isEmpty(secondLeftPosition)).thenReturn(true);

        // Simulate movements
        iceCreamController.step(null, GUI.ACTION.LEFT, 1000); // First movement

        // Verify the ice cream moved to both positions
        verify(mockIceCream).setPosition(firstLeftPosition); // First movement

        when(mockIceCream.getPosition()).thenReturn(firstLeftPosition);

        iceCreamController.step(null, GUI.ACTION.LEFT, 1014); // Second movement

        //BUGGED CODE
        verify(mockIceCream).setPosition(secondLeftPosition);
    }

    @Test
    void testMovementEmpty() {
        Position initialPosition = new Position(5, 5);
        Position firstLeftPosition = new Position(4, 5);
        Position secondLeftPosition = new Position(3, 5);

        // Mock the position chain for LEFT movements
        when(mockIceCream.getPosition()).thenReturn(initialPosition);
        when(mockArena.isEmpty(firstLeftPosition)).thenReturn(false);
        when(mockArena.isEmpty(secondLeftPosition)).thenReturn(false);

        // Simulate movements
        iceCreamController.step(null, GUI.ACTION.LEFT, 1000); // First movement

        // Verify the ice cream moved to both positions
        verify(mockIceCream, times(0)).setPosition(firstLeftPosition); // First movement

        when(mockIceCream.getPosition()).thenReturn(firstLeftPosition);

        iceCreamController.step(null, GUI.ACTION.LEFT, 1014); // Second movement

        verify(mockIceCream, times(0)).setPosition(secondLeftPosition);
    }

    @Test
    void testMovementAfterDelay() {
        Position initialPosition = new Position(5, 5);
        Position firstLeftPosition = new Position(4, 5);
        Position secondLeftPosition = new Position(3, 5);

        when(mockIceCream.getPosition()).thenReturn(initialPosition);
        when(mockArena.isEmpty(firstLeftPosition)).thenReturn(true);

        iceCreamController.step(null, GUI.ACTION.LEFT, 1000); // First movement

        verify(mockIceCream).setPosition(firstLeftPosition); // First movement

        when(mockIceCream.getPosition()).thenReturn(firstLeftPosition);

        when(mockArena.isEmpty(secondLeftPosition)).thenReturn(true);

        //sleep 16 ms
        try {
            Thread.sleep(16);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        iceCreamController.step(null, GUI.ACTION.LEFT, 1016); // Second movement (after delay)

        verify(mockIceCream).setPosition(secondLeftPosition); // Second movement
    }

    @Test
    void testChangeAlive_MonsterPresentWithoutStrawberry() {

        Position initialPosition = new Position(5, 5);
        Position firstLeftPosition = new Position(4, 5);

        when(mockIceCream.getPosition()).thenReturn(initialPosition);
        when(mockArena.isEmpty(firstLeftPosition)).thenReturn(true);

        when(mockIceCream.isStrawberryActive()).thenReturn(false);

        when(mockArena.hasMonster(firstLeftPosition)).thenReturn(mock(DefaultMonster.class));

        iceCreamController.step(null, GUI.ACTION.LEFT, 1000);

        verify(mockIceCream).changeAlive();

    }

    @Test
    void testChangeAlive_NoMonsterWithoutStrawberry() {
        Position currentPosition = new Position(5, 5);

        when(mockIceCream.getPosition()).thenReturn(currentPosition);
        when(mockArena.hasMonster(currentPosition)).thenReturn(null);
        when(mockIceCream.isStrawberryActive()).thenReturn(false); // Strawberry not active

        iceCreamController.step(null, GUI.ACTION.LEFT, 1000);

        verify(mockIceCream, never()).changeAlive(); // Should NOT be called
    }

    @Test
    void testChangeAlive_MonsterPresentWithStrawberry() {
        Position currentPosition = new Position(5, 5);

        when(mockIceCream.getPosition()).thenReturn(currentPosition);
        when(mockArena.hasMonster(currentPosition)).thenReturn(mock(DefaultMonster.class));
        when(mockIceCream.isStrawberryActive()).thenReturn(true);

        iceCreamController.step(null, GUI.ACTION.LEFT, 1000);

        verify(mockIceCream, never()).changeAlive();
    }


}
