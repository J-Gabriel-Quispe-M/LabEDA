package ejercicio2;

public class Principal {
    public static void main(String[] args) {
        Stack<Integer> cola = new Stack<>();
        for (int i = 1; i <= 8; i++) {
            cola.enqueue(i);
        }
        cola.printStack();
        System.out.println("Elemento al frente: " + cola.front());
        System.out.println("Elemento al final: " + cola.back());
        System.out.println("Elemento desencolado: " + cola.dequeue());
        cola.printStack();
    }
}