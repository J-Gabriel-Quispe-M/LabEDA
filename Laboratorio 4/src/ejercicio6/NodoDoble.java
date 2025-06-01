package ejercicio6;

public class NodoDoble {
    int data;
    NodoDoble next;
    NodoDoble prev;

    public NodoDoble(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}