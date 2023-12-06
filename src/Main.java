import java.util.LinkedList;
import java.util.Scanner;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Main {
    private static final Scanner scanner = new Scanner(System.in); //Declarar scanner para entrada de datos
    private static final TablaHash tabla = new TablaHash(); // Crear una instancia de TablaHash

    public static void main(String[] args) {
        login();
        menu();
    }

    public static void login() {
        boolean iniciar = false;
        String usuario = "Administrador";
        String pass = "Tarea3";
        while (!iniciar) {
            System.out.println("Usuario:");
            //scanner.nextLine();
            String userTrabajador = scanner.nextLine();
            System.out.println("Contraseña:");
            String passTrabajador = scanner.nextLine();
            if (!userTrabajador.equals(usuario) || !passTrabajador.equals(pass)) {
                iniciar = false;
                System.out.println("Las credenciales no son correctas, intente de nuevo");
            } else {
                iniciar = true;
                System.out.println("Acceso permitido!!!");
            }
        }
    }
    //clase para el menu
    public static void menu() {
        int option;
        boolean salir = false;
        while (!salir) {
            System.out.println("\nSelecciona una opción");
            System.out.println("1. Crear un usuarios");
            System.out.println("2. Ver tabla hash");
            System.out.println("3. Modificar usuario");
            System.out.println("4. Buscar usuario");
            System.out.println("5. Eliminar usuario");
            System.out.println("6. Salir");
            option = scanner.nextInt();
            //bucle para cada uno de los casos
            switch (option) {
                case 1:
                    crearUsuario();
                    break;
                case 2:
                    tabla.verTabla();
                    break;
                case 3:
                    //modificarUsuario();
                    break;
                case 4:
                    System.out.println("Caso cuatro");
                    break;
                case 5:
                    System.out.println("Caso cinco");
                    break;
                case 6:
                    salir = true;
                    System.out.println("Hasta luego!!!!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción valida.");
            }
        }
    }
//Crear usuario
    public static void crearUsuario(){
        System.out.println("Ingrese el nombre de usuario:");
        scanner.nextLine(); //consumir la entrada de datos en una nueva linea
        String nombreUsuario = scanner.nextLine();
        System.out.println("Ingrese la contraseña:");
        String pass = scanner.nextLine();
        String hashPass = obtenerHash(pass); //cifrar la contraseña

        tabla.agregarUsuario(nombreUsuario, hashPass);
    }
//función para cifrar la contraseña
    public static String obtenerHash(String pass){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(pass.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}