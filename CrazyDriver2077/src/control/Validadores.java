/*
 * Validadores.java
 */
package control;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author HectorEspinoza, RicardoGutierrez, AdrianaGutierrez, VictoriaVega
 */
public class Validadores {

    /**
     * Permite validar que la cadena de texto enviada en el parámetro represente
     * el nombre de una persona.
     *
     * @param nombre Cadena de texto que representa el nombre de una persona.
     * @return True si el nombre es válido, false en caso contrario
     */
    public boolean validarNombre(String nombre) {
        String patron = "^[A-Za-zÁáÉéÍíÓóÚúÜüÑñÇç]+([-'][A-Za-zÁáÉéÍíÓóÚúÜüÑñÇç]+)*(\\s+[A-Za-zÁáÉéÍíÓóÚúÜüÑñÇç]+([-'][A-Za-zÁáÉéÍíÓóÚúÜüÑñÇç]+)*)*$";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(nombre);
        return matcher.matches();
    }

    /**
     * Permite validar que la cadena de texto enviada en el parámetro represente
     * un nombre de usuario.
     *
     * @param usuario Cadena de texto que representa un usuario
     * @return True si el usuario es válido, false en caso contrario
     */
    public boolean validarUsuario(String usuario) {
        String patron = "(\\w[.]?){3,15}";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(usuario);
        return matcher.matches();
    }

    /**
     * Permite validar que la cadena de texto enviada en el parámetro represente
     * una contraseña.
     *
     * @param contrasenia Cadena de texto que debe coincidir con una contraseña.
     * @return True si la contraseña es válida, false en caso contrario
     */
    public boolean validarContrasenia(String contrasenia) {
        String patron = "^(?=.*[A-Z])(?=.*[\\W_]).{8,}$";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(contrasenia);
        return matcher.matches();
    }

    /**
     * Permite validar que las dos cadenas de texto enviadas en los parámetros
     * sean iguales.
     *
     * @param contrasenia Cadena de texto que representa una contraseña.
     * @param confirmarContrasenia Cadena de texto que representa una contraseña
     * y debe ser igual a la otra contraseña.
     * @return True si ambas contraseñas son iguales, false en caso contrario
     */
    public boolean validarConfirmarContrasenia(String contrasenia, String confirmarContrasenia) {
        return confirmarContrasenia.equals(contrasenia);
    }

}
