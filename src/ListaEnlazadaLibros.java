package src;

/**
 * Implementación manual de una Lista Enlazada Simple para la gestión de libros.
 * No utiliza java.util.LinkedList.
 */
public class ListaEnlazadaLibros {
    private Nodo<Libro> cabeza;

    public ListaEnlazadaLibros() {
        this.cabeza = null;
    }

    /**
     * Inserta un libro al final de la lista.
     * @param libro El libro a insertar.
     */
    public void insertar(Libro libro) {
        Nodo<Libro> nuevoNodo = new Nodo<>(libro);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo<Libro> actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevoNodo);
        }
    }

    /**
     * Busca un libro por su ISBN.
     * @param isbn El ISBN a buscar.
     * @return El libro encontrado, o null si no existe.
     */
    public Libro buscar(String isbn) {
        Nodo<Libro> actual = cabeza;
        while (actual != null) {
            if (actual.getDato().getIsbn().equals(isbn)) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }
        return null; // No encontrado
    }

    /**
     * Elimina un libro de la lista por su ISBN.
     * @param isbn El ISBN del libro a eliminar.
     * @return true si se eliminó, false si no se encontró.
     */
    public boolean eliminar(String isbn) {
        if (cabeza == null) return false;

        // Si es el primero
        if (cabeza.getDato().getIsbn().equals(isbn)) {
            cabeza = cabeza.getSiguiente();
            return true;
        }

        Nodo<Libro> actual = cabeza;
        while (actual.getSiguiente() != null) {
            if (actual.getSiguiente().getDato().getIsbn().equals(isbn)) {
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    /**
     * Muestra en consola todos los libros en el inventario.
     */
    public void mostrarInventario() {
        if (cabeza == null) {
            System.out.println("El inventario de libros está vacío.");
            return;
        }
        Nodo<Libro> actual = cabeza;
        System.out.println("--- Inventario de Libros ---");
        while (actual != null) {
            System.out.println(actual.getDato().toString());
            actual = actual.getSiguiente();
        }
        System.out.println("----------------------------");
    }
}
