package ejercicio1;

public class Nodo<T> {
    T dato;
    Nodo<T> izquierdo, derecho;

    public Nodo(T dato) {
        this.dato = dato;
        this.izquierdo = null;
        this.derecho = null;
    }
}