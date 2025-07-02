package ejercico2;

public class NodoAVL<T extends Comparable<T>> {
    public T dato;
    public NodoAVL<T> izquierdo;
    public NodoAVL<T> derecho;
    public int altura;

    public NodoAVL(T dato) {
        this.dato = dato;
        this.altura = 1;
    }
}

