package src;

/**
 * Clase que representa un libro en el inventario.
 */
public class Libro {
    private String isbn;
    private String titulo;
    private String autor;
    private boolean disponible;

    /**
     * Constructor por defecto.
     */
    public Libro() {
    }

    /**
     * Constructor parametrizado.
     * @param isbn Identificador único del libro.
     * @param titulo Título del libro.
     * @param autor Autor del libro.
     */
    public Libro(String isbn, String titulo, String autor) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.disponible = true;
    }

    // Getters y Setters
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    @Override
    public String toString() {
        return "Libro [" + isbn + "] - " + titulo + " (" + autor + ") -> " + (disponible ? "Disponible" : "Prestado");
    }
}
