package badIceCream.controller.game;

import badIceCream.audio.AudioController;

import badIceCream.model.Position;
import badIceCream.model.game.arena.Arena;
import badIceCream.model.game.elements.IceCream;
import badIceCream.model.game.elements.monsters.Monster;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.IOException;
import java.util.Random;

import static org.mockito.Mockito.*;

public class MonsterControllerTest {

    private MonsterController monsterController;
    private Monster mockMonster;
    private Arena mockArena;
    private StepMonsters mockStep;

    @BeforeEach
    void setUp() {
        mockMonster = mock(Monster.class);
        when(mockMonster.getPosition()).thenReturn(new Position(1, 1));
        when(mockMonster.getType()).thenReturn(1);
        mockArena = mock(Arena.class);
        IceCream mockIceCream = mock(IceCream.class);
        when(mockArena.getIceCream()).thenReturn(mockIceCream);
        when(mockIceCream.getPosition()).thenReturn(new Position(5, 5));
        mockStep = mock(StepMonsters.class);
        monsterController = new MonsterController(mockArena, mockStep, mockMonster);
    }

    @Test
    void testStep_RunnerToggle() throws IOException {
        when(mockMonster.getType()).thenReturn(3);

        try (MockedStatic<AudioController> mockAudioController = mockStatic(AudioController.class)) {
            // Simulate step before toggle time
            monsterController.step(0);

            monsterController.step(4999);

            verify(mockMonster, never()).startRunning();
            verify(mockMonster, never()).stopRunning();

            // Simulate time where toggle should happen
            monsterController.step(15001);

            mockAudioController.verify(AudioController::playRunnerMonsterSound, times(1));
            verify(mockMonster, times(1)).startRunning();

            // Simulate time for the next toggle
            monsterController.step(30000);
            verify(mockMonster, times(1)).stopRunning();

        }
    }



    @Test
    void testStep_NonRunnerMonster() throws IOException {
        when(mockMonster.getType()).thenReturn(1);
        long time = 1000;

        monsterController.step(time);

        verify(mockMonster, never()).startRunning();
        verify(mockMonster, never()).stopRunning();

        verify(mockStep).step(mockMonster, mockArena, time, 0);
    }



}
