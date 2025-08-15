package badIceCream.utils;

import badIceCream.model.Position;
import badIceCream.model.game.arena.Arena;
import badIceCream.model.game.elements.IceCream;
import badIceCream.model.game.elements.monsters.Monster;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ShortestPathNextMoveTest {

    private ShortestPathNextMove shortestPathNextMove;
    private Arena mockArena;
    private Monster mockMonster;

    private IceCream mockIceCream;

    @BeforeEach
    void setUp() {
        shortestPathNextMove = new ShortestPathNextMove();
        mockArena = mock(Arena.class);
        mockMonster = mock(Monster.class);
        mockIceCream = mock(IceCream.class);
    }

    @Test
    void testFindShortestPath_MonsterAlreadyAtIceCream() {
        Position monsterPos = new Position(1, 1);
        when(mockMonster.getPosition()).thenReturn(monsterPos);
        when(mockArena.getIceCream()).thenReturn(mockIceCream);
        when(mockIceCream.getPosition()).thenReturn(monsterPos);

        assertNull(shortestPathNextMove.findShortestPath(mockMonster, mockArena));
    }

    @Test
    void testFindShortestPath() {
        Position monsterPos = new Position(1, 1);
        Position iceCreamPos = new Position(3, 3);
        when(mockMonster.getPosition()).thenReturn(monsterPos);
        when(mockArena.getIceCream()).thenReturn(mockIceCream);
        when(mockIceCream.getPosition()).thenReturn(iceCreamPos);
        when(mockArena.isEmptyMonsters(any(Position.class))).thenReturn(true);

        Position downPos = new Position(1, 2);
        Position leftPos = new Position(0, 1);
        Position upPos = new Position(1, 0);
        Position rightPos = new Position(2, 1);
        List<Position> positions = new ArrayList<>(List.of(downPos, leftPos, upPos, rightPos));
        when(mockArena.isEmptyMonsters(downPos)).thenReturn(true);
        when(mockArena.isEmptyMonsters(leftPos)).thenReturn(true);
        when(mockArena.isEmptyMonsters(upPos)).thenReturn(true);
        when(mockArena.isEmptyMonsters(rightPos)).thenReturn(true);

        assertEquals(downPos, shortestPathNextMove.findShortestPath(mockMonster, mockArena));
    }

    @Test
    void testFindShortestPath_NoValidPath() {
        Position monsterPos = new Position(1, 1);
        Position iceCreamPos = new Position(3, 3);
        when(mockMonster.getPosition()).thenReturn(monsterPos);
        when(mockArena.getIceCream()).thenReturn(mockIceCream);
        when(mockIceCream.getPosition()).thenReturn(iceCreamPos);
        when(mockArena.isEmptyMonsters(any(Position.class))).thenReturn(false);

        assertNull(shortestPathNextMove.findShortestPath(mockMonster, mockArena));
    }

    @Test
    void testFindShortestPath_VerifyCostCalculation() {
        Position monsterPos = new Position(1, 1);
        Position iceCreamPos = new Position(3, 3);
        when(mockMonster.getPosition()).thenReturn(monsterPos);
        when(mockArena.getIceCream()).thenReturn(mockIceCream);
        when(mockIceCream.getPosition()).thenReturn(iceCreamPos);
        when(mockArena.isEmptyMonsters(any(Position.class))).thenReturn(true);

        Position downPos = new Position(1, 2);
        Position rightPos = new Position(2, 1);
        when(mockArena.isEmptyMonsters(downPos)).thenReturn(true);
        when(mockArena.isEmptyMonsters(rightPos)).thenReturn(true);

        shortestPathNextMove.findShortestPath(mockMonster, mockArena);
        verify(mockArena, atLeastOnce()).isEmptyMonsters(any(Position.class));
    }
}