package ejercicio1;

public class Principal {
    public static void main(String[] args) {
        Stack<Integer> pila = new Stack<>();
        for (int i = 1; i <= 10; i++) {
            pila.push(i);
        }
        pila.printStack();
        System.out.println("Elemento en el tope: " + pila.top());
        System.out.println("Elemento desapilado: " + pila.pop());
        pila.printStack();
    }
}