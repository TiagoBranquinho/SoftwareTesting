package badIceCream.audio;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import javax.sound.sampled.Clip;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import static org.mockito.Mockito.*;

class AudioControllerTest {

    private Clip mockClip = mock(Clip.class);

    void nullAudioController() {
        try {
            TestUtils.setPrivateStaticField(AudioController.class, "breakWallSound", null);
            TestUtils.setPrivateStaticField(AudioController.class, "buildWallSound", null);
            TestUtils.setPrivateStaticField(AudioController.class, "gameOverMusic", null);
            TestUtils.setPrivateStaticField(AudioController.class, "levelCompleteMusic", null);
            TestUtils.setPrivateStaticField(AudioController.class, "levelMusic", null);
            TestUtils.setPrivateStaticField(AudioController.class, "menuMusic", null);
            TestUtils.setPrivateStaticField(AudioController.class, "runnerMonsterSound", null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    void fillAudioController() {
        try {
            TestUtils.setPrivateStaticField(AudioController.class, "breakWallSound", new Audio(this.mockClip));
            TestUtils.setPrivateStaticField(AudioController.class, "buildWallSound", new Audio(this.mockClip));
            TestUtils.setPrivateStaticField(AudioController.class, "gameOverMusic", new Audio(this.mockClip));
            TestUtils.setPrivateStaticField(AudioController.class, "levelCompleteMusic", new Audio(this.mockClip));
            TestUtils.setPrivateStaticField(AudioController.class, "levelMusic", new Audio(this.mockClip));
            TestUtils.setPrivateStaticField(AudioController.class, "menuMusic", new Audio(this.mockClip));
            TestUtils.setPrivateStaticField(AudioController.class, "runnerMonsterSound", new Audio(this.mockClip));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void testAudioControllerInstantiation() {
        new AudioController();
    }
    @ParameterizedTest
    @CsvSource({
            "LevelMusic.wav, playLevelMusic",
            "MainMenuMusic.wav, playMenuMusic",
            "LevelCompleteMenuSound.wav, playLevelCompleteMusic",
            "GameOverMenuSound.wav, playGameOverMusic",
            "BuildWallSound.wav, playBuildWallSound",
            "BreakWallSound.wav, playBreakWallSound",
            "RunnerMonsterSound.wav, playRunnerMonsterSound"
    })
    void testPlayMusicException(String fileName, String methodName) throws Exception {

        nullAudioController();

        try (MockedStatic<Audio> mockedAudio = Mockito.mockStatic(Audio.class)) {
            mockedAudio.when(() -> Audio.loadMusic(fileName)).thenThrow(new IOException());
            Assertions.assertThrows(InvocationTargetException.class, () -> AudioController.class.getMethod(methodName).invoke(null));
        }
    }

    @ParameterizedTest
    @CsvSource({
            "LevelMusic.wav, playLevelMusic",
            "MainMenuMusic.wav, playMenuMusic",
            "LevelCompleteMenuSound.wav, playLevelCompleteMusic",
            "GameOverMenuSound.wav, playGameOverMusic",
            "BuildWallSound.wav, playBuildWallSound",
            "BreakWallSound.wav, playBreakWallSound",
            "RunnerMonsterSound.wav, playRunnerMonsterSound"
    })
    void testPlayMusic(String fileName, String methodName) throws Exception {
        nullAudioController();


        try (MockedStatic<Audio> mockedAudio = Mockito.mockStatic(Audio.class)) {
            mockedAudio.when(() -> Audio.loadMusic(fileName)).thenReturn(this.mockClip);
            AudioController.class.getMethod(methodName).invoke(null);

            mockedAudio.verify(() -> Audio.loadMusic(fileName), times(1));
            verify(this.mockClip, times(1)).start();
        }
    }

    @ParameterizedTest
    @CsvSource({
            "LevelMusic.wav, playLevelMusic",
            "MainMenuMusic.wav, playMenuMusic",
            "LevelCompleteMenuSound.wav, playLevelCompleteMusic",
            "GameOverMenuSound.wav, playGameOverMusic",
            "BuildWallSound.wav, playBuildWallSound",
            "BreakWallSound.wav, playBreakWallSound",
            "RunnerMonsterSound.wav, playRunnerMonsterSound"
    })
    void testPlayMusic2(String fileName, String methodName) throws Exception {
        fillAudioController();

        try (MockedStatic<Audio> mockedAudio = Mockito.mockStatic(Audio.class)) {
            mockedAudio.when(() -> Audio.loadMusic(fileName)).thenReturn(this.mockClip);
            AudioController.class.getMethod(methodName).invoke(null);

            verify(this.mockClip, times(1)).start();
        }
    }

    @ParameterizedTest
    @CsvSource({
            "stopLevelMusic",
            "stopMenuMusic",
            "stopGameOverMusic",
            "stopLevelCompleteMusic",
    })
    void testStopMusic(String methodName) throws Exception {
        fillAudioController();

        try (MockedStatic<Audio> mockedAudio = Mockito.mockStatic(Audio.class)) {
            mockedAudio.when(() -> Audio.loadMusic(anyString())).thenReturn(this.mockClip);

            AudioController.class.getMethod(methodName).invoke(null);

            // verify the stop function is called in the audio static
            verify(this.mockClip, times(1)).stop();
        }
    }

    @ParameterizedTest
    @CsvSource({
            "stopLevelMusic",
            "stopMenuMusic",
            "stopGameOverMusic",
            "stopLevelCompleteMusic",
    })
    void testStopMusic2(String methodName) throws Exception {
        nullAudioController();

        try (MockedStatic<Audio> mockedAudio = Mockito.mockStatic(Audio.class)) {
            mockedAudio.when(() -> Audio.loadMusic(anyString())).thenReturn(this.mockClip);

            AudioController.class.getMethod(methodName).invoke(null);

            // verify the stop function is called in the audio static
            verify(this.mockClip, times(0)).stop();
        }
    }
}
