package badIceCream.viewer;

import badIceCream.GUI.GUI;
import badIceCream.GUI.Graphics;
import badIceCream.model.Position;
import badIceCream.model.game.arena.Arena;
import badIceCream.model.game.elements.HotFloor;
import badIceCream.model.game.elements.IceCream;
import badIceCream.model.game.elements.Wall;
import badIceCream.model.game.elements.fruits.Fruit;
import badIceCream.model.game.elements.monsters.Monster;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.mockito.Mockito.*;

public class ArenaViewerTest {

    private Arena mockArena;
    private Graphics mockGraphics;
    private FruitViewer mockFruitViewer;
    private MonsterViewer mockMonsterViewer;
    private WallViewer mockWallViewer;
    private HotFloorViewer mockHotFloorViewer;
    private IceCreamViewer mockIceCreamViewer;
    private ArenaViewer arenaViewer;

    @BeforeEach
    void setUp() {
        mockArena = mock(Arena.class);
        mockGraphics = mock(Graphics.class);
        mockFruitViewer = mock(FruitViewer.class);
        mockMonsterViewer = mock(MonsterViewer.class);
        mockWallViewer = mock(WallViewer.class);
        mockHotFloorViewer = mock(HotFloorViewer.class);
        mockIceCreamViewer = mock(IceCreamViewer.class);

        arenaViewer = new ArenaViewer(mockArena, mockFruitViewer, mockMonsterViewer, mockWallViewer, mockHotFloorViewer, mockIceCreamViewer);
    }

    @Test
    void testDrawFruits() {
        Fruit mockFruit = mock(Fruit.class);
        when(mockArena.getFruits()).thenReturn(List.of(mockFruit));
        when(mockFruit.getType()).thenReturn(1);

        arenaViewer.drawElements(mockGraphics);

        verify(mockFruitViewer).draw(mockFruit, mockGraphics, 1);
    }

    @Test
    void testDrawMonsters() {
        Monster mockMonster = mock(Monster.class);
        when(mockArena.getMonsters()).thenReturn(List.of(mockMonster));
        when(mockMonster.getType()).thenReturn(2);

        arenaViewer.drawElements(mockGraphics);

        verify(mockMonsterViewer).draw(mockMonster, mockGraphics, 2);
    }

    @ParameterizedTest
    @CsvSource({
            "2, -1, -1, 2, 1",  // Wall type 2
            "1, 1, -1, 3, 1",   // Wall type 1 with fruit type 1
            "1, 2, -1, 4, 1",   // Wall type 1 with fruit type 2
            "1, 3, -1, 5, 1",   // Wall type 1 with fruit type 3
            "1, 4, -1, 6, 1",   // Wall type 1 with fruit type 4
            "1, 5, -1, 7, 1",   // Wall type 1 with fruit type 5
            "1, -1, 2, 9, 0",   // Wall type 1 with monster type 2 and UP action
            "1, -1, 3, 1, 0",   // Wall type 1 with monster type 3 and UP action
            "1, -1, 2, 10, 1",   // Wall type 1 with monster type 2 and RIGHT action
            "1, -1, 2, 11, 3",   // Wall type 1 with monster type 2 and LEFT action
            "1, -1, 2, 8, 2",   // Default case for wall type 1
            "-1, -1, 2, 1, 2"   // Default case for wall type -1

    })
    void testDrawWalls(int wallType, int fruitType, int monsterType, int expectedDrawType, int lastAction) {
        Wall mockWall = mock(Wall.class);
        Position mockPosition = new Position(5, 5);

        when(mockWall.getType()).thenReturn(wallType);
        when(mockWall.getPosition()).thenReturn(mockPosition);
        when(mockArena.getWalls()).thenReturn(List.of(mockWall));
        when(mockArena.isFruit(mockPosition)).thenReturn(fruitType);

        if (monsterType != -1) {
            Monster mockMonster = mock(Monster.class);
            when(mockArena.hasMonster(mockPosition)).thenReturn(mockMonster);
            when(mockMonster.getType()).thenReturn(monsterType);
            when(mockMonster.getLastAction()).thenReturn(GUI.ACTION.values()[lastAction]);
        } else {
            //when(mockArena.hasMonster(mockPosition)).thenReturn(null);
        }



        arenaViewer.drawElements(mockGraphics);

        verify(mockWallViewer).draw(mockWall, mockGraphics, expectedDrawType);
    }

