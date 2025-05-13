import java.util.Scanner;
public class Ejercicio3 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Ingrese el número de elementos: ");
        int n = scan.nextInt();
        int[] arreglo = new int[n];
        System.out.println("Ingrese los elementos:");
        for (int i = 0; i < n; i++) {
            arreglo[i] = scan.nextInt();
        }
        for (int i = 1; i < n; i++) {
            int valorActual = arreglo[i];
            int j = i - 1;  
            while (j >= 0 && arreglo[j] > valorActual) {
                arreglo[j + 1] = arreglo[j];
                j--;
            }
            arreglo[j + 1] = valorActual;
            System.out.println("Paso " + i + ":");
            for (int k = 0; k <= i; k++) {
                System.out.print(arreglo[k] + " ");
            }
            System.out.println("(segmento ordenado)");
        }
        
        System.out.println("Arreglo ordenado:");
        for (int num : arreglo) {
            System.out.print(num + " ");
        }
        
        scan.close();
    }
}