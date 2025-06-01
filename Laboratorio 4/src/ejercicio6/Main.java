package ejercicio6;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ListaDoble lista = new ListaDoble();
        Scanner sc = new Scanner(System.in);
        int opcion;
        for (int i = 1; i <= 10; i++) {
            lista.insert(i);
        }
        do {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Mostrar lista");
            System.out.println("2. Mostrar lista en reversa");
            System.out.println("3. Insertar al inicio");
            System.out.println("4. Insertar al final");
            System.out.println("5. Eliminar por valor");
            System.out.println("6. Eliminar por posición");
            System.out.println("7. Eliminar primero");
            System.out.println("8. Eliminar último");
            System.out.println("9. Tamaño de la lista");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    lista.printList();
                    break;
                case 2:
                    lista.printReverse();
                    break;
                case 3:
                    System.out.print("Valor: ");
                    lista.addFirst(sc.nextInt());
                    break;
                case 4:
                    System.out.print("Valor: ");
                    lista.addLast(sc.nextInt());
                    break;
                case 5:
                    System.out.print("Valor a eliminar: ");
                    lista.deleteByKey(sc.nextInt());
                    break;
                case 6:
                    System.out.print("Posición a eliminar: ");
                    lista.deleteAtPosition(sc.nextInt());
                    break;
                case 7:
                    lista.removeFirst();
                    break;
                case 8:
                    lista.removeLast();
                    break;
                case 9:
                    System.out.println("Tamaño: " + lista.size());
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
        sc.close();
    }
}