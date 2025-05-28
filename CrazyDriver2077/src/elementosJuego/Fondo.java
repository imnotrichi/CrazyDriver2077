/*
 * Fondo.java
 */
package elementosJuego;

import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 * @author HectorEspinoza, RicardoGutierrez, AdrianaGutierrez, VictoriaVega
 */
public class Fondo {

    //Variables que delimitan el area del objeto
    int x1 = 0, y1 = 0;
    int x2 = 0, y2 = -650;
    //objeto de la clase juego
    private Juego jueguito;

    /**
     * Constructor.
     *
     * @param jueguito El juego donde se agregará el fondo.
     */
    public Fondo(Juego jueguito) {
        this.jueguito = jueguito;
    }

    /**
     * Permite mover el fondo dentro del frame.
     */
    public void mover() {
        y1 += 1;
        y2 += 1;
        if (y1 == 650 && y2 == 0) {
            y1 = 0;
            y2 = -650;
        }
    }

    /**
     * Permite dibujar el fondo en el frame.
     *
     * @param g Donde se dibujará el fondo.
     */
    public void paint(Graphics g) {
        ImageIcon auto = new ImageIcon(getClass().getResource("/Multimedia/fondoDlgJuego.jpg"));
        g.drawImage(auto.getImage(), x1, y1, 850, 650, null);
        g.drawImage(auto.getImage(), x2, y2, 850, 650, null);
    }

}
