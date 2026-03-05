package src;

/**
 * Pila (LIFO) construida a partir de Nodos para almacenar el historial 
 * de transacciones (Acciones) y permitir "deshacer" (undo).
 */
public class PilaHistorial {
    private Nodo<Accion> tope;

    public PilaHistorial() {
        this.tope = null;
    }

    /**
     * Añade una acción al tope de la pila (Push).
     * @param accion La acción realizada.
     */
    public void push(Accion accion) {
        Nodo<Accion> nuevoNodo = new Nodo<>(accion);
        nuevoNodo.setSiguiente(tope);
        tope = nuevoNodo;
    }

    /**
     * Retira la última acción realizada (Pop).
     * @return La acción retirada o null si no hay historial.
     */
    public Accion pop() {
        if (estaVacia()) {
            System.out.println("El historial de acciones está vacío.");
            return null;
        }
        Accion accion = tope.getDato();
        tope = tope.getSiguiente();
        System.out.println("Deshaciendo acción: " + accion.getTipo() + "...");
        return accion;
    }

    /**
     * Muestra la última acción sin retirarla de la pila (Peek).
     * @return La última acción.
     */
    public Accion peek() {
        if (estaVacia()) {
            return null;
        }
        return tope.getDato();
    }

    public boolean estaVacia() {
        return (tope == null); // La pila está vacía si el tope es nulo.
    }

    /**
     * Muestra todo el historial de la sesión actual de manera secuencial (desde la última hasta la primera).
     */
    public void mostrarHistorial() {
        if (estaVacia()) {
            System.out.println("No hay registro de transacciones.");
            return;
        }
        System.out.println("--- Historial de Transacciones (Más recientes primero) ---");
        Nodo<Accion> actual = tope;
        int i = 1;
        while (actual != null) {
            System.out.println(i + ". " + actual.getDato().toString());
            actual = actual.getSiguiente();
            i++;
        }
        System.out.println("----------------------------------------------------------");
    }
}
