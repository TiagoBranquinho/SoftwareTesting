package badIceCream.model.game.elements.monsters;

import badIceCream.GUI.GUI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MonsterTest {
    @Test
    public void testGetType() {
        Monster defaultMonster = new DefaultMonster(0, 0);
        Monster jumperMonster = new JumperMonster(0, 0);
        Monster runnerMonster = new RunnerMonster(0, 0);
        Monster WallBreakerMonster = new WallBreakerMonster(0, 0);

        Assertions.assertEquals(1, defaultMonster.getType());
        Assertions.assertEquals(2, jumperMonster.getType());
        Assertions.assertEquals(3, runnerMonster.getType());
        Assertions.assertEquals(4, WallBreakerMonster.getType());
    }

    @Test
    public void testRunner() {
        RunnerMonster runnerMonster = new RunnerMonster(0, 0);

        runnerMonster.startRunning();
        Assertions.assertTrue(runnerMonster.isRunning());
        runnerMonster.stopRunning();
        Assertions.assertFalse(runnerMonster.isRunning());
    }

    @Test
    public void testMovement(){
        Monster monster = new DefaultMonster(0, 0);
        monster.startRunning();
        Assertions.assertEquals(monster.getLastAction(), GUI.ACTION.DOWN);
        monster.setLastAction(GUI.ACTION.UP);
        Assertions.assertEquals(monster.getLastAction(), GUI.ACTION.UP);
        monster.stopRunning();
    }
}
