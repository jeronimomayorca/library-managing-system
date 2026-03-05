package src;

/**
 * Gestión de usuarios utilizando un arreglo estático.
 */
public class ArregloUsuarios {
    private Usuario[] usuarios;
    private int contador;

    public ArregloUsuarios(int capacidad) {
        this.usuarios = new Usuario[capacidad];
        this.contador = 0;
    }

    /**
     * Registra un nuevo usuario en el sistema.
     * @param usuario El objeto usuario a registrar.
     * @return true si se registró, false si el arreglo está lleno o el ID ya existe.
     */
    public boolean registrar(Usuario usuario) {
        if (contador >= usuarios.length) {
            System.out.println("Error: Capacidad máxima de usuarios alcanzada.");
            return false;
        }

        if (buscar(usuario.getId()) != null) {
            System.out.println("Error: El usuario con ID " + usuario.getId() + " ya está registrado.");
            return false;
        }

        usuarios[contador] = usuario;
        contador++;
        return true;
    }

    /**
     * Busca un usuario por su ID.
     * @param id El ID a buscar.
     * @return El usuario encontrado o null.
     */
    public Usuario buscar(String id) {
        for (int i = 0; i < contador; i++) {
            if (usuarios[i].getId().equals(id)) {
                return usuarios[i];
            }
        }
        return null;
    }

    /**
     * Muestra todos los usuarios registrados.
     */
    public void mostrarUsuarios() {
        if (contador == 0) {
            System.out.println("No hay usuarios registrados en el sistema.");
            return;
        }
        System.out.println("--- Lista de Usuarios ---");
        for (int i = 0; i < contador; i++) {
            System.out.println((i + 1) + ". " + usuarios[i].toString());
        }
        System.out.println("-------------------------");
    }
}
