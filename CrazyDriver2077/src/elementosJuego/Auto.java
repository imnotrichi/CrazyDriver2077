/*
 * Auto.java
 */
package elementosJuego;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;
import javax.swing.ImageIcon;

/**
 * @author HectorEspinoza, RicardoGutierrez, AdrianaGutierrez, VictoriaVega
 */
public class Auto {

    //Variables que delimitan el area del objeto
    Area cuerpo, automovil;

    public static int x = 400, y = 500, ancho = 52, alto = 95;
    public static int desplazamiento = 5;

    /**
     * Permite dibujar al auto dentro del frame.
     *
     * @param g Los gráficos donde se dibujará el auto.
     */
    public void paint(Graphics g) {
        if (x > 590) {
            x = 590;
        }
        if (x < 200) {
            x = 200;
        }
        if (y > 505) {
            y = 505;
        }
        if (y < 10) {
            y = 10;
        }
        ImageIcon personaje = new ImageIcon(getClass().getResource("/Multimedia/spriteAutoPrincipal.png"));
        g.drawImage(personaje.getImage(), x, y, 58, 105, null);
    }

    /**
     * Permite mover al auto dependiendo de la tecla que se presionó.
     *
     * @param e La tecla que se peresionó.
     */
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            x -= desplazamiento;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            x += desplazamiento;
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            y -= desplazamiento;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            y += desplazamiento;
        }
    }

    /**
     * Permite obtner la hitbox del auto.
     *
     * @return La hitbox del auto.
     */
    public Area getBounds() {
        Rectangle c = new Rectangle(x, y, 58, 102);
        cuerpo = new Area(c);

        automovil = cuerpo;
        automovil.add(cuerpo);

        return automovil;
    }

}
