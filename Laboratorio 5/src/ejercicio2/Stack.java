package ejercicio2;

public class Queue<E> {
    private Nodo<E> frente;
    private Nodo<E> fin;

    public Queue() {
        this.frente = null;
        this.fin = null;
    }

    public void enqueue(E dato) {
        Nodo<E> nuevo = new Nodo<>(dato);
        if (isEmpty()) {
            frente = fin = nuevo;
        } else {
            fin.siguiente = nuevo;
            fin = nuevo;
        }
    }

    public E dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("ESTA VACIA");
        }
        E dato = frente.dato;
        frente = frente.siguiente;
        if (frente == null) {
            fin = null; 
        }
        return dato;
    }

    public E front() {
        if (isEmpty()) {
            throw new RuntimeException("ESTA VACIA");
        }
        return frente.dato;
    }

    public E back() {
        if (isEmpty()) {
            throw new RuntimeException("ESTA VACIA");
        }
        return fin.dato;
    }

    public boolean isEmpty() {
        return frente == null;
    }

    public void printQueue() {
        Nodo<E> actual = frente;
        System.out.print("Cola: ");
        while (actual != null) {
            System.out.print(actual.dato + " ");
            actual = actual.siguiente;
        }
        System.out.println();
    }
}