    @ParameterizedTest
    @CsvSource({
            "1, 0, UP, 1",   // Monster type 1 with UP action
            "1, 0, RIGHT, 2",   // Monster type 1 with RIGHT action
            "1, 0, LEFT, 3",   // Monster type 1 with LEFT action
            "1, 0, NONE, 4",   // Monster type 1 DEFAULT
            "2, 0, UP, 5", // Monster type 2 with RIGHT action
            "2, 0, RIGHT, 6", // Monster type 2 with RIGHT action
            "2, 0, LEFT, 7", // Monster type 2 with RIGHT action
            "2, 0, NONE, 8", // Monster type 2 DEFAULT
            "3, 0, UP, 9", // Monster type 3 no running with UP action
            "3, 0, RIGHT, 10", // Monster type 3 no running with RIGHT action
            "3, 0, LEFT, 11", // Monster type 3 no running with LEFT action
            "3, 0, NONE, 12", // Monster type 3 no running DEFAULT
            "3, 1, UP, 13", // Monster type 3 running with UP action
            "3, 1, RIGHT, 14", // Monster type 3 running with RIGHT action
            "3, 1, LEFT, 15", // Monster type 3 running with LEFT action
            "3, 1, NONE, 16", // Monster type 3 running DEFAULT
            "4, 0, UP, 17", // Monster type 4 running with UP action
            "4, 0, RIGHT, 18", // Monster type 4 running with RIGHT action
            "4, 0, LEFT, 19", // Monster type 4 running with LEFT action
            "4, 0, NONE, 20", // Monster type 4 running DEFAULT

    })
    void testDrawHotFloorsMonster(int monsterType, int running, String lastAction, int expectedDrawType) {
        HotFloor mockHotFloor = mock(HotFloor.class);
        Position mockPosition = new Position(3, 3);

        when(mockHotFloor.getPosition()).thenReturn(mockPosition);
        when(mockArena.getHotFloors()).thenReturn(List.of(mockHotFloor));

        Monster mockMonster = mock(Monster.class);
        when(mockArena.hasMonster(mockPosition)).thenReturn(mockMonster);
        when(mockMonster.getType()).thenReturn(monsterType);
        if (running == 1){
            when(mockMonster.isRunning()).thenReturn(true);
        }
        else {
            when(mockMonster.isRunning()).thenReturn(false);
        }
        when(mockMonster.getLastAction()).thenReturn(GUI.ACTION.valueOf(lastAction));

        arenaViewer.drawElements(mockGraphics);

        verify(mockHotFloorViewer).draw(mockHotFloor, mockGraphics, expectedDrawType);
    }

    @ParameterizedTest
    @CsvSource({
            "1, 21",   // Fruit type 1
            "2, 22",   // Fruit type 2
            "3, 23",   // Fruit type 3
            "4, 24",   // Fruit type 4
            "5, 25",   // Fruit type 5

    })
    void testDrawHotFloorsFruit(int fruitType, int expectedDrawType) {
        HotFloor mockHotFloor = mock(HotFloor.class);
        Position mockPosition = new Position(3, 3);

        when(mockHotFloor.getPosition()).thenReturn(mockPosition);
        when(mockArena.getHotFloors()).thenReturn(List.of(mockHotFloor));

        Fruit mockFruit = mock(Fruit.class);
        when(mockArena.isFruit(mockPosition)).thenReturn(fruitType);

        arenaViewer.drawElements(mockGraphics);

        verify(mockHotFloorViewer).draw(mockHotFloor, mockGraphics, expectedDrawType);
    }

    @ParameterizedTest
    @CsvSource({
            "UP, 26",
            "RIGHT, 27",
            "LEFT, 28",
            "NONE, 29"

    })
    void testDrawHotFloorsIceCream(String lastMovement, int expectedDrawType) {
        HotFloor mockHotFloor = mock(HotFloor.class);
        Position mockPosition = new Position(3, 3);

        when(mockArena.hasMonster(mockPosition)).thenReturn(null);
        when(mockArena.isFruit(mockPosition)).thenReturn(-1);

        when(mockHotFloor.getPosition()).thenReturn(mockPosition);
        when(mockArena.getHotFloors()).thenReturn(List.of(mockHotFloor));

        IceCream mockIceCream = mock(IceCream.class);
        when(mockArena.getIceCream()).thenReturn(mockIceCream);
        when(mockIceCream.getLastMovement()).thenReturn(GUI.ACTION.valueOf(lastMovement));
        when(mockIceCream.getPosition()).thenReturn(mockPosition);

        arenaViewer.drawElements(mockGraphics);

        verify(mockHotFloorViewer).draw(mockHotFloor, mockGraphics, expectedDrawType);
    }

    @Test
    void testDrawIceCream() {
        IceCream mockIceCream = mock(IceCream.class);
        when(mockArena.getIceCream()).thenReturn(mockIceCream);

        arenaViewer.drawElements(mockGraphics);

        verify(mockIceCreamViewer).draw(mockIceCream, mockGraphics, 1);
    }

    @Test
    void testDefault() {
        HotFloor mockHotFloor = mock(HotFloor.class);
        Position mockPosition = new Position(3, 3);

        when(mockArena.hasMonster(mockPosition)).thenReturn(null);
        when(mockArena.isFruit(mockPosition)).thenReturn(-1);

        when(mockHotFloor.getPosition()).thenReturn(mockPosition);
        when(mockArena.getHotFloors()).thenReturn(List.of(mockHotFloor));

        IceCream mockIceCream = mock(IceCream.class);
        when(mockArena.getIceCream()).thenReturn(mockIceCream);
        when(mockIceCream.getPosition()).thenReturn(mockPosition.getRight());

        arenaViewer.drawElements(mockGraphics);

        verify(mockHotFloorViewer).draw(mockHotFloor, mockGraphics, 0);
    }
}
