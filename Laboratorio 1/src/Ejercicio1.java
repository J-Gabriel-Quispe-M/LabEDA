import java.util.Arrays;
import java.util.HashMap;
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

        HashMap<Double, Integer> frecuencia = new HashMap<>();
        double moda = calificaciones[0];
        int maxFrecuencia = 1;
        
        for (double calif : calificaciones) {
            int count = frecuencia.getOrDefault(calif, 0) + 1;
            frecuencia.put(calif, count);
            
            if (count > maxFrecuencia) {
                maxFrecuencia = count;
                moda = calif;
            }
        }

        double suma = 0;
        for (double calif : calificaciones) {
            suma += calif;
        }
        double media = suma / n;
        
        double sumaDiferencias = 0;
        for (double calif : calificaciones) {
            sumaDiferencias += Math.pow(calif - media, 2);
        }
        double desviacionEstandar = Math.sqrt(sumaDiferencias / n);
        
        System.out.println("Mediana: " + mediana);
        System.out.println("Moda: " + moda);
        System.out.println("Desviación estándar: " + desviacionEstandar);

        scan.close();
    }
}