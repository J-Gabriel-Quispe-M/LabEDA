package ejercicio3;

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
        } 
        else {
            fin.siguiente = nuevo;
            fin = nuevo;
        }
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

    public boolean isEmpty() {
        return frente == null;
    }
}