package src;

/**
 * Clase que representa un usuario de la biblioteca.
 */
public class Usuario {
    private String id;
    private String nombre;
    private String carrera;

    /**
     * Constructor por defecto.
     */
    public Usuario() {
    }

    /**
     * Constructor parametrizado.
     * @param id Identificador único del usuario.
     * @param nombre Nombre del usuario.
     * @param carrera Carrera del usuario.
     */
    public Usuario(String id, String nombre, String carrera) {
        this.id = id;
        this.nombre = nombre;
        this.carrera = carrera;
    }

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCarrera() { return carrera; }
    public void setCarrera(String carrera) { this.carrera = carrera; }

    @Override
    public String toString() {
        return "Usuario [" + id + "] - " + nombre + " (" + carrera + ")";
    }
}
