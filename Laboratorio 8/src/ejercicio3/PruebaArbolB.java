package ejercicio3;

import java.util.*;

public class PruebaArbolB {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArbolB<Integer> arbol = new ArbolB<>(4);
        int opcion;

        do {
            System.out.println("\n--- MENÚ ÁRBOL B ---");
            System.out.println("1. Insertar");
            System.out.println("2. Eliminar");
            System.out.println("3. Buscar");
            System.out.println("4. Mostrar árbol");
            System.out.println("5. Mínimo");
            System.out.println("6. Máximo");
            System.out.println("7. Predecesor");
            System.out.println("8. Sucesor");
            System.out.println("9. Verificar si está vacío");
            System.out.println("10. Destruir árbol");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Dato a insertar: ");
                    int x = sc.nextInt();
                    arbol.insertar(x);
                    break;
                case 2:
                    System.out.print("Dato a eliminar: ");
                    int del = sc.nextInt();
                    arbol.eliminar(del);
                    break;
                case 3:
                    System.out.print("Dato a buscar: ");
                    int b = sc.nextInt();
                    System.out.println(arbol.buscar(b) ? "Existe" : "No existe");
                    break;
                case 4:
                    System.out.println(arbol.toString());
                    break;
                case 5:
                    System.out.println("Mínimo: " + arbol.minimo());
                    break;
                case 6:
                    System.out.println("Máximo: " + arbol.maximo());
                    break;
                case 7:
                    System.out.print("Dato: ");
                    int p = sc.nextInt();
                    System.out.println("Predecesor: " + arbol.predecesor(p));
                    break;
                case 8:
                    System.out.print("Dato: ");
                    int s = sc.nextInt();
                    System.out.println("Sucesor: " + arbol.sucesor(s));
                    break;
                case 9:
                    System.out.println(arbol.estaVacio() ? "Árbol vacío" : "Árbol no vacío");
                    break;
                case 10:
                    arbol.destruir();
                    System.out.println("Árbol destruido");
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while (opcion != 0);

        sc.close();
    }
}
