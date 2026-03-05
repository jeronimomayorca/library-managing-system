package src;

import java.util.Scanner;

/**
 * Aplicación principal del Sistema de Gestión de Bibliotecas en Consola (CLI).
 */
public class BibliotecaCLI {
    private ListaEnlazadaLibros inventario;
    private ArregloUsuarios usuarios;
    private PilaHistorial historial;
    private ColaReservas reservas;
    private Scanner scanner;

    public BibliotecaCLI() {
        inventario = new ListaEnlazadaLibros();
        // Capacidad inicial predeterminada a 100 usuarios (Arreglo estático)
        usuarios = new ArregloUsuarios(100);
        historial = new PilaHistorial();
        reservas = new ColaReservas();
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        BibliotecaCLI app = new BibliotecaCLI();
        app.iniciarSesion();
    }

    public void iniciarSesion() {
        int opcion = -1;
        System.out.println("===========================================");
        System.out.println("  SISTEMA DE GESTIÓN DE BIBLIOTECA (CLI)   ");
        System.out.println("===========================================");

        while (opcion != 0) {
            mostrarMenu();
            try {
                System.out.print("Seleccione una opción: ");
                opcion = Integer.parseInt(scanner.nextLine());
                ejecutarOpcion(opcion);
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor, ingrese un número válido.");
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }
        }
        System.out.println("Programa terminado. Gracias por usar el sistema.");
        scanner.close();
    }

    private void mostrarMenu() {
        System.out.println("\n--- Menú Principal ---");
        System.out.println("1. Registrar Usuario");
        System.out.println("2. Agregar nuevo Libro");
        System.out.println("3. Prestar Libro");
        System.out.println("4. Devolver Libro");
        System.out.println("5. Ver Inventario y Usuarios");
        System.out.println("6. Ver Historial de Transacciones");
        System.out.println("7. Ver Cola de Reservas");
        System.out.println("8. Deshacer última transacción (Opcional)");
        System.out.println("0. Salir");
    }

    private void ejecutarOpcion(int opcion) {
        System.out.println();
        switch (opcion) {
            case 1:
                cmdRegistrarUsuario();
                break;
            case 2:
                cmdAgregarLibro();
                break;
            case 3:
                cmdPrestarLibro();
                break;
            case 4:
                cmdDevolverLibro();
                break;
            case 5:
                inventario.mostrarInventario();
                usuarios.mostrarUsuarios();
                break;
            case 6:
                historial.mostrarHistorial();
                break;
            case 7:
                reservas.mostrarCola();
                break;
            case 8:
                cmdDeshacer();
                break;
            case 0:
                // Salir, manejado en el ciclo principal
                break;
            default:
                System.out.println("Opción no válida. Intente de nuevo.");
        }
    }

    private void cmdRegistrarUsuario() {
        System.out.print("Ingrese ID del usuario: ");
        String id = scanner.nextLine();
        System.out.print("Ingrese Nombre del usuario: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese Carrera del usuario: ");
        String carrera = scanner.nextLine();

        Usuario nuevo = new Usuario(id, nombre, carrera);
        if (usuarios.registrar(nuevo)) {
            System.out.println("Usuario " + nombre + " registrado exitosamente.");
            historial.push(new Accion("REGISTRO_USUARIO", nuevo, null));
        }
    }

    private void cmdAgregarLibro() {
        System.out.print("Ingrese ISBN del libro: ");
        String isbn = scanner.nextLine();
        
        // Evitar duplicados de base si es necesario, asumimos único
        if (inventario.buscar(isbn) != null) {
            System.out.println("Error: Ya existe un libro con ese ISBN.");
            return;
        }

        System.out.print("Ingrese Título del libro: ");
        String titulo = scanner.nextLine();
        System.out.print("Ingrese Autor del libro: ");
        String autor = scanner.nextLine();

        Libro nuevoLibro = new Libro(isbn, titulo, autor);
        inventario.insertar(nuevoLibro);
        System.out.println("Libro '" + titulo + "' añadido al inventario.");
        historial.push(new Accion("AGREGAR_LIBRO", null, nuevoLibro));
    }

