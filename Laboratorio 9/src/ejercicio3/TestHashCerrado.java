package ejercicio3;

import java.util.Scanner;

public class TestHashCerrado {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        HashCerrado<String> tablaHash = new HashCerrado<>(8); 
        int opcion;
        do {
            System.out.println("\n===== MENÚ TABLA HASH CERRADA =====");
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
                    System.out.print("Ingrese clave (entero): ");
                    int clave = entrada.nextInt();
                    entrada.nextLine();
                    System.out.print("Ingrese valor (texto): ");
                    String valor = entrada.nextLine();
                    tablaHash.insertar(new Registro<>(clave, valor));
                    break;
                case 2:
                    System.out.print("Ingrese clave a buscar: ");
                    int claveBusqueda = entrada.nextInt();
                    Registro<String> encontrado = tablaHash.buscar(claveBusqueda);
                    if (encontrado != null) {
                        System.out.println("Registro encontrado: " + encontrado);
                    } 
                    else {
                        System.out.println("Clave no encontrada.");
                    }
                    break;
                case 3:
                    System.out.print("Ingrese clave a eliminar: ");
                    int claveEliminar = entrada.nextInt();
                    tablaHash.eliminar(claveEliminar);
                    break;
                case 4:
                    tablaHash.mostrar();
                    break;
                case 5:
                    System.out.println("Tamaño actual de la tabla: " + tablaHash.obtenerTamanio());
                    break;
                case 6:
                    System.out.println("¿La tabla está vacía? " + (tablaHash.estaVacia() ? "Sí" : "No"));
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente otra.");
                    break;
            }

        } while (opcion != 0);

        entrada.close();
    }
}