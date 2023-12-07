import java.util.LinkedList;
//función para generar tabla
public class TablaHash {
    private static final int TAMANO = 2000; //tamaño de la tabla
    private LinkedList<Usuario>[] tabla; //Arreglo para manejar colisiones
    public TablaHash() {
        tabla = new LinkedList[TAMANO];
        for (int i = 0; i < TAMANO; i++) {
            tabla[i] = new LinkedList<>();
        }
    }
    //Función hash
    private int funcionHash(String nombreUsuario) {
        int hash = 7;
        for (int i = 0; i < nombreUsuario.length(); i++){
            hash = hash * 31 + nombreUsuario.charAt(i);
        }
        return Math.abs(hash) % TAMANO;
    }

    //Agregar usuario
    public void agregarUsuario(String nombreUsuario, String pass) {
        Usuario nuevoUsuario = new Usuario(nombreUsuario, pass);
        int indice = funcionHash(nombreUsuario);
        tabla[indice].add(nuevoUsuario);
    }

    //Obtener usuario de la tabla
    public Usuario obtenerUsuario(String nombreUsuario) {
        int indice = funcionHash(nombreUsuario);
        for (Usuario usuario : tabla[indice]) {
            if (usuario.getNombre().equals(nombreUsuario)) {
                return usuario;
            }
        }
        return null; // Usuario no encontrado
    }
    // Función para mostrar la tabla hash
    public void verTabla() {
        System.out.println("Contenido de la tabla hash:");
        for (int i = 0; i < TAMANO; i++) {
            System.out.print("Índice " + i + ": ");
            for (Usuario usuario : tabla[i]) {
                System.out.print("Usuario: " + usuario.getNombre() + ", Contraseña: " + usuario.getPass() + " | ");
            }
            System.out.println();
        }
    }
    //Eliminar usuario
    public void eliminarUsuario(String nombreUsuario) {
        int indice = funcionHash(nombreUsuario);
        tabla[indice].removeIf(usuario -> usuario.getNombre().equals(nombreUsuario));
    }

}
