import java.util.Scanner;

public class Ejercicio3 {
    public static void main(String [] args) {
        Scanner scan = new Scanner (System.in);
        Ejercicio3 t = new Ejercicio3();
        System.out.print("Ingrese la base del triangulo: ");
        int b = scan.nextInt();
        t.trianguloRecursivo1(b);
        scan.close();
    }

    public void trianguloRecursivo1(int base) {
        imprimirTriangulo(base,1);

    }

    private void imprimirTriangulo(int base, int nivel) {
        if (nivel > base) { 
            return;
        }

        for (int i = 0; i < nivel; i++) {
            System.out.print("*");
        }
        System.out.println();
        imprimirTriangulo(base, nivel + 1);
    }
}
