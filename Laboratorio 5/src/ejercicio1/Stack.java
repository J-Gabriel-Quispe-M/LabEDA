package ejercicio1;

public class Stack<E> {
    private Nodo<E> cima;

    public Stack() {
        this.cima = null;
    }

    public void push(E dato) {
        Nodo<E> nuevo = new Nodo<>(dato);
        nuevo.siguiente = cima;
        cima = nuevo;
    }

    public E pop() {
        if (isEmpty()) {
            throw new RuntimeException("ESTA VACIA");
        }
        E dato = cima.dato;
        cima = cima.siguiente;
        return dato;
    }

    public E top() {
        if (isEmpty()) {
            throw new RuntimeException("ESTA VACIA");
        }
        return cima.dato;
    }

    public boolean isEmpty() {
        return cima == null;
    }

    public void printStack() {
        Nodo<E> actual = cima;
        System.out.print("Pila: ");
        while (actual != null) {
            System.out.print(actual.dato + " ");
            actual = actual.siguiente;
        }
        System.out.println();
    }
}

