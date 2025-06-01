package ejercicio2;

public class ListaEnlazadaCircular {
    private Nodo head = null;
    private Nodo tail = null;

    public void insertar(int data) {
        Nodo nuevo = new Nodo(data);
        if (head == null) {
            head = nuevo;
            tail = nuevo;
            nuevo.next = head;
        } 
        else {
            tail.next = nuevo;
            tail = nuevo;
            tail.next = head;
        }
    }

    public void imprimir() {
        if (head == null) return;
        Nodo actual = head;
        System.out.print("Lista circular: ");
        do {
            System.out.print(actual.data + " ");
            actual = actual.next;
        } 
        while (actual != head);
        System.out.println();
    }

    public boolean esCircular() {
        if (head == null) return false;
        Nodo actual = head.next;
        while (actual != null && actual != head) {
            actual = actual.next;
        }
        return actual == head;
    }
}