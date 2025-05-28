/*
 * Obstaculo1.java
 */
package elementosJuego;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Area;
import javax.swing.ImageIcon;

/**
 * @author HectorEspinoza, RicardoGutierrez, AdrianaGutierrez, VictoriaVega
 */
public class Obstaculo1 {

    //Variables que delimitan el area del objeto
    Area cuerpo, automovil2;

    //objeto de la clase juego
    private Juego jueguito;

    public static int x = 0, y = -560;

    int inicio = 1, fin = 5;
    public static int carril = 1;

    /**
     * Constructor.
     *
     * @param jueguito El juego donde se mostrará el obstaculo.
     */
    public Obstaculo1(Juego jueguito) {
        this.jueguito = jueguito;
    }

    /**
     * Permite mover el obstáculo dentro del juego.
     */
    void mover() {
        if (carril == 1) {
            x = 235;
        }
        if (carril == 2) {
            x = 340;
        }
        if (carril == 3) {
            x = 450;
        }
        if (carril == 4) {
            x = 560;
        }
        if (y == 720) {
            jueguito.puntos += 10;
            if ((jueguito.puntos == 100) | (jueguito.puntos == 200) | (jueguito.puntos == 300) | (jueguito.puntos == 400)) {
                jueguito.velocidad--;
                if (jueguito.velocidad == 4) {
                    jueguito.velocidadTexto = "100km/h";
                    Auto.desplazamiento += 1;
                }
                if (jueguito.velocidad == 3) {
                    jueguito.velocidadTexto = "150km/h";
                    Auto.desplazamiento += 1;
                }
                if (jueguito.velocidad == 2) {
                    jueguito.velocidadTexto = "200km/h";
                    Auto.desplazamiento += 1;
                }
                if (jueguito.velocidad == 1) {
                    jueguito.velocidadTexto = "250km/h";
                    Auto.desplazamiento += 1;
                }
            }
            y = 0;
            carril = (int) (Math.random() * (fin - inicio) + inicio);
        } else {
            if (colision()) {
                jueguito.finJuego();
            } else {
                y += 1;
            }
        }
    }

    /**
     * Permite dibujal el obstáculo dentro del juego.
     *
     * @param g Los gráficos donde se dibujará el obstáculo.
     */
    public void paint(Graphics g) {
        ImageIcon personaje = new ImageIcon(getClass().getResource("/Multimedia/autoRojoObstaculo.png"));
        g.drawImage(personaje.getImage(), x, y, 55, 105, null);
    }

    /**
     * Permite saber cuando el obstáculo choca con el auto del usuario.
     *
     * @return true si los autos chocaron, false en caso contrario.
     */
    public boolean colision() {
        Area areaA = new Area(jueguito.carro.getBounds());
        areaA.intersect(getBounds());
        return !areaA.isEmpty();
    }

    /**
     * Permite establecer la hitbox del obstáculo
     *
     * @return La hitbox del obstáculo.
     */
    public Area getBounds() {
        Rectangle c = new Rectangle(x, y, 55, 102);
        cuerpo = new Area(c);

        automovil2 = cuerpo;
        automovil2.add(cuerpo);

        return automovil2;
    }

}
