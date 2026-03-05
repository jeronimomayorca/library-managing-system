package src;

/**
 * Clase genérica que representa un nodo para las estructuras de datos enlazadas.
 * @param <T> Tipo de dato que almacenará el nodo.
 */
public class Nodo<T> {
    private T dato;
    private Nodo<T> siguiente;

    /**
     * Constructor del Nodo.
     * @param dato El dato a almacenar.
     */
    public Nodo(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    // Getters y Setters
    public T getDato() { return dato; }
    public void setDato(T dato) { this.dato = dato; }

    public Nodo<T> getSiguiente() { return siguiente; }
    public void setSiguiente(Nodo<T> siguiente) { this.siguiente = siguiente; }
}
