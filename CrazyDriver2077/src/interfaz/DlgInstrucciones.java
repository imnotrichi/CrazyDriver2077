/*
 * DlgInstrucciones.java
 */
package interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;

/**
 * @author HectorEspinoza, RicardoGutierrez, AdrianaGutierrez, VictoriaVega
 */
public class DlgInstrucciones extends javax.swing.JDialog {

    /**
     * Creates new form DlgInstrucciones
     *
     * @param parent Ventana sobre la que aparecerá el cuadro de diálogo.
     * @param modal true si permite acceder fuera de los límites del cuadro de
     * diálogo, false en caso contrario.
     * @param respuesta Boton presionado al salir de los cuadros de diálogos:
     * ACEPTAR = "Aceptar", CANCELAR = "Cancelar".
     */
    public DlgInstrucciones(java.awt.Frame parent, boolean modal, StringBuffer respuesta) {
        super(parent, modal);
        this.respuesta = respuesta;
        initComponents();
        btnAceptar.setFont(new Font("Pixel Digivolve", Font.PLAIN, 12));
        btnAceptar.setBackground(new Color(10, 13, 24));
        btnAceptar.setForeground(new Color(110, 113, 144));
        setSize(522, 430);
        setTitle("Instructions");
        centraCuadroDialogo(parent);
        setVisible(true);
    }

    /**
     * Este método centra el cuadro de dialogo sobre la ventana de la
     * aplicación.
     *
     * @param parent Ventana sobre la que aparecerá el cuadro de diálogo
     */
    private void centraCuadroDialogo(java.awt.Frame parent) {
        // Obtiene el tamaño y posición de la ventana de la aplicación
        Dimension frameSize = parent.getSize();
        Point loc = parent.getLocation();
        // Obtiene el tamaño del cuadro de diálogo
        Dimension dlgSize = getPreferredSize();
        // Centra el cuadro de diálogo sobre la ventana padre
        setLocation((frameSize.width - dlgSize.width) / 2 + loc.x, (frameSize.height - dlgSize.height) / 2 + loc.y);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAceptar = new javax.swing.JButton();
        fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAceptar.setBackground(new java.awt.Color(0, 0, 0));
        btnAceptar.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 14)); // NOI18N
        btnAceptar.setForeground(new java.awt.Color(0, 102, 153));
        btnAceptar.setText("Accept");
        btnAceptar.setFocusable(false);
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 330, 80, -1));

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/multimedia/imagenControles.png"))); // NOI18N
        getContentPane().add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método oyente del botón Aceptar.
     *
     * @param evt Evento al que escucha.
     */
    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        respuesta.append(ConstantesGUI.ACEPTAR);
        dispose();
    }//GEN-LAST:event_btnAceptarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JLabel fondo;
    // End of variables declaration//GEN-END:variables
    private StringBuffer respuesta;
}
