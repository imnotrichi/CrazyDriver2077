/*
 * Control.java
 */
package control;

import elementosJuego.Principal;
import interfaz.ConstantesGUI;
import interfaz.DlgInicioSesion;
import interfaz.DlgInstrucciones;
import interfaz.DlgPuntajes;
import interfaz.DlgRecuperarContrasenia;
import interfaz.DlgRegistroUsuario;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import objetosNegocio.Usuario;

/**
 * @author HectorEspinoza, RicardoGutierrez, AdrianaGutierrez, VictoriaVega
 */
public class Control {

    private String rutaArchivo;
    private ArrayList<String> rutasArchivos;

    public static Principal p;

    /**
     * Constructor.
     */
    public Control() {
        rutasArchivos = new ArrayList<>();
    }

    /**
     * Constructor.
     *
     * @param rutaArchivo La ruta al archivo del usuario actual.
     * @param rutasArchivos La lista de rutas a los archivos de los usuarios.
     */
    public Control(String rutaArchivo, ArrayList<String> rutasArchivos) {
        this.rutaArchivo = rutaArchivo;
        this.rutasArchivos = rutasArchivos;
    }

    /**
     * Permite obtener la ruta al archivo del usuario actual.
     *
     * @return La ruta al archivo de usuario actual.
     */
    public String getRutaArchivo() {
        return rutaArchivo;
    }

    /**
     * Permite obtener la lista con las rutas de todos los archivos de los
     * usuarios registrados.
     *
     * @return La lista con las rutas de los archivos de todos los usuarios
     * registrados.
     */
    public ArrayList<String> getRutasArchivos() {
        return rutasArchivos;
    }

