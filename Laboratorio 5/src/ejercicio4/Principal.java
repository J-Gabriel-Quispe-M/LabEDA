package ejercicio4;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Queue<Integer> cola = new Queue<>();
        Scanner scanner = new Scanner(System.in);
        int opcion;
        for (int i = 1; i <= 10; i++) {
            cola.enqueue(i);
        }
        do {
            System.out.println("\n--- MENU COLA ---");
            System.out.println("1. Mostrar Cola");
            System.out.println("2. Encolar un elemento");
            System.out.println("3. Desencolar un elemento");
            System.out.println("4. Ver frente");
            System.out.println("5. Ver final");
            System.out.println("6. ¿Esta vacia?");
            System.out.println("7. ¿Esta llena?");
            System.out.println("8. Destruir cola");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    cola.printQueue();
                    break;
                case 2:
                    System.out.print("Ingrese un numero a encolar: ");
                    int num = scanner.nextInt();
                    try {
                        cola.enqueue(num);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        System.out.println("Elemento desencolado: " + cola.dequeue());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        System.out.println("Elemento al frente: " + cola.front());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    try {
                        System.out.println("Elemento al final: " + cola.back());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
                    System.out.println("¿Esta vacia? " + cola.isEmpty());
                    break;
                case 7:
                    System.out.println("¿Esta llena? " + cola.isFull());
                    break;
                case 8:
                    cola.destroyQueue();
                    System.out.println("Cola destruida.");
                    break;
                case 0:
                    System.out.println("Saliendo del programa");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        } 
        while (opcion != 0);
        scanner.close();
    }
}