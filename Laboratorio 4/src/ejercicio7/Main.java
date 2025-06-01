package ejercicio7;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ListaCircular lista = new ListaCircular();
        Scanner sc = new Scanner(System.in);
        int opcion;
        for (int i = 1; i <= 12; i++) {
            lista.insert(i);
        }
        do {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Mostrar lista circular");
            System.out.println("2. Insertar al inicio");
            System.out.println("3. Insertar al final");
            System.out.println("4. Eliminar por valor");
            System.out.println("5. Eliminar por posición");
            System.out.println("6. Eliminar primero");
            System.out.println("7. Eliminar último");
            System.out.println("8. Ver tamaño");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    lista.printList();
                    break;
                case 2:
                    System.out.print("Valor: ");
                    lista.addFirst(sc.nextInt());
                    break;
                case 3:
                    System.out.print("Valor: ");
                    lista.addLast(sc.nextInt());
                    break;
                case 4:
                    System.out.print("Valor a eliminar: ");
                    lista.deleteByKey(sc.nextInt());
                    break;
                case 5:
                    System.out.print("Posición a eliminar: ");
                    lista.deleteAtPosition(sc.nextInt());
                    break;
                case 6:
                    lista.removeFirst();
                    break;
                case 7:
                    lista.removeLast();
                    break;
                case 8:
                    System.out.println("Tamaño: " + lista.size());
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } 
        while (opcion != 0);
        sc.close();
    }
}