package zelda.sounds;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundManager {


    public static void playSound(String soundFilePath, int volumePercent) {

        try {
            File file = new File(soundFilePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            // Obtenir l'objet FloatControl pour ajuster le volume
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float volume = volumePercent / 100f;
            float dB = (float) (Math.log10(volume) * 20f);
            gainControl.setValue(dB);

            clip.start();

			} 
            catch (UnsupportedAudioFileException e) {
            	 System.err.println("Error playing sound: " + e.getMessage());
			}
            catch (LineUnavailableException e) {
            	 System.err.println("Error playing sound: " + e.getMessage());
			} 
            catch (IOException e) {
              System.err.println("Error playing sound: " + e.getMessage());
			} 
		
    }
   
   
}


