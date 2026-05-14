/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package insercioeliminacioarrayordenat;

/**
 *
 * @author Juan
 */
public class Timer {
    private long startTime;
    private long stopTime;
    
    public void start() {
        this.startTime = System.currentTimeMillis();
    }
    
    public void stop() {
        this.stopTime = System.currentTimeMillis();
    }
    
    public double getSeconds() {
        return (stopTime - startTime);
    }
    
}
