/*
 * Juego.java
 */
package elementosJuego;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import javax.swing.JPanel;

/**
 * @author HectorEspinoza, RicardoGutierrez, AdrianaGutierrez, VictoriaVega
 */
public class Juego extends JPanel {

    Auto carro = new Auto();
    Obstaculo obs = new Obstaculo(this);
    Obstaculo1 obs2 = new Obstaculo1(this);
    Fondo fondo = new Fondo(this);
    public static int reiniciaJuego = -1;
    public static boolean fin;
    public static int puntos = 0, velocidad = 10, puntajeMasAlto = 0;
    public static String velocidadTexto = "50km/h";
    public static String nombreGanador = "richi.ast";

    URL direccionSonidoSalto, direccionSonidoChoque, direccionSonidoLoop;
    AudioClip sonidoChoque, sonidoSalto;
    static AudioClip sonidoLoop;

    /**
     * Constructor.
     */
    public Juego() {
        direccionSonidoChoque = getClass().getResource("/Multimedia/choque.wav");
        sonidoChoque = Applet.newAudioClip(direccionSonidoChoque);

        direccionSonidoLoop = getClass().getResource("/Multimedia/musicaJuego.wav");
        sonidoLoop = Applet.newAudioClip(direccionSonidoLoop);
        sonidoLoop.loop();

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                carro.keyPressed(e);
            }
        });
        setFocusable(true);
    }

    /**
     * Permite dibujar todos los elementos del juego.
     *
     * @param g Los gráficos donde se dibujarán los elementos del juego.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        fondo.paint(g2);
        obs2.paint(g2);
        obs.paint(g2);
        carro.paint(g2);
        dibujarRectangulos(g2);
        dibujarPuntaje(g2);
        mover();
    }

    /**
     * Permite mover todos los elementos necesarios del juego.
     */
    public void mover() {
        obs.mover();
        obs2.mover();
        fondo.mover();
    }

    /**
     * Permite dibujar elementos como el puntaje, la velocidad y el usuario que
     * se encuentra jugando.
     *
     * @param g Los gráficos donde se dibujarán los elementos.
     */
    public void dibujarPuntaje(Graphics2D g) {
        Graphics2D g1 = g;
        Font score = new Font("Pixel Digivolve", Font.PLAIN, 18);
        g.setFont(score);
        g.setColor(new Color(189, 0, 255));
        g1.drawString("User:", 8, 60);
        g1.drawString("Highscore:", 710, 60);
        g1.drawString("Score:", 710, 150);
        g1.drawString("Speed:", 710, 540);

        g.setColor(new Color(255, 0, 214));
        g1.drawString(nombreGanador, 8, 90);
        g1.drawString("" + puntajeMasAlto, 710, 90);
        g1.drawString("" + puntos, 710, 180);
        g1.drawString("" + velocidadTexto, 710, 570);
    }

    /**
     * Permite dibujar rectángulos debajo de los puntajes, la velocidad y el
     * usuario que se encuentra juegando para que estos se puedan apreciar de
     * mejor manera.
     *
     * @param g Los gráficos donde se dibujarán los rectángulos.
     */
    public void dibujarRectangulos(Graphics2D g) {
        g.setColor(new Color(3, 23, 47, 170));
        g.fillRect(4, 41, 185, 55);
        g.fillRect(704, 41, 119, 55);
        g.fillRect(704, 130, 119, 55);
        g.fillRect(704, 523, 119, 55);

        g.setColor(new Color(3, 23, 47));
        g.drawRect(4, 41, 185, 55);
        g.drawRect(704, 41, 119, 55);
        g.drawRect(704, 130, 119, 55);
        g.drawRect(704, 523, 119, 55);
    }

    /**
     * Permite detener el juego.
     */
    public void finJuego() {
        sonidoLoop.stop();
        sonidoChoque.play();
        fin = true;
    }
}
