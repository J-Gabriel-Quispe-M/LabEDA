import java.util.Scanner;
public class Ejercicio2{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Ingrese el límite superior del rango: ");
        int limite = scan.nextInt();
        boolean[] esPrimo = new boolean[limite + 1];

        for (int i = 2; i <= limite; i++) {
            esPrimo[i] = true;
        }

        for (int p = 2; p * p <= limite; p++) {
            if (esPrimo[p]) {
                for (int i = p * p; i <= limite; i += p) {
                    esPrimo[i] = false;
                }
            }
        }

        System.out.println("Números primos en el rango:");

        for (int i = 2; i <= limite; i++) {
            if (esPrimo[i]) {
                System.out.print(i + " ");
            }
        }
        
        scan.close();
    }
}