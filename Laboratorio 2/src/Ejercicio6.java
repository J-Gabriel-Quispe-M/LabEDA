import java.util.Scanner;

public class Ejercicio6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Ejercicio6 c = new Ejercicio6();
        
        System.out.print("Ingrese tamaño del cuadrado: ");
        int b = sc.nextInt();
        
        System.out.println("\nSalida:");
        c.cuadradoRecursivo(b);
    }

    public void cuadradoRecursivo(int base) {
        imprimirCuadradoHueco(base, 1);
    }

    private void imprimirCuadradoHueco(int base, int nivel) {
        if (nivel > base) {
            return;
        }
        
        if (nivel == 1 || nivel == base) {
            System.out.println("*".repeat(base));
        } 
        else {
            System.out.print("*");
            System.out.print(" ".repeat(base - 2));
            System.out.println("*");
        }
        imprimirCuadradoHueco(base, nivel + 1);
    }
}