    /**
     * Permite registrar los datos de un nuevo usuario y crea un archivo DAT con
     * la información.
     *
     * @param frame La ventana donde se mostrarán los cuadros de diálogo.
     */
    public void registrarUsuario(JFrame frame) {
        Usuario usuario = new Usuario();
        StringBuffer respuesta = new StringBuffer("");

        new DlgRegistroUsuario(frame, true, usuario, respuesta);

        if (respuesta.substring(0).equals(ConstantesGUI.ACEPTAR)) {

            rutaArchivo = "archivosUsuarios\\" + usuario.getUsuario() + ".dat";

            //Verifica si el archivo existe
            if (new File(rutaArchivo).exists()) {
                JOptionPane.showMessageDialog(frame, "The user to register already exists.",
                        "Existing User", JOptionPane.ERROR_MESSAGE);
                return;
            }

            //Si el archivo no existe, lo crea
            File file = new File(rutaArchivo);

            try {
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                //Se escriben los datos del usuario en el archivo
                bufferedWriter.write(usuario.getNombre() + "\n");
                bufferedWriter.write(usuario.getUsuario() + "\n");
                bufferedWriter.write(usuario.getContrasenia() + "\n");
                bufferedWriter.write('0');

                bufferedWriter.flush();
                bufferedWriter.close();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame, "Error registering user.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            //Muestra confirmación de registro del usaurio
            JOptionPane.showMessageDialog(frame, "User has successfully registered.",
                    "Successful User Registration", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Permite el inicio de sesión de un usuario que ya se ha creado
     *
     * @param frame La ventana donde se mostrarán los cuadros de diálogo.
     * @return Regresa al usuario si fue posible iniciar sesión
     */
    public Usuario iniciarSesion(JFrame frame) {
        Usuario usuario = new Usuario();
        StringBuffer respuesta = new StringBuffer("");
        DlgInicioSesion dlgInicioSesion;
        String[] datosUsuario = new String[4];

        //Llama al DlgInicioSesion
        dlgInicioSesion = new DlgInicioSesion(frame, true, usuario, respuesta);

        //Verifica que el usuario haya seleccionado iniciar sesión
        if (respuesta.substring(0).equals(ConstantesGUI.ACEPTAR)) {

            rutaArchivo = "archivosUsuarios/" + usuario.getUsuario() + ".dat";

            File file = new File(rutaArchivo);

            //Lee los datos del usuario
            try {
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String linea = bufferedReader.readLine();
                int i = 0;
                while (linea != null) {
                    datosUsuario[i] = linea;
                    linea = bufferedReader.readLine();
                    i++;
                }
                fileReader.close();
                bufferedReader.close();
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(frame, "The entered user does not exist.",
                        "Nonexistent User", JOptionPane.ERROR_MESSAGE);
                return null;
            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame, "Sign in error.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }

            //Si la contraseña es correcta
            if (usuario.getContrasenia().equals(datosUsuario[2])) {
                usuario.setNombre(datosUsuario[0]);
                usuario.setScore(Integer.parseInt(datosUsuario[3]));
                return usuario;
            } else {
                JOptionPane.showMessageDialog(frame, "The entered password is not correct.",
                        "Wrong Password", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }

    /**
     * Permite jugar una partida del juego.
     *
     * @param frame La ventana donde se mostrarán los cuadros de diálogo
     * @param usuario El usuario que jugará la partida
     */
    public void jugarPartida(JFrame frame, Usuario usuario) {
        StringBuffer respuesta = new StringBuffer("");
        DlgInstrucciones dlgInstrucciones = new DlgInstrucciones(frame, true, respuesta);
        if (respuesta.substring(0).equals(ConstantesGUI.ACEPTAR)) {
            p = new Principal(usuario);
            p.start();
        }
    }

    /**
     * Permite escribir el mejor puntaje del usuario en su archivo.
     *
     * @param usuario El usuario que jugó la partida.
     */
    public void escribirPuntajeArchivo(Usuario usuario) {
        String[] datosUsuario = new String[4];

        rutaArchivo = "archivosUsuarios/" + usuario.getUsuario() + ".dat";

        File file = new File(rutaArchivo);

        //Lee los datos del usuario
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String linea = bufferedReader.readLine();
            int i = 0;
            while (linea != null) {
                datosUsuario[i] = linea;
                linea = bufferedReader.readLine();
                i++;
            }
            fileReader.close();
            bufferedReader.close();
        } catch (IOException e) {
            return;
        }

        if (usuario.getScore() > Integer.parseInt(datosUsuario[3])) {
            try {
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                //Se escriben los datos del usuario en el archivo
                bufferedWriter.write(usuario.getNombre() + "\n");
                bufferedWriter.write(usuario.getUsuario() + "\n");
                bufferedWriter.write(usuario.getContrasenia() + "\n");
                bufferedWriter.write(Integer.toString(usuario.getScore()));

                bufferedWriter.flush();
                bufferedWriter.close();
            } catch (IOException e) {
                return;
            }
        }
    }

    /**
     * Permite leer la información que se encuentra en el archivo de HighScores
     * y la almacena en un ArrayList. Llama a otros métodos para actualizar el
     * archivo y acomodar segun el puntaje de los usuarios.
     *
     * @param puntos El puntaje que hizo el usuario en la partida.
     * @param usuario El usuario que jugó la partida.
     */
    public void verificarPuntosUsuarios(int puntos, Usuario usuario) {
        try {
            // Verificar que el puntaje sea mayor a 0
            if (puntos <= 0) {
                return;
            }

            // Leer el archivo
            File archivo = new File("archivosUsuarios/highScores.dat");
            ArrayList<String> listaString = new ArrayList<>();

            // Leer cada línea y almacenarla en la lista
            try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
                lector.lines().forEach(listaString::add);
            }

            // Actualizar puntajes
            actualizarPuntajes(listaString, puntos, usuario);

            // Ordenar la lista de puntajes
            ordenarPuntajes(listaString);

            // Limitar a 5 puntajes
            listaString = new ArrayList<>(listaString.subList(0, Math.min(10, listaString.size())));

            // Escribir el contenido actualizado de vuelta al archivo
            try (BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo))) {
                for (String linea : listaString) {
                    escritor.write(linea);
                    escritor.newLine(); // Agregar salto de línea después de cada línea
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Permite actualizar los puntajes que se encuentran dentro del archivo,
     * esto tomando en cuenta los puntos que tiene actualmente el archivo como
     * los puntos que el usuario obtuvo.
     *
     * @param listaString texto dentro del archivo
     * @param puntos puntos de la partida jugada
     * @param usuario usuario al que se le actualizarán los puntos
     */
    private void actualizarPuntajes(ArrayList<String> listaString, int puntos, Usuario usuario) {
        boolean usuarioEncontrado = false;

        for (int i = 0; i < listaString.size(); i += 2) {
            String lineaUsuario = listaString.get(i);
            String lineaPuntaje = listaString.get(i + 1);

            if (lineaUsuario.equalsIgnoreCase(usuario.getUsuario())) {
                // Actualizar puntaje existente si es mayor
                int puntajeExistente = Integer.parseInt(lineaPuntaje.trim());
                if (puntos > puntajeExistente) {
                    listaString.set(i + 1, String.valueOf(puntos));
                }
                usuarioEncontrado = true;
                break; // Salir del bucle después de actualizar el puntaje
            }
        }

        // Si el usuario no se encontró, agregarlo al final de la lista
        if (!usuarioEncontrado) {
            listaString.add(usuario.getUsuario().toUpperCase());
            listaString.add(String.valueOf(puntos));
        }
    }

    /**
     * Permite ordenar la lista de usuarios con base en el puntaje de manera
     * descendente.
     *
     * @param listaString La lista a ordenar.
     */
    private void ordenarPuntajes(ArrayList<String> listaString) {
        // Ordenar la lista de puntajes (método de burbuja simple)
        int n = listaString.size();

        for (int i = 0; i < n; i += 2) {
            for (int j = i + 2; j < n; j += 2) {
                try {
                    int puntaje1 = Integer.parseInt(listaString.get(i + 1).trim());
                    int puntaje2 = Integer.parseInt(listaString.get(j + 1).trim());

                    if (puntaje1 < puntaje2) {
                        // Intercambiar nombres de usuario y puntajes
                        String tempUsuario = listaString.get(i);
                        String tempPuntaje = listaString.get(i + 1);

                        listaString.set(i, listaString.get(j));
                        listaString.set(i + 1, listaString.get(j + 1));

                        listaString.set(j, tempUsuario);
                        listaString.set(j + 1, tempPuntaje);
                    }
                } catch (NumberFormatException e) {
                    // Manejar excepción si no se puede convertir a entero
                }
            }
        }
    }

    /**
     * Permite mostrar el DlgPuntajes con los 5 mejores puntajes del juego.
     *
     * @param frame
     */
    public void mostrarHighScores(JFrame frame) {
        DlgPuntajes dlgPuntajes = new DlgPuntajes(frame, true);
    }

    /**
     * Método el cual manda a llamar el DlgRecuperarContrasenia si el usuario la
     * ha olvidado, muestra la contraseña con un JOptionPane si los datos de
     * nombre y usuario son correctos
     *
     * @param dialog
     */
    public void recuperarContrasenia(JDialog dialog) {
        //Usuario que verificara
        Usuario usuario = new Usuario();
        StringBuffer respuesta = new StringBuffer();
        //DlgRecuperarContrasenia se muestra
        DlgRecuperarContrasenia dlg = new DlgRecuperarContrasenia(dialog, true, usuario, respuesta);

        //Se verifica que se haya seleccionado aceptar
        if (respuesta.substring(0).equals(ConstantesGUI.ACEPTAR)) {
            //se genera la ruta del archivo 
            String ruta = "archivosUsuarios\\" + usuario.getUsuario() + ".dat";;
            File file = new File(ruta);
            //En este arreglo se guardaran los datos
            String[] datosUsuario = new String[3];

            try {
                //Se hace el FileReader para acceder a los datos
                FileReader fileReader = new FileReader(file);
                //Se crea el buffered para recorrer el archivo y guardar los datos
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                //Linea actual del archivo que guarda sus valores
                String linea = bufferedReader.readLine();
                //Se recorren 3 lineas del archivo para guardar su inforamción
                for (int i = 0; i < 3; i++) {
                    //Se guarda la información de la nueva linea
                    datosUsuario[i] = linea;
                    //Se pasa a la siguiente linea del archivo
                    linea = bufferedReader.readLine();
                }
                fileReader.close();
                bufferedReader.close();
                //En caso de no encontrar el usuario
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(dialog, "The entered user does not exist.",
                        "Nonexistent User", JOptionPane.ERROR_MESSAGE);
                return;
                //En caso de no poder acceder
            } catch (IOException e) {
                JOptionPane.showMessageDialog(dialog, "Password cannot be accessed",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            //Se verifica que los datos que se ingresaron del usuario en el Dlg coincidan con la información recuperada
            if (datosUsuario[0].equals(usuario.getNombre()) && datosUsuario[1].equals(usuario.getUsuario())) {
                //Muestra la contraseña
                JOptionPane.showMessageDialog(dialog, "Password is: " + datosUsuario[2],
                        "Information", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(dialog, "The data entered is incorrect.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
