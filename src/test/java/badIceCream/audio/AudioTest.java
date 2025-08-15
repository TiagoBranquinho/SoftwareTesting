package badIceCream.audio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import java.io.File;
import java.io.FileNotFoundException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.*;

class AudioTest {
    private Clip mockClip;

    private Audio audio;

    @BeforeEach
    void setUp() {
        mockClip = mock(Clip.class);
        audio = new Audio(mockClip);
    }

    @Test
    void testPlayNullSound () {
        audio = new Audio(null);
        audio.play();
    }

    @Test
    void testPlayOnceNullSound () {
        audio = new Audio(null);
        audio.playOnce();
    }

    @Test
    void testPlay() {
        audio.play();
        verify(mockClip, times(1)).setMicrosecondPosition(0);
        Assertions.assertEquals(0, mockClip.getMicrosecondPosition());
        verify(mockClip, times(1)).start();
        verify(mockClip, times(1)).loop(Clip.LOOP_CONTINUOUSLY);
    }

    @Test
    void testPlayOnce() {
        audio.playOnce();
        verify(mockClip, times(1)).setMicrosecondPosition(0);
        Assertions.assertEquals(0, mockClip.getMicrosecondPosition());
        verify(mockClip, times(1)).start();
    }

    @Test
    void testStop() {
        audio.stop();
        verify(mockClip, times(1)).stop();
    }

    @Test
    void testStop2() {
        Audio audio2 = new Audio(null);
        audio2.stop();
        verify(mockClip, times(0)).stop();
    }

    @Test
    void testLoadMusic() throws Exception {
        AudioInputStream mockAudioInputStream = mock(AudioInputStream.class);
        Clip mockClip = mock(Clip.class);
        FloatControl mockVolumeController = mock(FloatControl.class);

        MockedStatic<AudioSystem> mockedAudioSystem = mockStatic(AudioSystem.class);

        mockedAudioSystem.when(() -> AudioSystem.getAudioInputStream(any(File.class))).thenReturn(mockAudioInputStream);
        mockedAudioSystem.when(AudioSystem::getClip).thenReturn(mockClip);

        when(mockClip.getControl(FloatControl.Type.MASTER_GAIN)).thenReturn(mockVolumeController);

        Clip loadedClip = Audio.loadMusic("testSound.wav");

        Assertions.assertNotNull(loadedClip, "The loaded Clip should not be null");
        verify(mockClip, times(1)).open(mockAudioInputStream);
        verify(mockVolumeController, times(1)).setValue(-20.0f);

        mockedAudioSystem.close();
    }

    @Test
    void testLoadMusicNull() {
        MockedStatic<AudioSystem> mockedAudioSystem = mockStatic(AudioSystem.class);

        // Simulate exception when trying to load the audio input stream
        mockedAudioSystem.when(() -> AudioSystem.getAudioInputStream(any(File.class)))
                .thenThrow(FileNotFoundException.class);

        // Assert that FileNotFoundException is thrown
        Assertions.assertThrows(FileNotFoundException.class, () -> {
            Audio.loadMusic("testSound.wav");
        });

        mockedAudioSystem.close();
    }
}
