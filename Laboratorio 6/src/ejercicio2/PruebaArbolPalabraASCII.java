package ejercicio2;

import java.util.Scanner;

public class PruebaArbolPalabraASCII {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArbolABB<Integer> arbol = new ArbolABB<>();

        System.out.print("Ingrese una palabra: ");
        String palabra = scanner.nextLine();

        System.out.println("\nInsertando caracteres como valores ASCII en el árbol:");

        for (int i = 0; i < palabra.length(); i++) {
            char caracter = palabra.charAt(i);
            int valorAscii = (int) caracter;
            arbol.insertar(valorAscii);
            System.out.println("Insertado: '" + caracter + "' → " + valorAscii);
        }

        System.out.println("\nRecorrido en orden (InOrden):");
        arbol.recorridoInOrden();

        System.out.println("\nRecorrido en preorden (PreOrden):");
        arbol.recorridoPreOrden();

        System.out.println("\nRecorrido en postorden (PostOrden):");
        arbol.recorridoPostOrden();

        scanner.close();
    }
}