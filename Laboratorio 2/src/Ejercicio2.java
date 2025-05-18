import java.util.Scanner;

public class Ejercicio2{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Ejercicio2 ri = new Ejercicio2();
        
        System.out.print("Ingrese tamaño del vector: ");
        int n = scan.nextInt();
        int[] vector = new int[n];
        
        System.out.println("Ingrese valores:");
        for (int i = 0; i < n; i++) {
            vector[i] = scan.nextInt();
        }
        
        int[] rotado = ri.rotarizquierdaArray(vector);
        
        System.out.print("Vector rotado: ");
        for (int num : rotado) {
            System.out.print(num + " ");
        }

        scan.close();
    }

    public int[] rotarizquierdaArray(int[] A) {
        return rotarizquierdaRecursivo(A, 2); 
    }

    private int[] rotarizquierdaRecursivo(int[] A, int d) {
        if (d == 0) return A;
        int temp = A[0];
        for (int i = 0; i < A.length - 1; i++) {
            A[i] = A[i + 1];
        }
        A[A.length - 1] = temp;
        return rotarizquierdaRecursivo(A, d - 1);
    }    
}