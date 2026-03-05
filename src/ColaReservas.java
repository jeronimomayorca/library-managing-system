package src;

/**
 * Cola (FIFO) construida a partir de Nodos para gestionar una lista de espera de reservas.
 */
public class ColaReservas {

    /**
     * Representa una solicitud de reserva de un libro por parte de un usuario.
     */
    public static class Reserva {
        private Usuario usuario;
        private Libro libro;
        
        public Reserva(Usuario usuario, Libro libro) {
            this.usuario = usuario;
            this.libro = libro;
        }

        public Usuario getUsuario() { return usuario; }
        public Libro getLibro() { return libro; }

        @Override
        public String toString() {
            return "Reserva: " + usuario.getNombre() + " -> " + libro.getTitulo();
        }
    }

    private Nodo<Reserva> frente;
    private Nodo<Reserva> finalCola;

    public ColaReservas() {
        this.frente = null;
        this.finalCola = null;
    }

    /**
     * Añade una reserva al final de la cola (Enqueue).
     * @param reserva La reserva a colocar en espera.
     */
    public void encolar(Reserva reserva) {
        Nodo<Reserva> nuevoNodo = new Nodo<>(reserva);
        if (estaVacia()) {
            frente = nuevoNodo;
            finalCola = nuevoNodo;
        } else {
            finalCola.setSiguiente(nuevoNodo);
            finalCola = nuevoNodo;
        }
        System.out.println("El usuario " + reserva.getUsuario().getNombre() + 
                           " ha sido agregado a la lista de espera para el libro " + 
                           reserva.getLibro().getTitulo() + ".");
    }

    /**
     * Retira la reserva al frente de la cola (Dequeue).
     * @return La reserva retirada o null si está vacía.
     */
    public Reserva desencolar() {
        if (estaVacia()) {
            return null;
        }
        Reserva reservaDesencolada = frente.getDato();
        frente = frente.getSiguiente();
        if (frente == null) {
            // Si el frente se vacía, significa que la cola está vacía
            finalCola = null;
        }
        return reservaDesencolada;
    }

    /**
     * Muestra la reserva que está actualmente al frente (Peek).
     * @return La reserva o null.
     */
    public Reserva peek() {
        if (estaVacia()) {
            return null;
        }
        return frente.getDato();
    }

    /**
     * Verifica si hay algún usuario esperando un libro en particular.
     * @param isbn El ISBN del libro.
     * @return Verdadero si hay alguien en fila para ese libro.
     */
    public boolean hayReservaPara(String isbn) {
        Nodo<Reserva> actual = frente;
        while(actual != null) {
            if (actual.getDato().getLibro().getIsbn().equals(isbn)) {
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    /**
     * Devuelve y elimina de la cola a la primera persona esperando un libro específico.
     * Útil cuando se devuelve el libro y pasa automáticamente al siguiente.
     * @param isbn El ISBN del libro a buscar en la reserva.
     * @return La Reserva encontrada o null.
     */
    public Reserva desencolarParaLibro(String isbn) {
        if (estaVacia()) return null;

        // Si es el primero en la cola
        if (frente.getDato().getLibro().getIsbn().equals(isbn)) {
            return desencolar();
        }

        Nodo<Reserva> actual = frente;
        while (actual.getSiguiente() != null) {
            if (actual.getSiguiente().getDato().getLibro().getIsbn().equals(isbn)) {
                Reserva reservada = actual.getSiguiente().getDato();
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                // Ajustar finalCola si se extrajo el último elemento
                if (actual.getSiguiente() == null) {
                    finalCola = actual;
                }
                return reservada;
            }
            actual = actual.getSiguiente();
        }
        return null; // Nadie en la cola para este libro
    }

    public boolean estaVacia() {
        return (frente == null);
    }
    
    public void mostrarCola() {
        if(estaVacia()) {
            System.out.println("No hay reservas actuales pendientes en la cola.");
            return;
        }
        System.out.println("--- Cola de Reservas (Esperando) ---");
        Nodo<Reserva> actual = frente;
        int i = 1;
        while(actual != null) {
            System.out.println(i + ". " + actual.getDato().toString());
            actual = actual.getSiguiente();
            i++;
        }
        System.out.println("------------------------------------");
    }
}
