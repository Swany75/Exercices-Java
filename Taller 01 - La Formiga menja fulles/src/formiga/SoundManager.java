/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package formiga;
 
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
 
/**
 * @author Juan
 */
public class SoundManager {
 
    private static String[] paths;
    private static Clip[]   clips;
 
    public static void load(String[] sounds) {
        paths = sounds;
        clips = new Clip[sounds.length];
 
        for (int i = 0; i < sounds.length; i++) {
            try {
                AudioInputStream ais = AudioSystem.getAudioInputStream(new File(sounds[i]));
                Clip clip = AudioSystem.getClip();
                clip.open(ais);
                clips[i] = clip;
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                System.err.println("SoundManager: could not load \"" + sounds[i] + "\": " + e.getMessage());
            }
        }
    }
 
    public void reproduce(String path) {
        if (clips == null) return;
 
        for (int i = 0; i < paths.length; i++) {
            if (paths[i].equals(path) && clips[i] != null) {
                clips[i].setFramePosition(0); // Rewind to start
                clips[i].start();
                return;
            }
        }
 
        System.err.println("SoundManager: \"" + path + "\" was not found. Did you load it?");
    }
}
 