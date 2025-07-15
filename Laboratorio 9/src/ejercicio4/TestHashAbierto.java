package ejercicio4;

import java.util.Scanner;

public class TestHashAbierto {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        HashAbierto<String> hash = new HashAbierto<>(8);
        int opcion;
        do {
            System.out.println("\n===== MENÚ TABLA HASH ABIERTA =====");
            System.out.println("1. Insertar registro");
            System.out.println("2. Buscar registro");
            System.out.println("3. Eliminar registro");
            System.out.println("4. Mostrar tabla");
            System.out.println("5. Mostrar tamaño actual");
            System.out.println("6. Verificar si la tabla está vacía");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = entrada.nextInt();
            switch (opcion) {
                case 1:
                    System.out.print("Ingrese la clave (entero): ");
                    int clave = entrada.nextInt();
                    entrada.nextLine();  // limpiar buffer
                    System.out.print("Ingrese el valor (texto): ");
                    String valor = entrada.nextLine();
                    hash.insertar(new Registro<>(clave, valor));
                    break;
                case 2:
                    System.out.print("Ingrese la clave a buscar: ");
                    int claveBuscar = entrada.nextInt();
                    Registro<String> encontrado = hash.buscar(claveBuscar);
                    if (encontrado != null) {
                        System.out.println("Registro encontrado: " + encontrado);
                    } 
                    else {
                        System.out.println("Clave no encontrada.");
                    }
                    break;
                case 3:
                    System.out.print("Ingrese la clave a eliminar: ");
                    int claveEliminar = entrada.nextInt();
                    hash.eliminar(claveEliminar);
                    break;
                case 4:
                    hash.mostrarTabla();
                    break;
                case 5:
                    System.out.println("Tamaño actual: " + hash.obtenerTamanio());
                    break;
                case 6:
                    System.out.println("¿La tabla está vacía? " + (hash.estaVacia() ? "Sí" : "No"));
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
                    break;
            }
        } while (opcion != 0);
        entrada.close();
    }
}
