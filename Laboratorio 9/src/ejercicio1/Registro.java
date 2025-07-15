package ejercicio1;

public class Registro<E> implements Comparable<Registro<E>> {
    private int clave;
    private E valor;

    public Registro(int clave, E valor) {
        this.clave = clave;
        this.valor = valor;
    }

    public int obtenerClave() {
        return clave;
    }

    public E obtenerValor() {
        return valor;
    }

    public void establecerValor(E valor) {
        this.valor = valor;
    }

    public int compareTo(Registro<E> otro) {
        return Integer.compare(this.clave, otro.clave);
    }

    public String toString() {
        return clave + ": " + valor;
    }
}