    private void cmdPrestarLibro() {
        System.out.print("Ingrese el ID del usuario: ");
        String idUsuario = scanner.nextLine();
        Usuario user = usuarios.buscar(idUsuario);
        if (user == null) {
            System.out.println("Error: Usuario no encontrado.");
            return;
        }

        System.out.print("Ingrese el ISBN del libro: ");
        String isbn = scanner.nextLine();
        Libro libro = inventario.buscar(isbn);
        if (libro == null) {
            System.out.println("Error: Libro no encontrado en el inventario.");
            return;
        }

        if (libro.isDisponible()) {
            libro.setDisponible(false);
            System.out.println("Préstamo exitoso. El libro '" + libro.getTitulo() + "' ha sido prestado a " + user.getNombre() + ".");
            historial.push(new Accion("PRESTAMO", user, libro));
        } else {
            System.out.println("El libro no está disponible. Se pondrá al usuario '" + user.getNombre() + "' en la cola de reservas.");
            reservas.encolar(new ColaReservas.Reserva(user, libro));
            historial.push(new Accion("RESERVA", user, libro));
        }
    }

    private void cmdDevolverLibro() {
        System.out.print("Ingrese el ID del usuario devolviendo el libro: ");
        String idUsuario = scanner.nextLine();
        Usuario user = usuarios.buscar(idUsuario);
        if (user == null) {
            System.out.println("Error: Usuario no encontrado.");
            return;
        }

        System.out.print("Ingrese el ISBN del libro devuelto: ");
        String isbn = scanner.nextLine();
        Libro libro = inventario.buscar(isbn);
        if (libro == null) {
            System.out.println("Error: Libro no encontrado en el inventario.");
            return;
        }

        if (libro.isDisponible()) {
            System.out.println("Este libro ya consta como disponible. No se puede devolver un libro que no se ha prestado (o no fue registrado).");
            return;
        }

        libro.setDisponible(true);
        System.out.println("Devolución exitosa. El libro '" + libro.getTitulo() + "' vuelve a estar disponible.");
        historial.push(new Accion("DEVOLUCION", user, libro));

        // Comprobamos si alguien estaba en la cola de espera para este libro
        if (reservas.hayReservaPara(isbn)) {
            ColaReservas.Reserva sigReserva = reservas.desencolarParaLibro(isbn);
            if (sigReserva != null) {
                System.out.println("El libro '" + libro.getTitulo() + "' estaba reservado por " + sigReserva.getUsuario().getNombre() + ".");
                System.out.println("-> Cediendo el préstamo automáticamente de la Cola de Espera.");
                libro.setDisponible(false);
                historial.push(new Accion("PRESTAMO_DESDE_RESERVA", sigReserva.getUsuario(), libro));
            }
        }
    }
    
    private void cmdDeshacer() {
        Accion ultima = historial.pop();
        if (ultima == null) return;
        
        switch (ultima.getTipo()) {
            case "AGREGAR_LIBRO":
                inventario.eliminar(ultima.getLibro().getIsbn());
                System.out.println("Se eliminó el libro " + ultima.getLibro().getTitulo() + " del inventario.");
                break;
            case "REGISTRO_USUARIO":
                System.out.println("(Nota: El Arreglo estático requeriría compactación manual para poder deshacer el alta de usuario permanentemente de manera segura en un prototipo).");
                break;
            case "PRESTAMO":
                ultima.getLibro().setDisponible(true);
                System.out.println("El libro " + ultima.getLibro().getTitulo() + " fue devuelto a la disponibilidad. (Deshecho el préstamo)");
                break;
            case "DEVOLUCION":
            case "PRESTAMO_DESDE_RESERVA":
                ultima.getLibro().setDisponible(false);
                System.out.println("El libro " + ultima.getLibro().getTitulo() + " vuelve a ser prestado. (Deshecha devolución)");
                break;
            case "RESERVA":
                System.out.println("(Nota: Deshacer Reserva implicaría remover elementos aleatorios de la ColaFIFO, lo que en una biblioteca requeriría cancelar una solicitud de usuario en backend).");
                break;
            default:
                System.out.println("Tipo de acción no reconocible para deshacer.");
        }
    }
}
