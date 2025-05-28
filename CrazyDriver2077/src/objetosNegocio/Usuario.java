/*
 * Usuario.java
 */
package objetosNegocio;

/**
 * @author HectorEspinoza, RicardoGutierrez, AdrianaGutierrez, VictoriaVega
 */
public class Usuario {

    private String nombre;
    private String usuario;
    private String contrasenia;
    private int score;

    /**
     * Constructor.
     */
    public Usuario() {
    }

    /**
     * Constructor.
     *
     * @param nombre El nombre real del usuario.
     * @param usuario El nombre de usuario.
     * @param contrasenia La contraseña del usuario.
     */
    public Usuario(String nombre, String usuario, String contrasenia) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    /**
     * Constructor.
     *
     * @param usuario El usuario del que se copiarán los valores.
     */
    public Usuario(Usuario usuario) {
        nombre = usuario.getNombre();
        this.usuario = usuario.getUsuario();
        contrasenia = usuario.getContrasenia();
    }

    /**
     * Permite obtener el nombre real del usuario.
     *
     * @return El nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Permite establecer un nombre real al usuario.
     *
     * @param nombre El nombre del usuario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Permite obtener el nombre de usuario del usuario.
     *
     * @return El nombre de usuario del usuario.
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Permite establecer un nombre de usuario al usuario.
     *
     * @param usuario El nombre de usuario del usuario.
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Permite obtener la contraseña del usuario.
     *
     * @return La contraseña del usuario.
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * Permite establecer una contraseña al usuario.
     *
     * @param contrasenia La contraseña del usuario.
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    /**
     * Permite obtener el puntaje del usuario.
     *
     * @return El puntaje del usuario.
     */
    public int getScore() {
        return score;
    }

    /**
     * Permite establecer un puntaje al usuario.
     *
     * @param score El puntaje del usuario.
     */
    public void setScore(int score) {
        this.score = score;
    }

}
