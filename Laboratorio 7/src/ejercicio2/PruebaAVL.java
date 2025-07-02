package ejercico2;

import java.util.Scanner;

public class PruebaAVL {
    public static void main(String[] args) {
        ArbolAVL<Integer> arbol = new ArbolAVL<>();
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n=== MENÚ ÁRBOL AVL ===");
            System.out.println("1. Insertar");
            System.out.println("2. Eliminar");
            System.out.println("3. Buscar");
            System.out.println("4. Mínimo");
            System.out.println("5. Máximo");
            System.out.println("6. Predecesor");
            System.out.println("7. Sucesor");
            System.out.println("8. Recorrido InOrden");
            System.out.println("9. Recorrido PreOrden");
            System.out.println("10. Recorrido PostOrden");
            System.out.println("11. Destruir árbol");
            System.out.println("12. ¿Árbol vacío?");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    System.out.print("Valor a insertar: ");
                    int insertar = sc.nextInt();
                    arbol.insertar(insertar);
                    break;
                case 2:
                    System.out.print("Valor a eliminar: ");
                    int eliminar = sc.nextInt();
                    arbol.eliminar(eliminar);
                    break;
                case 3:
                    System.out.print("Valor a buscar: ");
                    int buscar = sc.nextInt();
                    System.out.println(arbol.buscar(buscar) ? "Existe" : "No existe");
                    break;
                case 4:
                    System.out.println("Mínimo: " + arbol.minimo());
                    break;
                case 5:
                    System.out.println("Máximo: " + arbol.maximo());
                    break;
                case 6:
                    System.out.print("Predecesor de: ");
                    int pred = sc.nextInt();
                    System.out.println("Predecesor: " + arbol.predecesor(pred));
                    break;
                case 7:
                    System.out.print("Sucesor de: ");
                    int suc = sc.nextInt();
                    System.out.println("Sucesor: " + arbol.sucesor(suc));
                    break;
                case 8:
                    System.out.print("InOrden: ");
                    arbol.recorridoInOrden();
                    break;
                case 9:
                    System.out.print("PreOrden: ");
                    arbol.recorridoPreOrden();
                    break;
                case 10:
                    System.out.print("PostOrden: ");
                    arbol.recorridoPostOrden();
                    break;
                case 11:
                    arbol.destruir();
                    System.out.println("Árbol destruido.");
                    break;
                case 12:
                    System.out.println(arbol.estaVacio() ? 
                    "Árbol vacío" : "Árbol NO vacío");
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
