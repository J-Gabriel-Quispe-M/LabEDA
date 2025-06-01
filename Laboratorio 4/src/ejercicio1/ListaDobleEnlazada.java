package ejercicio1;

public class ListaDobleEnlazada {
    private Nodo head;

    public void insertar(int data) {
        Nodo nuevo = new Nodo(data);
        if (head == null) {
            head = nuevo;
            return;
        }
        Nodo actual = head;
        while (actual.next != null) {
            actual = actual.next;
        }
        actual.next = nuevo;
        nuevo.prev = actual;
    }

    public void imprimirAdelante() {
        Nodo actual = head;
        System.out.print("Lista hacia adelante: ");
        while (actual != null) {
            System.out.print(actual.data + " ");
            actual = actual.next;
        }
        System.out.println();
    }

    public void imprimirAtras() {
        Nodo actual = head;
        if (actual == null) return;
        while (actual.next != null) {
            actual = actual.next;
        }
        System.out.print("Lista hacia atrás: ");
        while (actual != null) {
            System.out.print(actual.data + " ");
            actual = actual.prev;
        }
        System.out.println();
    }
}

