/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snake;

import java.awt.Color;

/**
 *
 * @author Juan
 */

public class SettingsState {
    public int     speed          = 2;       // 1=Slow, 2=Normal, 3=Fast
    public int     gridSizeIndex  = 1;       // 0=Small, 1=Normal, 2=Large
    public boolean squaresVisible = true;
    public int     appleCount     = 1;
    public boolean randomColors   = false;
    public Color   headColor      = new Color(0x1D49A5);
    public Color   bodyStartColor = new Color(0x315EC4);
    public Color   bodyEndColor   = new Color(0x507EF6);
}
