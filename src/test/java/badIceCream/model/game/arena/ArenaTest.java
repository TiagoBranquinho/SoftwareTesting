package badIceCream.model.game.arena;

import badIceCream.GUI.GUI;
import badIceCream.audio.Audio;
import badIceCream.audio.AudioController;
import badIceCream.model.Position;
import badIceCream.model.game.elements.*;
import badIceCream.model.game.elements.fruits.Fruit;
import badIceCream.model.game.elements.monsters.Monster;
import net.bytebuddy.implementation.auxiliary.MethodCallProxy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

import static org.mockito.Mockito.*;

public class ArenaTest {

    private Arena arena;

    private ArenaBuilder arenaBuilder;

    private Position position1;
    private Position position2;
    private Position position3;
    private Position position4;
    @BeforeEach
    void setUp() {
        arena = new Arena(10, 8);

        arena.setWalls(new ArrayList<>());
        arena.setMonsters(new ArrayList<>());
        arena.setFruits(new ArrayList<>());
        arena.setHotFloors(new ArrayList<>());

        position1 = new Position(1, 1);
        position2 = new Position(2, 2);
        position3 = new Position(3, 3);
        position4 = new Position(4, 4);
    }
    @Test
    void levelTest() {
        arena.setLevel(1);
        Assertions.assertEquals(1, arena.getLevel());
    }

    @Test
    void dimensionsTest() {
        Assertions.assertEquals(10, arena.getWidth());
        Assertions.assertEquals(8, arena.getHeight());
    }

    @Test
    void iceCreamTest() {
        IceCream iceCream = new IceCream(1, 1);
        iceCream.setPosition(new Position(1, 1));
        arena.setIceCream(iceCream);
        Assertions.assertEquals(iceCream, arena.getIceCream());
    }

    @Test
    void hotFloorsTest() {
        HotFloor mockHotFloor = mock(HotFloor.class);
        ArrayList<HotFloor> hotFloors = new ArrayList<>();
        hotFloors.add(mockHotFloor);
        arena.setHotFloors(hotFloors);
        Assertions.assertEquals(hotFloors, arena.getHotFloors());


        Wall mockWall = mock(Wall.class);
        when(mockWall.getPosition()).thenReturn(position1);
        when(mockHotFloor.getPosition()).thenReturn(position2);

        ArrayList<Wall> walls = new ArrayList<>();
        walls.add(mockWall);
        arena.setWalls(walls);

        Assertions.assertFalse(arena.isHotFloor(position1));
        Assertions.assertTrue(arena.isHotFloor(position2));
        Assertions.assertFalse(arena.isHotFloor(position3));

    }

    @Test
    void fruitsTest() {
        Fruit mockFruit = mock(Fruit.class);
        ArrayList<Fruit> fruits = new ArrayList<>();
        fruits.add(mockFruit);
        arena.setFruits(fruits);
        Assertions.assertEquals(fruits, arena.getFruits());

        Wall mockStoneWall = mock(StoneWall.class);
        Wall mockNotStoneWall = mock(IceWall.class);

        when(mockStoneWall.getPosition()).thenReturn(position1);
        when(mockNotStoneWall.getPosition()).thenReturn(position2);
        when(mockFruit.getPosition()).thenReturn(position3);

        ArrayList<Wall> walls = new ArrayList<>();
        walls.add(mockStoneWall);
        walls.add(mockNotStoneWall);
        arena.setWalls(walls);

        Assertions.assertFalse(arena.isEmptySpawnFruit(position1));
        Assertions.assertTrue(arena.isEmptySpawnFruit(position2));
        Assertions.assertFalse(arena.isEmptySpawnFruit(position3));
        Assertions.assertTrue(arena.isEmptySpawnFruit(position4));

        when(mockFruit.getType()).thenReturn(1);

        Assertions.assertEquals(1, arena.isFruit(position3));
        Assertions.assertEquals(-1, arena.isFruit(position4));

        Fruit mockFruit2 = mock(Fruit.class);
        Position position5 = new Position(5, 5);

        when(mockFruit2.getPosition()).thenReturn(position5);
        when(mockFruit2.getType()).thenReturn(1);

        fruits.add(mockFruit2);
        arena.setFruits(fruits);

        Assertions.assertEquals(1, arena.eatFruit(position3));
        Assertions.assertEquals(-1, arena.eatFruit(position4));
    }

