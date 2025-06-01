package ejercicio5;

public class ListaSimple {
    private Nodo head;

    public void insert(int data) {
        Nodo nuevo = new Nodo(data);
        if (head == null) {
            head = nuevo;
        } 
        else {
            Nodo actual = head;
            while (actual.next != null) {
                actual = actual.next;
            }
            actual.next = nuevo;
        }
    }

    public void addFirst(int data) {
        Nodo nuevo = new Nodo(data);
        nuevo.next = head;
        head = nuevo;
    }

    public void addLast(int data) {
        insert(data);
    }

    public void removeFirst() {
        if (head != null) {
            head = head.next;
        }
    }

    public void removeLast() {
        if (head == null || head.next == null) {
            head = null;
            return;
        }
        Nodo actual = head;
        while (actual.next.next != null) {
            actual = actual.next;
        }
        actual.next = null;
    }

    public void deleteByKey(int key) {
        Nodo actual = head, anterior = null;
        while (actual != null && actual.data != key) {
            anterior = actual;
            actual = actual.next;
        }
        if (actual == null) {
            System.out.println("Elemento no encontrado.");
            return;
        }
        if (anterior == null) {
            head = actual.next;
        } 
        else {
            anterior.next = actual.next;
        }
    }

    public void deleteAtPosition(int pos) {
        if (pos == 0 && head != null) {
            head = head.next;
            return;
        }
        Nodo actual = head;
        Nodo anterior = null;
        int i = 0;
        while (actual != null && i < pos) {
            anterior = actual;
            actual = actual.next;
            i++;
        }
        if (actual == null) {
            System.out.println("Posición no válida.");
            return;
        }
        anterior.next = actual.next;
    }

    public int size() {
        int count = 0;
        Nodo actual = head;
        while (actual != null) {
            count++;
            actual = actual.next;
        }
        return count;
    }

    public void printList() {
        Nodo actual = head;
        System.out.print("Lista: ");
        while (actual != null) {
            System.out.print(actual.data + " ");
            actual = actual.next;
        }
        System.out.println();
    }
}