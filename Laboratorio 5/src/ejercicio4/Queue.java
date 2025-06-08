package ejercicio4;

public class Queue<E> {
    private Nodo<E> frente;
    private Nodo<E> fin;
    private int size;
    private final int CAPACIDAD_MAXIMA = 100;
    
    public Queue() {
        this.frente = null;
        this.fin = null;
        this.size = 0;
    }

    public void enqueue(E dato) {
        if (isFull()) {
            throw new RuntimeException("ESTA LLENA");
        }
        Nodo<E> nuevo = new Nodo<>(dato);
        if (isEmpty()) {
            frente = fin = nuevo;
        } 
        else {
            fin.siguiente = nuevo;
            fin = nuevo;
        }
        size++;
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
        size--;
        return dato;
    }

    public void destroyQueue() {
        frente = null;
        fin = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size >= CAPACIDAD_MAXIMA;
    }

    public E front() {
        if (isEmpty()) throw new RuntimeException("ESTA VACIA");
        return frente.dato;
    }

    public E back() {
        if (isEmpty()) throw new RuntimeException("ESTA VACIA");
        return fin.dato;
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