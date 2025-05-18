import java.util.Scanner;

public class Ejercicio5 {
    public static void main(String [] args) {
        Scanner scan = new Scanner (System.in);
        Ejercicio5 t = new Ejercicio5();
        System.out.print("Ingrese la base del triangulo: ");
        int b = scan.nextInt();
        t.trianguloRecursivo3(b);
        scan.close();
    }

    public void trianguloRecursivo3(int base) {
        imprimirTrianguloAlineado(base,1);
    }

    private void imprimirTrianguloAlineado(int base, int nivel) {
        if (nivel > base) {
            return;
        }
        int espacios = base - nivel;

        for (int i = 0; i < espacios; i++) {
            System.out.print(" ");
        }

        for (int i = 0; i < nivel; i++) {
            System.out.print("* ");
        }
        System.out.println();
        imprimirTrianguloAlineado(base, nivel + 1);
    }

}
