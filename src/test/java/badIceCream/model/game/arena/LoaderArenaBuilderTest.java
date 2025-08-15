package badIceCream.model.game.arena;

import badIceCream.model.Position;
import badIceCream.model.game.elements.*;
import badIceCream.model.game.elements.fruits.*;
import badIceCream.model.game.elements.monsters.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoaderArenaBuilderTest {

    private LoaderArenaBuilder builder;
    private Arena arena;
    @BeforeEach
    void setUp() throws IOException {
        builder = new LoaderArenaBuilder(1);
        arena = builder.createArena();
    }
    @Test
    void testDimensions() {
        assertEquals(builder.getWidth(), arena.getWidth());
        assertEquals(builder.getHeight(), arena.getHeight());
        assertEquals(14, builder.getWidth());
        assertEquals(18, builder.getHeight());
    }

    @Test
    void testWalls()  {
        List<Wall> walls = builder.createWalls();
        assertFalse(walls.isEmpty());
        assertFalse(arena.getWalls().isEmpty());

        int stoneWallCount = 60;
        int iceWallCount = 16;


        assertEquals(arena.getWalls().stream().filter(wall -> wall instanceof StoneWall).count(), walls.stream().filter(wall -> wall instanceof StoneWall).count());
        assertEquals(stoneWallCount, walls.stream().filter(wall -> wall instanceof StoneWall).count());

        assertEquals(arena.getWalls().stream().filter(wall -> wall instanceof IceWall).count(), walls.stream().filter(wall -> wall instanceof IceWall).count());
        assertEquals(iceWallCount, walls.stream().filter(wall -> wall instanceof IceWall).count());

    }

    @Test
    void testMonsters() {
        List<Monster> monsters = builder.createMonsters();
        assertFalse(monsters.isEmpty());
        assertFalse(arena.getMonsters().isEmpty());

        int defaultMonsterCount = 2;
        int jumperMonsterCount = 2;
        int runnerMonsterCount = 0;
        int wallBreakerMonsterCount = 0;


        assertEquals(arena.getMonsters().stream().filter(monster -> monster instanceof DefaultMonster).count(), monsters.stream().filter(monster -> monster instanceof DefaultMonster).count());
        assertEquals(defaultMonsterCount, monsters.stream().filter(monster -> monster instanceof DefaultMonster).count());

        assertEquals(arena.getMonsters().stream().filter(monster -> monster instanceof JumperMonster).count(), monsters.stream().filter(monster -> monster instanceof JumperMonster).count());
        assertEquals(jumperMonsterCount, monsters.stream().filter(monster -> monster instanceof JumperMonster).count());

        assertEquals(arena.getMonsters().stream().filter(monster -> monster instanceof RunnerMonster).count(), monsters.stream().filter(monster -> monster instanceof RunnerMonster).count());
        assertEquals(runnerMonsterCount, monsters.stream().filter(monster -> monster instanceof RunnerMonster).count());

        assertEquals(arena.getMonsters().stream().filter(monster -> monster instanceof WallBreakerMonster).count(), monsters.stream().filter(monster -> monster instanceof WallBreakerMonster).count());
        assertEquals(wallBreakerMonsterCount, monsters.stream().filter(monster -> monster instanceof WallBreakerMonster).count());

    }

    @Test
    void testIceCream() {
        IceCream iceCream = builder.createIceCream();
        assertEquals(arena.getIceCream().getPosition(), iceCream.getPosition());

        assertNotNull(arena.getIceCream());
        assertNotNull(iceCream);
        assertTrue(arena.getIceCream().getPosition().getX() >= 0 && arena.getIceCream().getPosition().getX() < arena.getWidth());

        assertTrue(arena.getIceCream().getPosition().getY() >= 0 && arena.getIceCream().getPosition().getY() < arena.getHeight());

        assertEquals(iceCream.getPosition().getX(), 6);
        assertEquals(iceCream.getPosition().getX(), 6);

        assertEquals(arena.getIceCream().getPosition().getY(), 16);
        assertEquals(arena.getIceCream().getPosition().getY(), 16);

    }

    @Test
    void testFruits() {
        List<Fruit> fruits = builder.createFruits();
        assertFalse(fruits.isEmpty());
        assertFalse(arena.getFruits().isEmpty());

        int appleCount = 0;
        int bananaCount = 0;
        int cherryCount = 0;
        int pineappleCount = 24;
        int strawberryCount = 0;

        assertEquals(arena.getFruits().stream().filter(fruit -> fruit instanceof CherryFruit).count(), fruits.stream().filter(fruit -> fruit instanceof CherryFruit).count());
        assertEquals(cherryCount, fruits.stream().filter(fruit -> fruit instanceof CherryFruit).count());

        assertEquals(arena.getFruits().stream().filter(fruit -> fruit instanceof BananaFruit).count(), fruits.stream().filter(fruit -> fruit instanceof BananaFruit).count());
        assertEquals(bananaCount, fruits.stream().filter(fruit -> fruit instanceof BananaFruit).count());

        assertEquals(arena.getFruits().stream().filter(fruit -> fruit instanceof StrawberryFruit).count(), fruits.stream().filter(fruit -> fruit instanceof StrawberryFruit).count());
        assertEquals(strawberryCount, fruits.stream().filter(fruit -> fruit instanceof StrawberryFruit).count());

        assertEquals(arena.getFruits().stream().filter(fruit -> fruit instanceof PineappleFruit).count(), fruits.stream().filter(fruit -> fruit instanceof PineappleFruit).count());
        assertEquals(pineappleCount, fruits.stream().filter(fruit -> fruit instanceof PineappleFruit).count());

        assertEquals(arena.getFruits().stream().filter(fruit -> fruit instanceof AppleFruit).count(), fruits.stream().filter(fruit -> fruit instanceof AppleFruit).count());
        assertEquals(appleCount, fruits.stream().filter(fruit -> fruit instanceof AppleFruit).count());
    }

        @Test
    void testHotFloors() {
        List<HotFloor> hotFloors = builder.createHotFloors();
        assertEquals(arena.getHotFloors().size(), hotFloors.size());
        assertEquals(60, hotFloors.size());
    }

    @Test
    void testLevel() {
        assertEquals(1, builder.getLevel());
        assertEquals(1, arena.getLevel());
    }

}
