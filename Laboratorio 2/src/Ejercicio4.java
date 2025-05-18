import java.util.Scanner;

public class Ejercicio4 {
    public static void main(String [] args) {
        Scanner scan = new Scanner(System.in);
        Ejercicio4 t = new Ejercicio4();
        System.out.print("Ingrese la base del triangulo: ");
        int b = scan.nextInt();
        t.trianguloRecursivo2(b);
        scan.close();
    }

    public void trianguloRecursivo2(int base) {
        imprimirTrianguloCentrado(base,1);
    }

    private void imprimirTrianguloCentrado(int base, int nivel) {
        if (nivel > base) {
            return;
        }
        int espacios = base - nivel;
        
        for (int i = 0; i< espacios; i++) {
            System.out.print(" ");
        }

        for (int i = 0; i< nivel; i++) {
            System.out.print("*");
        }
        System.out.println();
        imprimirTrianguloCentrado(base, nivel + 1);
    }
}