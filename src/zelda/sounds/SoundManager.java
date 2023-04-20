package zelda.sounds;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundManager {

	public static void playSound(String soundFilePath) {
            
            try {
            	File file = new File(soundFilePath);
				AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
				Clip clip;
				clip = AudioSystem.getClip();
				clip.open(audioStream);
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