    @Test
    void wallsTest() {
        Wall mockWall = mock(Wall.class);
        ArrayList<Wall> walls = new ArrayList<>();
        walls.add(mockWall);
        arena.setWalls(walls);
        Assertions.assertEquals(walls, arena.getWalls());
    }

    @Test
    void monstersTest() {
        Monster mockMonster = mock(Monster.class);
        ArrayList<Monster> monsters = new ArrayList<>();
        monsters.add(mockMonster);
        arena.setMonsters(monsters);
        Assertions.assertEquals(monsters, arena.getMonsters());


        Wall mockWall = mock(Wall.class);

        when(mockWall.getPosition()).thenReturn(position1);
        when(mockMonster.getPosition()).thenReturn(position2);

        ArrayList<Wall> walls = new ArrayList<>();
        walls.add(mockWall);
        arena.setWalls(walls);

        Assertions.assertFalse(arena.isEmptyMonsters(position1));
        Assertions.assertFalse(arena.isEmptyMonsters(position2));
        Assertions.assertTrue(arena.isEmptyMonsters(position3));

        Assertions.assertNull(arena.hasMonster(position1));
        Assertions.assertNotNull(arena.hasMonster(position2));
        Assertions.assertNull(arena.hasMonster(position3));

    }

    @Test
    void isEmptyTest() {
        Wall mockWall = mock(Wall.class);
        when(mockWall.getPosition()).thenReturn(position1);

        ArrayList<Wall> walls = new ArrayList<>();
        walls.add(mockWall);
        arena.setWalls(walls);

        Assertions.assertFalse(arena.isEmpty(position1));
        Assertions.assertTrue(arena.isEmpty(position2));
    }

    @Test
    void isEmptyNoStoneWallTest() {
        Wall mockStoneWall = mock(StoneWall.class);
        Wall mockNotStoneWall = mock(IceWall.class);
        Monster mockMonster = mock(Monster.class);

        when(mockStoneWall.getPosition()).thenReturn(position1);
        when(mockNotStoneWall.getPosition()).thenReturn(position2);
        when(mockMonster.getPosition()).thenReturn(position3);

        ArrayList<Wall> walls = new ArrayList<>();
        walls.add(mockStoneWall);
        walls.add(mockNotStoneWall);
        ArrayList<Monster> monsters = new ArrayList<>();
        monsters.add(mockMonster);
        arena.setWalls(walls);
        arena.setMonsters(monsters);

        Assertions.assertFalse(arena.isEmptyNoStoneWall(position1));
        Assertions.assertTrue(arena.isEmptyNoStoneWall(position2));
        Assertions.assertFalse(arena.isEmptyNoStoneWall(position3));
        Assertions.assertTrue(arena.isEmptyNoStoneWall(position4));
    }

    @Test
    void iceWallTest() {
        IceWall mockIceWall = mock(IceWall.class);
        StoneWall mockStoneWall = mock(StoneWall.class);

        ArrayList<Wall> walls = new ArrayList<>();
        walls.add(mockIceWall);
        walls.add(mockStoneWall);
        arena.setWalls(walls);

        when(mockIceWall.getPosition()).thenReturn(position1);
        when(mockStoneWall.getPosition()).thenReturn(position2);

        Assertions.assertTrue(arena.isIceWall(position1));
        Assertions.assertFalse(arena.isIceWall(position2));
        Assertions.assertFalse(arena.isIceWall(position3));

        arena.iceWallDestroyed(position1);
        Assertions.assertFalse(arena.isIceWall(position1));

        arena.iceWallDestroyed(position3);

        Assertions.assertFalse(arena.isIceWall(position3));
    }


