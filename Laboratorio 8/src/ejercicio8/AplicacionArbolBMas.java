import java.util.Scanner;

public class AplicacionArbolBMas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VisualizadorArbolBMas<Integer> arbol = new VisualizadorArbolBMas<>(5);

        while (true) {
            System.out.println("\n=== VISUALIZADOR DE ÁRBOL B+ ===");
            System.out.println("1. Insertar números");
            System.out.println("2. Buscar elemento");
            System.out.println("3. Mostrar árbol (texto)");
            System.out.println("4. Visualizar árbol (GraphStream)");
            System.out.println("5. Eliminar elemento");
            System.out.println("6. Limpiar árbol");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese números separados por espacio: ");
                    String[] datos = scanner.nextLine().split(" ");
                    for (String num : datos) {
                        try {
                            arbol.insertar(Integer.parseInt(num));
                        } catch (NumberFormatException e) {
                            System.out.println("Número inválido: " + num);
                        }
                    }
                    break;

                case 2:
                    System.out.print("Elemento a buscar: ");
                    int buscar = scanner.nextInt();
                    System.out.println("¿Está presente?: " + arbol.buscar(buscar));
                    break;

                case 3:
                    System.out.println(arbol.toString());
                    break;

                case 4:
                    arbol.mostrarArbol();
                    break;

                case 5:
                    System.out.print("Elemento a eliminar: ");
                    int eliminar = scanner.nextInt();
                    arbol.eliminar(eliminar);
                    break;

                case 6:
                    arbol.destruir();
                    System.out.println("Árbol limpiado.");
                    break;

                case 0:
                    System.out.println("¡Hasta luego!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
