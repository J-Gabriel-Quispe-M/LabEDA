package ejercicio7;

import java.util.Scanner;

public class PruebaArbolBMas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArbolBMas<Integer> arbol = new ArbolBMas<>(5);

        int opcion;
        do {
            System.out.println("\n--- MENÚ ÁRBOL B+ ---");
            System.out.println("1. Insertar clave");
            System.out.println("2. Eliminar clave");
            System.out.println("3. Buscar clave");
            System.out.println("4. Mostrar árbol (en orden)");
            System.out.println("5. Obtener mínimo");
            System.out.println("6. Obtener máximo");
            System.out.println("7. Obtener predecesor");
            System.out.println("8. Obtener sucesor");
            System.out.println("9. Vaciar árbol");
            System.out.println("10. Verificar si está vacío");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            
            while (!sc.hasNextInt()) {
                System.out.print("Por favor, ingrese un número válido: ");
                sc.next();
            }
            opcion = sc.nextInt();

            switch (opcion) {
                case 1 -> {
                    System.out.print("Ingrese la clave a insertar: ");
                    int claveInsertar = sc.nextInt();
                    arbol.insertar(claveInsertar);
                    System.out.println("Clave insertada correctamente.");
                }
                case 2 -> {
                    System.out.print("Ingrese la clave a eliminar: ");
                    int claveEliminar = sc.nextInt();
                    arbol.eliminar(claveEliminar);
                }
                case 3 -> {
                    System.out.print("Ingrese la clave a buscar: ");
                    int claveBuscar = sc.nextInt();
                    System.out.println("¿Existe?: " + arbol.buscar(claveBuscar));
                }
                case 4 -> {
                    System.out.println("Árbol B+ en orden:");
                    System.out.println(arbol);
                }
                case 5 -> System.out.println("Mínimo: " + arbol.minimo());
                case 6 -> System.out.println("Máximo: " + arbol.maximo());
                case 7 -> {
                    System.out.print("Ingrese la clave para buscar su predecesor: ");
                    int clavePred = sc.nextInt();
                    System.out.println("Predecesor: " + arbol.predecesor(clavePred));
                }
                case 8 -> {
                    System.out.print("Ingrese la clave para buscar su sucesor: ");
                    int claveSucc = sc.nextInt();
                    System.out.println("Sucesor: " + arbol.sucesor(claveSucc));
                }
                case 9 -> {
                    arbol.destruir();
                    System.out.println("Árbol eliminado.");
                }
                case 10 -> System.out.println("¿Está vacío?: " + arbol.estaVacio());
                case 0 -> System.out.println("¡Hasta luego!");
                default -> System.out.println("Opción no válida.");
            }

        } while (opcion != 0);

        sc.close();
    }
}