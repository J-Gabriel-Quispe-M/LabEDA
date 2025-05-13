import java.util.Arrays;
import java.util.Scanner;

public class Ejercicio1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        System.out.print("Ingrese el número de estudiantes: ");
        int n = scan.nextInt();
        double[] calificaciones = new double[n];
        
        System.out.println("Ingrese las calificaciones:");
        for (int i = 0; i < n; i++) {
            calificaciones[i] = scan.nextDouble();
        }
        
        Arrays.sort(calificaciones);
        double mediana;
        if (n % 2 == 0) {
            mediana = (calificaciones[n/2] + calificaciones[n/2 - 1]) / 2.0;
        } 
        else {
            mediana = calificaciones[n/2];
        }
        System.out.println("Mediana: " + mediana);

        scan.close();
    }
}