/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package formiga;
 
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
 
/**
 * @author Juan Dalmau
 * Font d'inspiració: https://www.coderslexicon.com/playing-and-thottling-sound-clips-in-java/
 */
public class SoundManager {

    public static void reproduce(String path) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File(path));
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close();
                }
            });
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("SoundManager: error reproduciendo \"" + path + "\": " + e.getMessage());
        }
    }
    
}
 