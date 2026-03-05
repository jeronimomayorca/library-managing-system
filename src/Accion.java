package src;

/**
 * Encapsula una acción realizada para ser almacenada en la Pila del historial.
 */
public class Accion {
    private String tipo;      // Ej: "PRESTAMO", "RESERVA", "DEVOLUCION"
    private Usuario usuario;
    private Libro libro;
    private String timestamp;

    public Accion(String tipo, Usuario usuario, Libro libro) {
        this.tipo = tipo;
        this.usuario = usuario;
        this.libro = libro;
        this.timestamp = java.time.LocalDateTime.now().toString();
    }

    public String getTipo() { return tipo; }
    public Usuario getUsuario() { return usuario; }
    public Libro getLibro() { return libro; }

    @Override
    public String toString() {
        return "[" + timestamp + "] Acción: " + tipo + 
               " | Usuario: " + (usuario != null ? usuario.getNombre() : "N/A") + 
               " | Libro: " + (libro != null ? libro.getTitulo() : "N/A");
    }
}
