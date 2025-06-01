package ejercicio7;

public class ListaCircular {
    private NodoCircular tail;

    public void insert(int data) {
        NodoCircular nuevo = new NodoCircular(data);
        if (tail == null) {
            tail = nuevo;
            tail.next = tail;
        } 
        else {
            nuevo.next = tail.next;
            tail.next = nuevo;
            tail = nuevo;
        }
    }

    public void addFirst(int data) {
        NodoCircular nuevo = new NodoCircular(data);
        if (tail == null) {
            tail = nuevo;
            tail.next = tail;
        } 
        else {
            nuevo.next = tail.next;
            tail.next = nuevo;
        }
    }

    public void addLast(int data) {
        insert(data);
    }

    public void removeFirst() {
        if (tail == null) return;
        if (tail.next == tail) {
            tail = null;
        } 
        else {
            tail.next = tail.next.next;
        }
    }

    public void removeLast() {
        if (tail == null) return;
        if (tail.next == tail) {
            tail = null;
        } 
        else {
            NodoCircular actual = tail.next;
            while (actual.next != tail) {
                actual = actual.next;
            }
            actual.next = tail.next;
            tail = actual;
        }
    }

    public void deleteByKey(int key) {
        if (tail == null) return;
        NodoCircular actual = tail.next;
        NodoCircular prev = tail;
        do {
            if (actual.data == key) {
                if (actual == tail) {
                    if (tail.next == tail) {
                        tail = null;
                    } 
                    else {
                        prev.next = actual.next;
                        tail = prev;
                    }
                } 
                else {
                    prev.next = actual.next;
                }
                return;
            }
            prev = actual;
            actual = actual.next;
        } 
        while (actual != tail.next);
        System.out.println("Elemento no encontrado.");
    }

    public void deleteAtPosition(int pos) {
        if (tail == null || pos < 0) return;
        NodoCircular actual = tail.next;
        NodoCircular prev = tail;
        int index = 0;
        do {
            if (index == pos) {
                if (actual == tail) {
                    if (tail.next == tail) {
                        tail = null;
                    } 
                    else {
                        prev.next = actual.next;
                        tail = prev;
                    }
                } 
                else {
                    prev.next = actual.next;
                }
                return;
            }
            prev = actual;
            actual = actual.next;
            index++;
        } while (actual != tail.next);
        System.out.println("Posición no válida.");
    }

    public int size() {
        if (tail == null) return 0;
        int count = 0;
        NodoCircular actual = tail.next;
        do {
            count++;
            actual = actual.next;
        } 
        while (actual != tail.next);
        return count;
    }

    public void printList() {
        if (tail == null) {
            System.out.println("Lista vacía.");
            return;
        }

        NodoCircular actual = tail.next;
        System.out.print("Lista circular: ");
        do {
            System.out.print(actual.data + " ");
            actual = actual.next;
        } 
        while (actual != tail.next);
        System.out.println();
    }
}