    @ParameterizedTest
    @CsvSource({
            "UP",
            "DOWN",
            "RIGHT",
            "LEFT"
    })
    void powerIceCreamTest(String lastMovement) {
        Arena spy = spy(arena);
        IceWall mockWall = mock(IceWall.class);
        when(mockWall.getPosition()).thenReturn(position1);
        GUI.ACTION action = GUI.ACTION.valueOf(lastMovement);

        IceCream iceCream = mock(IceCream.class);
        when(spy.getIceCream()).thenReturn(iceCream);


        int x, y = 0;

        switch (action) {
            case UP -> {
                x = 0;
                y = -1;
                when(iceCream.getPosition()).thenReturn(position1.getDown());
            }
            case DOWN -> {
                x = 0;
                y = 1;
                when(iceCream.getPosition()).thenReturn(position1.getUp());
            }
            case RIGHT -> {
                x = 1;
                y = 0;
                when(iceCream.getPosition()).thenReturn(position1.getLeft());
            }
            case LEFT -> {
                x = -1;
                y = 0;
                when(iceCream.getPosition()).thenReturn(position1.getRight());
            }
            default -> {
                x = 0;
                y = 0;
            }
        }

        List<Wall> walls = new ArrayList<>();
        List<Monster> monsters = new ArrayList<>();
        spy.setMonsters(monsters);
        walls.add(mockWall);
        spy.setWalls(walls);
        spy.setIceCream(iceCream);

        try (MockedStatic<AudioController> audioControllerMock = mockStatic(AudioController.class)) {
            spy.powerIceCream(GUI.ACTION.valueOf(lastMovement));

            Position expectedPosition = new Position(position1.getX() + x, position1.getY() + y);


            verify(spy).iceWallDestroyed(eq(expectedPosition));

            audioControllerMock.verify(AudioController::playBreakWallSound);
        }
    }


    @ParameterizedTest
    @CsvSource({
            "UP",
            "DOWN",
            "RIGHT",
            "LEFT"
    })
    void powerIceCreamTest2(String lastMovement) {
        Arena spy = spy(arena);
        IceWall mockWall = mock(IceWall.class);
        when(mockWall.getPosition()).thenReturn(position1);

        GUI.ACTION action = GUI.ACTION.valueOf(lastMovement);

        IceCream iceCream = mock(IceCream.class);
        when(spy.getIceCream()).thenReturn(iceCream);

        when(spy.isEmptyMonsters(any(Position.class))).thenReturn(true, false);


        int x, y = 0;

        switch (action) {
            case UP -> {
                x = 0;
                y = -1;
                when(iceCream.getPosition()).thenReturn(position1.getDown());
            }
            case DOWN -> {
                x = 0;
                y = 1;
                when(iceCream.getPosition()).thenReturn(position1.getUp());
            }
            case RIGHT -> {
                x = 1;
                y = 0;
                when(iceCream.getPosition()).thenReturn(position1.getLeft());
            }
            case LEFT -> {
                x = -1;
                y = 0;
                when(iceCream.getPosition()).thenReturn(position1.getRight());
            }
            default -> {
                x = 0;
                y = 0;
            }
        }

        List<Wall> walls = new ArrayList<>();
        spy.setWalls(walls);
        spy.setIceCream(iceCream);

        try (MockedStatic<AudioController> audioControllerMock = mockStatic(AudioController.class)) {
            spy.powerIceCream(GUI.ACTION.valueOf(lastMovement));


            Assertions.assertEquals(spy.getWalls().get(0).getPosition(), position1);


            audioControllerMock.verify(AudioController::playBuildWallSound);
        }
    }


    @ParameterizedTest
    @CsvSource({
            "1, 6",
            "2, 8",
            "3, 10",
            "4, 12",
            "5, 14"
    })
    void generateNewFruitsTest(String level, String ammount) throws Exception {
        arena.generateNewFruits(Integer.parseInt(level));
        Assertions.assertEquals(Integer.parseInt(ammount), arena.getFruits().size());

        }
    }