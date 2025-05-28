/*
 * Principal.java
 */
package elementosJuego;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import objetosNegocio.Usuario;

/**
 * @author HectorEspinoza, RicardoGutierrez, AdrianaGutierrez, VictoriaVega
 */
public class Principal extends Thread {

    private Usuario usuario;
    private Juego juego;
    private int puntosP;

    /**
     * Constructor.
     *
     * @param usuario El usuario que jugará la partida.
     */
    public Principal(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Permite iniciar el juego mediante un hilo.
     */
    @Override
    public void run() {
        JFrame ventana = new JFrame("Videogame");

        juego = new Juego();
        ventana.add(juego);

        ventana.setSize(850, 650);
        ventana.setResizable(false);
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
        Juego.nombreGanador = usuario.getUsuario();
        Juego.puntajeMasAlto = usuario.getScore();
        Juego.puntos = 0;
        while (true) {
            if (!Juego.fin) {
                juego.repaint();
            }
            if (Juego.fin) {
                Juego.reiniciaJuego = JOptionPane.showConfirmDialog(juego, "You've lost, want to play again?",
                        "You've lost", JOptionPane.YES_NO_OPTION);
                if (Juego.reiniciaJuego == JOptionPane.YES_OPTION) {
                    Juego.fin = false;
                    Juego.reiniciaJuego = -1;
                    Obstaculo.y = -200;
                    Obstaculo1.y = -560;
                    Auto.y = 500;
                    Auto.x = 400;
                    if (Juego.puntajeMasAlto > Juego.puntos) {
                        Juego.puntos = Juego.puntajeMasAlto;
                        puntosP = Juego.puntos;
                    } else {
                        Juego.puntajeMasAlto = Juego.puntos;
                        puntosP = Juego.puntajeMasAlto;
                    }
                    Juego.puntos = 0;
                    Juego.velocidad = 8;
                    Juego.velocidadTexto = "50km/h";
                    Juego.sonidoLoop.loop();
                } else if (Juego.reiniciaJuego == JOptionPane.NO_OPTION) {
                    if (Juego.puntajeMasAlto > Juego.puntos) {
                        Juego.puntos = Juego.puntajeMasAlto;
                        puntosP = Juego.puntos;
                    }
                    usuario.setScore(Juego.puntos);
                    puntosP = usuario.getScore();
                    DlgJuegoTerminado dlgPuntaje = new DlgJuegoTerminado(ventana, true, usuario, Juego.puntos);
                    ventana.dispose();
                    break;
                }
            } else {
                juego.repaint();
                usuario.setScore(Juego.puntos);
                try {
                    Thread.sleep(2);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Juego.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
