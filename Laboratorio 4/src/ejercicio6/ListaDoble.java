package ejercicio6;

public class ListaDoble {
    private NodoDoble head;
    private NodoDoble tail;

    public void insert(int data) {
        NodoDoble nuevo = new NodoDoble(data);
        if (head == null) {
            head = tail = nuevo;
        } 
        else {
            tail.next = nuevo;
            nuevo.prev = tail;
            tail = nuevo;
        }
    }

    public void addFirst(int data) {
        NodoDoble nuevo = new NodoDoble(data);
        if (head == null) {
            head = tail = nuevo;
        } 
        else {
            nuevo.next = head;
            head.prev = nuevo;
            head = nuevo;
        }
    }

    public void addLast(int data) {
        insert(data);
    }

    public void removeFirst() {
        if (head == null) return;
        head = head.next;
        if (head != null) head.prev = null;
        else tail = null;
    }

    public void removeLast() {
        if (tail == null) return;
        tail = tail.prev;
        if (tail != null) tail.next = null;
        else head = null;
    }

    public void deleteByKey(int key) {
        NodoDoble actual = head;
        while (actual != null && actual.data != key) {
            actual = actual.next;
        }
        if (actual == null) {
            System.out.println("Elemento no encontrado.");
            return;
        }
        if (actual == head) removeFirst();
        else if (actual == tail) removeLast();
        else {
            actual.prev.next = actual.next;
            actual.next.prev = actual.prev;
        }
    }

    public void deleteAtPosition(int pos) {
        if (pos < 0) return;

        NodoDoble actual = head;
        int index = 0;

        while (actual != null && index < pos) {
            actual = actual.next;
            index++;
        }

        if (actual == null) {
            System.out.println("Posición no válida.");
            return;
        }

        if (actual == head) removeFirst();
        else if (actual == tail) removeLast();
        else {
            actual.prev.next = actual.next;
            actual.next.prev = actual.prev;
        }
    }

    public int size() {
        int count = 0;
        NodoDoble actual = head;
        while (actual != null) {
            count++;
            actual = actual.next;
        }
        return count;
    }

    public void printList() {
        NodoDoble actual = head;
        System.out.print("Lista hacia adelante: ");
        while (actual != null) {
            System.out.print(actual.data + " ");
            actual = actual.next;
        }
        System.out.println();
    }

    public void printReverse() {
        NodoDoble actual = tail;
        System.out.print("Lista hacia atrás: ");
        while (actual != null) {
            System.out.print(actual.data + " ");
            actual = actual.prev;
        }
        System.out.println();
    }
}